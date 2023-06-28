package com.lab3.grupo01_lab3_2023;

import com.lab3.grupo01_lab3_2023.HTTPRequest.HTTPRequester;
import com.lab3.grupo01_lab3_2023.feed.Article;
import com.lab3.grupo01_lab3_2023.feed.Feed;
import com.lab3.grupo01_lab3_2023.namedEntity.NamedEntity;
import com.lab3.grupo01_lab3_2023.namedEntity.heuristic.DigimonHeuristic;
import com.lab3.grupo01_lab3_2023.namedEntity.heuristic.Heuristic;
import com.lab3.grupo01_lab3_2023.namedEntity.heuristic.QuickHeuristic;
import com.lab3.grupo01_lab3_2023.namedEntity.heuristic.RandomHeuristic;
import com.lab3.grupo01_lab3_2023.parser.*;
import com.lab3.grupo01_lab3_2023.parser.feed.FeedParser;
import com.lab3.grupo01_lab3_2023.parser.feed.RSSParser;
import com.lab3.grupo01_lab3_2023.parser.feed.RedditParser;
import com.lab3.grupo01_lab3_2023.subscription.SingleSubscription;

import scala.Tuple2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.HashMap;

import org.apache.spark.sql.SparkSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class FeedReaderMain {

    static HashMap<String, Heuristic> heuristics = new HashMap<>();

    static {
        heuristics.put("quick", new QuickHeuristic()); //lo usi para ver que pasa si no le pongo ninguna heuristica
        heuristics.put("random", new RandomHeuristic()); //lo uso para ver que pasa si no le pongo ninguna heuristica
        heuristics.put("digimon", new DigimonHeuristic());
    }

    static String DEFAULT_HEURISTIC = "quick";

    private static void printHelp() {
        System.out.println("Please, call this program in correct way: FeedReader [-ne] [Heuristic: quick, random, digimon]");
    }

    // extrae un feed de una subscripcion especifica
    private static List<Feed> getFeeds(SingleSubscription subscription) {
        System.out.println("Obteniendo Feeds para una subscripcion");
        System.out.printf("Tipo de subscripcion: %s%n", subscription.getUrlType());
        List<Feed> feeds = new ArrayList<Feed>();
        // para cada seccion de la suscripcion
        for (String param : subscription.getUrlParams()) {
            try {
                System.out.printf("Parametro de este Feed: %s%n", param);
                // formateamos la url con la seccion
                String formattedUrl = String.format(subscription.getUrl(), param);
                System.out.printf("URL final: %s%n", formattedUrl);

                // hacemos la reequest http de la url formateada
                HTTPRequester requester = new HTTPRequester();

                // creamos un parser de acuerdo al tipo dado e el json
                FeedParser parser;
                if (subscription.getUrlType().equals("rss")) {
                    parser = new RSSParser();
                    System.out.println("Iniciando peticion y parseo del Feed.");
                    Feed feed = parser.parseFeed(requester.getFeedRss(formattedUrl));
                    feeds.add(feed);
                } else if (subscription.getUrlType().equals("reddit")) {
                    parser = new RedditParser();
                    System.out.println("Iniciando peticion y parseo del Feed.");
                    Feed feed = parser.parseFeed(requester.getFeedReedit(formattedUrl));
                    feeds.add(feed);
                } else {
                    System.out.println("Tipo de subscripcion invalida!");
                    break;
                }
            } catch (MalformedURLException e) {
                System.out.printf("URL de la subscripcion malformada: %s%n", e);
            } catch (IOException e) {
                System.out.printf("No se pudo conectar al servidor del feed, comprobar la conexion al internet: %s%n", e);
            }

        }
        return feeds;
    }

    private static void searchword(JavaRDD<Feed> feedsRDD) {
        //Pedimos por consola la palabra
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una palabra a buscar: ");
        String word = scanner.next().toLowerCase();
        // Filtrar los artículos que contienen la palabra o entidad nombrada
        JavaRDD<Article> matchingArticlesRDD = feedsRDD
                .flatMap(feed -> feed.getArticleList().iterator())
                .filter(article -> article.containsTerm(word));

        // Contar las ocurrencias de la palabra o entidad nombrada por artículo
        JavaPairRDD<Article, Integer> occurrencesRDD = matchingArticlesRDD
                .mapToPair(article -> new Tuple2<>(article, article.countOccurrences(word)));

        // Ordenar los artículos según la cantidad de ocurrencias de la palabra o entidad nombrada
        List<Tuple2<Article, Integer>> sortedOccurrences = occurrencesRDD.collect();

        List<Tuple2<Article, Integer>> sortedOccurrencesList = new ArrayList<>(sortedOccurrences);
        Collections.sort(sortedOccurrencesList, (a1, a2) -> Integer.compare(a2._2, a1._2));

        // Mostrar los resultados ordenados
        System.out.println("Los documentos que contienen la palabra o entidad nombrada son:");
        for (Tuple2<Article, Integer> tuple : sortedOccurrencesList) {
            Article article = tuple._1;
            int count = tuple._2;
            System.out.println("Título: " + article.getTitle());
            System.out.println("Contenido: " + article.getText());
            System.out.println("Cantidad de ocurrencias: " + count);
            System.out.println("--------------------------------------");
        }
    }

    public static void main(String[] args) {
        System.out.println("************* FeedReader version 1.0 *************");
        SparkSession spark = SparkSession.builder()
                .appName("FeedReaderMain")
                .master("local[*]")
                .getOrCreate();
        if (args.length <= 2) {
            if (args.length >= 1 && !args[0].equalsIgnoreCase("-ne")) {
                printHelp();
                return;
            }
            Heuristic h = heuristics.get(DEFAULT_HEURISTIC);
            if (args.length == 2) {
                h = heuristics.get(args[1].toLowerCase());
                if (h == null) {
                    printHelp();
                    return;
                }
            }

            SubscriptionParser parser = new SubscriptionParser(Path.of("config/subscriptions.json"));
            List<SingleSubscription> subscriptions = parser.parse();
            JavaSparkContext sparkContext = JavaSparkContext.fromSparkContext(spark.sparkContext());
            JavaRDD<SingleSubscription> subscriptionsRDD = sparkContext.parallelize(subscriptions);

            JavaRDD<Feed> feedsRDD = subscriptionsRDD.flatMap(subscription
                    -> getFeeds(subscription).iterator()).cache();

            if (args.length == 0) {
                // LLamar al prettyPrint del Feed para ver los articulos del feed en forma legible y amigable para el usuario
                feedsRDD.foreach(Feed::prettyPrint);
                searchword(feedsRDD);

            } else {

                // * Llamar a la heuristica para que compute las entidades nombradas de CADA ARTICULO del feed
                // * LLamar al prettyPrint de la tabla de entidades nombradas del feed.
                JavaPairRDD<NamedEntity, Integer> namedEntitiesRDD = feedsRDD.flatMap(feed -> {
                    List<NamedEntity> entities = new ArrayList<NamedEntity>();
                    for (Article article : feed.getArticleList()) {
                        Heuristic h1 = heuristics.get(DEFAULT_HEURISTIC);
                        article.computeNamedEntities(h1);
                        entities.addAll(article.getListNamedEntity());
                    }
                    feed.prettyPrint();
                    return entities.iterator();
                }).mapToPair(namedEntity -> new Tuple2<>(namedEntity, namedEntity.getFrequency()));

                JavaPairRDD<NamedEntity, Integer> reducedRDD = namedEntitiesRDD.reduceByKey((value1, value2) -> value1 + value2);
                // Imprimir el resultado
                reducedRDD.foreach(tuple -> System.out.println("Entidad Nombrada: " + tuple._1().getName() + " Categoria: " + tuple._1().getCategory() + " Frecuencia: " + tuple._2()));

                searchword(feedsRDD);

            }
        } else {
            printHelp();
        }
        spark.stop();
    }
}