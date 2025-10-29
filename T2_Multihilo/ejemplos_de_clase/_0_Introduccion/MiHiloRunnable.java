package U2_Multihilo._0_Introduccion;

public class MiHiloRunnable implements Runnable {
  @Override
  public void run() {
    for (int i = 0; i < 500; i++) {
      System.out.println("Soy un hilo y esto es lo que hago");
    }
  }
}
