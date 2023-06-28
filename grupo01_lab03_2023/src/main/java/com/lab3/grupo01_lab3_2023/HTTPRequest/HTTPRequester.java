package com.lab3.grupo01_lab3_2023.HTTPRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class HTTPRequester {
        public String getFeedRss(String urlFeed) throws ProtocolException, IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL(urlFeed).openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder feedRssXml;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                feedRssXml = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    feedRssXml.append(inputLine);
                }
            }
            return feedRssXml.toString();
        } else {
            throw new IOException("HTTP request failed with status code " + responseCode);
        }
    }

    public String getFeedReedit(String urlFeed) {
        String feedReeditJson = null;
        
          try {
            URL url = new URL(urlFeed);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Scanner scanner = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                scanner.close();
                
                // la respuesta es un objeto JSON en formato de cadena de caracteres
                feedReeditJson = response.toString();
                
            } else {
                System.out.println("HTTP request failed with status code " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedReeditJson;
    }

    public static void main(String[] args) throws Exception {

        String url = "https://www.reddit.com/r/Marketing/hot/.json?count=100";
        HTTPRequester s  = new HTTPRequester();
        String rss = s.getFeedReedit(url);
        System.out.println(rss);
}
    
}

