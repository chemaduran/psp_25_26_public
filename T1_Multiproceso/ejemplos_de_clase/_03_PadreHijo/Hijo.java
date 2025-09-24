package U1_Multiproceso._03_PadreHijo;

public class Hijo {
  public static void main(String[] args)  {

    try {
      System.out.println("Main thread: Let's take a short nap.");
      System.out.flush();
      Thread.sleep(5000); // Sleep for 2 seconds
      System.out.println("Main thread: That was refreshing!");
    } catch (InterruptedException e) {
      System.out.println("Main thread: Someone woke me up!");
    }


//    System.out.println("Soy el hijo y me duermo tres segundos ...");
//    Thread.sleep(3000);

    // Finalización del proceso con valor de salida
    System.exit(50);
  }
}
