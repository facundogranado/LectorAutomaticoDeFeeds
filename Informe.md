# Laboratorio 3: Programación asistida para Frameworks sobre Cálculo Distribuido

En este laboratorio vamos a implementar un lector automático de feeds,sobre una arquitectura distribuida, a través del
framework Spark, utilizando como lenguaje de programación Java.

### Funcionalidad
El usuario de nuestro lector establece, mediante un archivo en formato .json, los
distinto sitios (ej: new york times, etc) y sus respectivos tópicos (sports, business, etc) de
los cuales obtener los diferentes feeds a mostrar por pantalla en forma legible y
amigable para el usuario. Además, se agrega una funcionalidad a nuestro lector para
computar heurísticamente las entidades nombradas más mencionadas en la lista de
feeds.



### Incorporación de la funcionalidad de recuperar documentos por palabras clave

La funcionalidad de recuperar los documentos que contienen una determinada palabra o entidad nombrada, ordenados por la cantidad de ocurrencias de esa palabra o entidad, se implementa de la siguiente manera:

1.  Se solicita al usuario que ingrese una palabra clave por consola.
    
2.  Los artículos en `feedsRDD` se filtran utilizando la función `flatMap` y `filter`. `flatMap` se utiliza para obtener una lista plana de todos los artículos de todos los feeds, y luego `filter` se utiliza para retener solo aquellos artículos que contienen la palabra clave ingresada.
    
3.  Se crea un par RDD llamado `occurrencesRDD` utilizando `mapToPair`. Cada artículo se mapea a un par que consiste en el artículo mismo y el número de ocurrencias de la palabra clave dentro del artículo.
    
4.  La lista `sortedOccurrences` se crea recopilando los elementos de `occurrencesRDD`.
    
5.  La lista `sortedOccurrences` se ordena utilizando `Collections.sort` en orden descendente basándose en el número de ocurrencias de la palabra clave en cada artículo.
    
6.  Se muestra el resultado ordenado en la consola, que incluye el título, contenido y la cantidad de ocurrencias de la palabra clave en cada artículo.
