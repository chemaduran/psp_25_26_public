# Ejercicio: Abecedario - Consumidor de letras

Diseñar una aplicación `Abecedario` en la que un hilo `H1` genere `N` carácteres aleatorios <ins>en mayúsculas</ins> (
consultar tabla ASCII) y otro hilo `H2` obtenga los mismos `N` carácteres, por cada letra que genere `H1`. Ambos hilos
deben comunicarse entre sí para que cada una de estas letras, `H2` vaya mostrándolas por pantalla.

Para realizar la comunicación y sincronización: `H1` utiliza el método de `dejarLetra(char letra)` y `H2` el método `char
cogerLetra()`. Ambos métodos pertenecen a la clase `BufferLetra`.

Simular el funcionamiento para sincronización de los métodos `dejarLetra` y `cogerLetra` junto a una correcta
comunicación entre
hilos, esperando a que `H2` finalice su ejecución concurrente para acabar la simulación.

```
H2: G
H2: Y
H2: O
H2: O
H2: M
Generación de 5 letras acabada
```

