# Lector Automatico De Feeds
Se implemento un lector automático de feeds,sobre una arquitectura distribuida, a través del framework Spark, utilizando como lenguaje de programación Java.

# Funcionalidad
El usuario de nuestro lector establece, mediante un archivo en formato .json, los distinto sitios (ej: new york times, etc) y sus respectivos tópicos (sports, business, etc) de los cuales obtener los diferentes feeds a mostrar por pantalla en forma legible y amigable para el usuario. Además, se agrega una funcionalidad a nuestro lector para computar heurísticamente las entidades nombradas más mencionadas en la lista de feeds.

# Incorporación de la funcionalidad de recuperar documentos por palabras clave
La funcionalidad de recuperar los documentos que contienen una determinada palabra o entidad nombrada, ordenados por la cantidad de ocurrencias de esa palabra o entidad, se implementa de la siguiente manera:

Se solicita al usuario que ingrese una palabra clave por consola.

Los artículos en feedsRDD se filtran utilizando la función flatMap y filter. flatMap se utiliza para obtener una lista plana de todos los artículos de todos los feeds, y luego filter se utiliza para retener solo aquellos artículos que contienen la palabra clave ingresada.

Se crea un par RDD llamado occurrencesRDD utilizando mapToPair. Cada artículo se mapea a un par que consiste en el artículo mismo y el número de ocurrencias de la palabra clave dentro del artículo.

La lista sortedOccurrences se crea recopilando los elementos de occurrencesRDD.

La lista sortedOccurrences se ordena utilizando Collections.sort en orden descendente basándose en el número de ocurrencias de la palabra clave en cada artículo.

Se muestra el resultado ordenado en la consola, que incluye el título, contenido y la cantidad de ocurrencias de la palabra clave en cada artículo.




# Detalles de compilación y ejecución del proyecto

## Requisitos previos
Antes de compilar y ejecutar FeedReader, asegúrate de tener instalado lo siguiente:

Java Development Kit (JDK) 8 o superior
Apache Maven
Apache Spark
Scala 3.3.0

opcional:
IDE Apache NetBeans 18

## Por terminal

### Compilación
Sigue estos pasos para compilar el proyecto:


1. Navega al directorio del proyecto:

   ```shell
   cd .\Grupal\grupo01_lab03_2023\
   ```
2. Compila el proyecto utilizando Maven:

   ```shell
   mvn package
   ```
3. El comando anterior generará un archivo JAR en la carpeta target. Puedes encontrarlo en la siguiente ruta:

   ```bash
   target/grupo01_lab03_2023-1.0-SNAPSHOT.jar
   ```

### Ejecución

Después de compilar el proyecto, puedes ejecutarlo con el siguiente comando:
   ```shell
 spark-submit --class com.lab3.grupo01_lab3_2023.FeedReaderMain --master local[*] target/grupo01_lab03_2023-1.0-SNAPSHOT.jar [opciones]
 ```                                                                                     
Reemplaza `[opciones]` con los argumentos que desees proporcionar al programa. Aquí tienes algunos ejemplos:

- Para especificar una heurística de análisis de entidades nombradas:

  ```shell 
   spark-submit --class  com.lab3.grupo01_lab3_2023.FeedReaderMain --master local[*] target/grupo01_lab03_2023-1.0-SNAPSHOT.jar -ne [heurística]
   ```
Reemplaza `[heurística]` con una de las siguientes opciones: quick, random, digimon.

Después de ejecutar el comando, se mostrará en la consola el resultado correspondiente, junto con un mensaje que solicitará al usuario que ingrese una palabra que desee buscar con el lector.

![consola](./img/Captura%20de%20pantalla%202023-06-17%20224921.png)


## Por IDE NetBeans

### Compilación

1. Abre el proyecto en NetBeans.
2. Haz clic derecho en el proyecto y selecciona "Clean and Build".
3. El comando anterior generará un archivo JAR en la carpeta target. Puedes encontrarlo en la siguiente ruta:

   ```bash
   target/grupo01_lab03_2023-1.0-SNAPSHOT.jar
   ```

### Ejecución

Para ejecutar el proyecto, haz clic derecho en el proyecto y selecciona "Run". Esto ejecutará el proyecto con las opciones predeterminadas.

Para especificar opciones de ejecución, haz clic derecho en el proyecto y selecciona "Set Configuration" y luego "Customize". 

![configuration](./img/Captura%20de%20pantalla%202023-06-17%20230557.png)

En la ventana de propiedades, selecciona "Run" en el panel izquierdo. En el panel derecho, tendra un campo para configurar la clase principal, un campo para agregar los argumentos y un campo para las opciones de la maquina virtual.

VM Options: --add-opens java.base/sun.nio.ch=ALL-UNNAMED;

![properties](./img/Captura%20de%20pantalla%202023-06-17%20231039.png)

Lueog de configurar las opciones, haz clic en "OK" y luego en "Run" para ejecutar el proyecto.

## En caso de errores de ejecución
En caso de que se produzca el siguiente error al ejecutar el proyecto:

![error](./img/Captura%20de%20pantalla%202023-06-17%20231701.png)

Se puede solucionar editando el archivo pom.xml y modificando versionas de las dependencias de spark como sigue:

Antes:

![pom_antes](./img/Captura%20de%20pantalla%202023-06-17%20232316.png)

Despues:

![pom_despues](./img/Captura%20de%20pantalla%202023-06-17%20232346.png)
