package U3_Red.clase._05_TCPMultihilo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

  private static final int PUERTO = 6000;
  private static final String SERVER = "localhost";

  public static void main(String[] args) {
    try {
      Socket clientSocket = new Socket();
      InetSocketAddress addr = new InetSocketAddress(SERVER, PUERTO);
      clientSocket.connect(addr);

      // 1. Crear flujo de ENTRADA al servidor (recepción del mensaje)
      DataInputStream flujoEntrada = new DataInputStream(clientSocket.getInputStream());

      // 2. RECEPCION del mensaje del servidor
      System.out.println("CLIENTE: Recibiendo del SERVIDOR: " + flujoEntrada.readUTF());

      Scanner sc = new Scanner(System.in);
      System.out.print("Introduce algún dato:");
      sc.nextInt();

      // 3. Cierre de flujos de datos y conexión (en orden)
      flujoEntrada.close();
      clientSocket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
