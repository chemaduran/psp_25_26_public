package U3_Red.clase._03_SocketObjectYMensaje;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServidorSocketStreamObject {

  private static final int PUERTO = 6000;
  private int N_CONEXIONES = 7;

  private static int aleatorio(int min, int max) {
    Random r = new Random();
    return min + r.nextInt(max - min + 1); // Entre min y max -> [min,max];
  }

  public static void main(String[] args) throws ClassNotFoundException {
    try {
      ServerSocket serverSocket = new ServerSocket(PUERTO);
      int numcunexiones = 0;
      while (numcunexiones < 10) {
        System.out.println("SERVIDOR: Escuchando por el puerto " + PUERTO + " ...");
        Socket clientSocket = serverSocket.accept();

        // 1. Crear flujo de SALIDA hacia el cliente (envío del OBJETO)
        ObjectOutputStream flujoSalidaObjeto =
            new ObjectOutputStream(clientSocket.getOutputStream());

        // 2. ENVÍO del OBJETO hacia el cliente
        Integer numero_aleatorio = aleatorio(1, 10);
        flujoSalidaObjeto.writeObject(numero_aleatorio);

        // 3. Crear flujo de SALIDA hacia el cliente (envío del mensaje)
        DataOutputStream flujoSalida = new DataOutputStream(clientSocket.getOutputStream());

        // 4. ENVÍO del mensaje hacia el cliente
        flujoSalida.writeUTF("Número de conexiones!" + numcunexiones);

        // 5. Cierre de flujos y conexión del cliente (en orden)
        flujoSalidaObjeto.close();
        flujoSalida.close();
        clientSocket.close();
        numcunexiones++;
      }

      // 4. Cierre de conexión principal del servidor
      // serverSocket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
