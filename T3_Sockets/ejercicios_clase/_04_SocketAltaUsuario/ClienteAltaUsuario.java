package U3_Red.clase._04_SocketAltaUsuario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAltaUsuario {

  private static final int PUERTO = 6000;
  private static final String SERVER = "localhost";

  public static void main(String[] args) throws ClassNotFoundException {
    try {
      // Flujo de entrada para solicitar la nueva contraseña de usuario
      // por teclado
      Scanner sc = new Scanner(System.in);

      Socket clientSocket = new Socket();
      InetSocketAddress addr = new InetSocketAddress(SERVER, PUERTO);
      clientSocket.connect(addr);

      // 1. Crear flujo de ENTRADA al servidor (recepción del OBJETO)
      ObjectInputStream flujoEntrada = new ObjectInputStream(clientSocket.getInputStream());

      // 2. RECEPCIÓN del OBJETO del servidor
      Usuario newUser = (Usuario) flujoEntrada.readObject();
      System.out.println(
          "CLIENTE: Recibiendo del SERVIDOR las nuevas credenciales de acceso: "
              + newUser.getUsername()
              + " - "
              + newUser.getPassword());

      // 3. Crear flujo de SALIDA hacia el servidor (envío del OBJETO)
      ObjectOutputStream flujoSalida = new ObjectOutputStream(clientSocket.getOutputStream());

      // 4. ENVÍO del OBJETO hacia el servidor
      System.out.println(
          "Introduce la nueva contraseña del usuario " + newUser.getUsername() + ":");
      String newPassword = sc.nextLine();
      // Se modifican los valores del objeto
      newUser.setPassword(newPassword);
      newUser.setIp(InetAddress.getLocalHost().getHostAddress());
      // Se escribe el objeto
      flujoSalida.writeObject(newUser);

      // 5. Cierre de flujos de datos y conexión (en orden)
      sc.close();
      flujoSalida.close();
      flujoEntrada.close();
      clientSocket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
