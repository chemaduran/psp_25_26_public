package U3_Red.clase._00_SocketSimple;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketStream2 {

  private static final int PUERTO = 6000;
  private static final int NCONEXIONES = 5;

  public static void main(String[] args) {
    try {
      // 1. Creación del socket servidor (puerto localhost)
      // Asignación de dirección y puerto (BIND)
      ServerSocket serverSocket = new ServerSocket(PUERTO);

      // 2. Espera/escucha del cliente (LISTEN) y aceptación de conexiones
      // (ACCEPT)
      int nconexiones = 0;

      while (nconexiones < NCONEXIONES) {
        System.out.println("SERVIDOR: Escuchando por el puerto " + PUERTO + " ...");
        Socket clientSocket = serverSocket.accept();

        // 3. Flujo de entrada del cliente (recepción del mensaje)
        DataInputStream flujoEntrada = new DataInputStream(clientSocket.getInputStream());
        System.out.println("MENSAJE RECIBIDO: " + flujoEntrada.readUTF());

        // 4. Cierre de flujos y conexión (en orden)
        flujoEntrada.close();

        // 5. Cierre de conexión del cliente
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
