package U3_Red.clase._01_SocketStreamObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorSocketStreamObject {

  private static final int PUERTO = 6000;

  private static int aleatorio(int min, int max) {
    Random r = new Random();
    return min + r.nextInt(max - min + 1); // Entre min y max -> [min,max];
  }

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(PUERTO);

      while (true) {
        System.out.println("SERVIDOR: Escuchando por el puerto " + PUERTO + " ...");
        Socket clientSocket = serverSocket.accept();

        // 1. Crear flujo de SALIDA hacia el cliente (envío del OBJETO)
        ObjectOutputStream flujoSalida = new ObjectOutputStream(clientSocket.getOutputStream());

        // 2. ENVÍO del OBJETO hacia el cliente
        Integer numero_aleatorio = aleatorio(1, 10);
        flujoSalida.writeObject(numero_aleatorio);

        // 3. Cierre de flujos y conexión del cliente (en orden)
        flujoSalida.close();
        clientSocket.close();
      }

      // 4. Cierre de conexión principal del servidor
      // serverSocket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
