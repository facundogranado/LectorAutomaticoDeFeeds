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