package U3_Red.clase._02_SocketBidireccional;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketStreamBidireccional {

  private static final int PUERTO = 6000;
  private static final int NCONEXIONES = 5;

  public static void main(String[] args) {
    try {
      ServerSocket serverSocket = new ServerSocket(PUERTO);
      int nconexiones = 0;

      while (nconexiones < NCONEXIONES) {
        System.out.println("SERVIDOR: Escuchando por el puerto " + PUERTO + " ...");
        Socket clientSocket = serverSocket.accept();

        // 1. Crear flujo de ENTRADA del cliente (recepción del mensaje)
        DataInputStream flujoEntrada = new DataInputStream(clientSocket.getInputStream());

        // 2. RECEPCIÓN del mensaje del cliente
        System.out.println("SERVIDOR: MENSAJE RECIBIDO: " + flujoEntrada.readUTF());

        // 3. Crear flujo de SALIDA hacia el cliente (envío del mensaje)
        DataOutputStream flujoSalida = new DataOutputStream(clientSocket.getOutputStream());

        // 4. ENVÍO del mensaje hacia el cliente
        flujoSalida.writeUTF("Hola amigo !");

        // 5. Cierre de flujos y conexión del cliente (en orden)
        flujoEntrada.close();
        flujoSalida.close();
        clientSocket.close();

        nconexiones++;
      }

      // 6. Cierre de conexión principal del servidor
      serverSocket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
