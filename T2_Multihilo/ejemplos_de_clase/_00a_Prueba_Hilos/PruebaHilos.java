package U2_Multihilo._00a_Prueba_Hilos;

public class PruebaHilos {

  public static void main(String[] args) {

    System.out.println(
        "CON THREAD: Yo soy el hilo principal y creo Hilo 1 (Que extiende de thread)");
    MiHiloThread hilo1 = new MiHiloThread();
    hilo1.setName("hilo1");
    hilo1.start();

    System.out.println(
        "CON RUNNABLE: Yo soy el hilo principal y creo Hilo 2(Que implementa Runnable)");
    MiHiloRunnable tarea2 = new MiHiloRunnable();
    Thread hilo2 = new Thread(tarea2);
    hilo2.setName("hilo2");
    hilo2.start();

    System.out.println(
        "CON RUNNABLE: Yo soy el hilo principal y creo Hilo 3(Que implementa Runnable y tiene un atributo Thread)");
    MiHiloRunnableComplejo hilo3 = new MiHiloRunnableComplejo("Hilo3");
    hilo3.iniciarHilo();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException ex) {
      System.out.println("Error al dormir");
    }
    System.out.println("Y ahora voy a terminar el hilo que he creado");
    hilo3.desactivarHilo();
    System.out.println("CON RUNNABLE: Fin del hilo principal");
  }
}
