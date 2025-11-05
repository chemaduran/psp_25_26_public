package U2_Multihilo._02c_Productor_Consumidor_ABC;

public class BufferLetra {
  private char letra;
  private boolean estaNumero = false;

  public synchronized void dejarLetra(char n) {

    // ESPERA ACTIVA mientras se esté consumiendo
    while (estaNumero) {
      try {
        wait();
      } catch (InterruptedException e) {
        // Print the exception and the stack trace, line by line in a for each
        System.out.println("Error en la espera del productor: " + e.getLocalizedMessage());
        for (StackTraceElement element : e.getStackTrace()) {
          System.out.println(element);
        }
      }
    }

    // 1. El número se coloca
    letra = n;

    // 2. El número ya está disponible para recogerlo
    estaNumero = true;

    // 3. Notifica al resto de hilos que están suspendidos (CONSUMIDORES)
    notifyAll();
  }

  public synchronized char cogerLetra() {
    char l = 0;

    // ESPERA ACTIVA mientras se esté produciendo
    while (!estaNumero) {
      try {
        wait();
      } catch (InterruptedException e) {
        // Print the exception and the stack trace, line by line in a for each
        System.out.println("Error en la espera del consumidor: " + e.getLocalizedMessage());
        for (StackTraceElement element : e.getStackTrace()) {
          System.out.println(element);
        }
      }
    }

    // CONDICIÓN: Si hay algún número ya puesto, lo recojo
    // 1. El número se recoge
    l = letra;

    // 2. Vuelve a su estado inicial
    estaNumero = false;

    // 3. Notifica al resto de hilos que están suspendidos (PRODUCTORES)
    notifyAll();

    return l;
  }
}
