package U3_Red.clase._05_TCPMultihilo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class HiloCliente extends Thread {

  private Socket cliente = null;
  private DataOutputStream flujoSalida;

  public HiloCliente(Socket cliente) {
    this.cliente = cliente;
    try {
      flujoSalida = new DataOutputStream(cliente.getOutputStream());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static int aleatorio(int min, int max) {
    Random r = new Random();
    return min + r.nextInt(max - min + 1); // Entre min y max -> [min,max];
  }

  public void run() {
    try {
      System.out.println("INICIO DE COMUNICACIÓN: " + cliente.getInetAddress().getHostAddress());
      int num = aleatorio(1, 10);
      flujoSalida.writeUTF("ME DORMIRÉ " + num + " SEGUNDOS");

      // Espera simulada de N segundos (observar comportamiento
      // concurrente del servidor cuando genera hilos dedicados)
      Thread.sleep(num * 1000L);

      System.out.println(
          "FIN DE COMUNICACIÓN (tras pasar "
              + num
              + " segundos): "
              + cliente.getInetAddress().getHostAddress());

      // Cierre de flujos y conexión (en orden)
      flujoSalida.close();

      // Cierre de conexión del cliente
      cliente.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
