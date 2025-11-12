package U2_Multihilo._02a_ProductorConsumidorFallido;

import static java.lang.System.exit;

import java.util.LinkedList;
import java.util.Queue;

public class PCNoSincronizado {
  public static void main(String[] args) {
    Queue<Integer> buffer = new LinkedList<>();
    int capacidad = 5;

    Thread productor = new Thread(new Productor(buffer, capacidad));
    Thread consumidor = new Thread(new Consumidor(buffer));

    consumidor.start();
//    try {
//      Thread.sleep(200);
//    } catch (InterruptedException e) {
//      System.out.println("Error en el hilo principal");
//      exit(1);
//    }
    productor.start();
  }
}
