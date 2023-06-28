# Laboratorio 3: Programación asistida para Frameworks sobre Cálculo Distribuido


### Elección de una solución

Después de evaluar los tres proyectos individuales presentados por los miembros del grupo y observar la similitud en la estructura del código y el uso del framework Spark, se decidió seleccionar el proyecto desarrollado por la alumna Malena Bustamante. Esta decisión se basó en las siguientes razones:

- Malena demostró un aprovechamiento efectivo de las funcionalidades proporcionadas por Spark tanto en el procesamiento de los pedidos de los feeds como en la búsqueda de entidades nombradas.

- En particular, al implementar el modelo de Map-Reduce para la búsqueda de entidades nombradas, Malena utilizó de manera adecuada tanto las funciones de mapeo (flatMap y mapToPair) como las de reducción (reduceByKey).

### Incorporación de la funcionalidad de recuperar documentos por palabras clave

La funcionalidad de recuperar los documentos que contienen una determinada palabra o entidad nombrada, ordenados por la cantidad de ocurrencias de esa palabra o entidad, se implementa de la siguiente manera:

1.  Se solicita al usuario que ingrese una palabra clave por consola.
    
2.  Los artículos en `feedsRDD` se filtran utilizando la función `flatMap` y `filter`. `flatMap` se utiliza para obtener una lista plana de todos los artículos de todos los feeds, y luego `filter` se utiliza para retener solo aquellos artículos que contienen la palabra clave ingresada.
    
3.  Se crea un par RDD llamado `occurrencesRDD` utilizando `mapToPair`. Cada artículo se mapea a un par que consiste en el artículo mismo y el número de ocurrencias de la palabra clave dentro del artículo.
    
4.  La lista `sortedOccurrences` se crea recopilando los elementos de `occurrencesRDD`.
    
5.  La lista `sortedOccurrences` se ordena utilizando `Collections.sort` en orden descendente basándose en el número de ocurrencias de la palabra clave en cada artículo.
    
6.  Se muestra el resultado ordenado en la consola, que incluye el título, contenido y la cantidad de ocurrencias de la palabra clave en cada artículo.
