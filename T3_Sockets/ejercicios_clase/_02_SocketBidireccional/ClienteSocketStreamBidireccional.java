package U3_Red.clase._02_SocketBidireccional;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteSocketStreamBidireccional {

  private static final int PUERTO = 6000;
  private static final String SERVER = "localhost";

  public static void main(String[] args) {
    try {
      Socket clientSocket = new Socket();
      InetSocketAddress addr = new InetSocketAddress(SERVER, PUERTO);
      clientSocket.connect(addr);

      // 1. Crear flujo de SALIDA hacia el servidor (envío del mensaje)
      DataOutputStream flujoSalida = new DataOutputStream(clientSocket.getOutputStream());

      // 2. ENVÍO del mensaje hacia el servidor
      String mensaje =
          "Hola soy el CLIENTE con dirección " + InetAddress.getLocalHost().getHostAddress();
      flujoSalida.writeUTF(mensaje);

      // 3. Crear flujo de ENTRADA al servidor (recepción del mensaje)
      DataInputStream flujoEntrada = new DataInputStream(clientSocket.getInputStream());

      // 4. RECEPCION del mensaje del servidor
      System.out.println("CLIENTE: Recibiendo del SERVIDOR: " + flujoEntrada.readUTF());

      // 5. Cierre de flujos de datos y conexión (en orden)
      flujoSalida.close();
      flujoEntrada.close();
      clientSocket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
