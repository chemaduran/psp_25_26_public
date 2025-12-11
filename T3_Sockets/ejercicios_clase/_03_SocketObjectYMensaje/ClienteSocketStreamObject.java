package U3_Red.clase._03_SocketObjectYMensaje;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteSocketStreamObject {

  private static final int PUERTO = 6000;
  private static final String SERVER = "localhost";

  public static void main(String[] args) throws ClassNotFoundException {
    try {
      Socket clientSocket = new Socket();
      InetSocketAddress addr = new InetSocketAddress(SERVER, PUERTO);
      clientSocket.connect(addr);

      // 1. Crear flujo de ENTRADA al servidor (recepción del OBJETO)
      ObjectInputStream flujoEntradaObjeto = new ObjectInputStream(clientSocket.getInputStream());
      // 2. RECEPCIÓN del OBJETO del servidor
      Integer numero = (Integer) flujoEntradaObjeto.readObject();
      System.out.println("CLIENTE: Recibiendo del SERVIDOR el numero: " + numero);

      // 3. Crear flujo de ENTRADA al servidor (recepción del mensaje)
      DataInputStream flujoEntrada = new DataInputStream(clientSocket.getInputStream());

      // 4. RECEPCIÓN del mensaje del servidor
      System.out.println("CLIENTE: Recibiendo del SERVIDOR: " + flujoEntrada.readUTF());

      // 5. Cierre de flujos de datos y conexión (en orden)
      flujoEntrada.close();
      flujoEntradaObjeto.close();
      clientSocket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
