package U3_Red.clase._01_SocketStreamObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteSocketStreamObject {

  private static final int PUERTO = 6000;
  private static final String SERVER = "localhost";

  public static void main(String[] args) {
    try {
      Socket clientSocket = new Socket();
      InetSocketAddress addr = new InetSocketAddress(SERVER, PUERTO);
      clientSocket.connect(addr);

      // 1. Crear flujo de ENTRADA al servidor (recepción del OBJETO)
      ObjectInputStream flujoEntrada = new ObjectInputStream(clientSocket.getInputStream());

      // 2. RECEPTION del OBJETO del servidor
      Integer numero = (Integer) flujoEntrada.readObject();
      System.out.println("CLIENTE: Recibiendo del SERVIDOR el numero: " + numero);

      // 3. Cierre de flujos de datos y conexión (en orden)
      flujoEntrada.close();
      clientSocket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
      System.out.println("No se ha podido realizar la conversión del objeto");
    }
  }
}
