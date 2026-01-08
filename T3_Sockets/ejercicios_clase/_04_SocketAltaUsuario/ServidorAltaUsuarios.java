package U3_Red.clase._04_SocketAltaUsuario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;

public class ServidorAltaUsuarios {

  private static final int PUERTO = 6000;
  private static final int LONGITUD_CONTRASENIA = 10; // Longitud máxima de

  /* FUNCIÓN DE GENERACIÓN DE CONTRASEÑA PARA LOS NUEVOS USUARIOS */
  public static String generarPassword() {
    char c;
    String password = "";

    for (int i = 0; i < LONGITUD_CONTRASENIA; i++) {
      c = (char) (aleatorio(65, 126));
      password += c;
    }

    return password;
  }

  private static int aleatorio(int min, int max) {
    Random r = new Random();
    return min + r.nextInt(max - min + 1); // Entre min y max -> [min,max];
  }

  public static void main(String[] args) throws ClassNotFoundException {
    try {

      Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

      for (NetworkInterface nif : Collections.list(nets)) {
        // do something with the network interface
        Enumeration<InetAddress> addressEnum = nif.getInetAddresses();
        if (addressEnum.hasMoreElements()) {
          InetAddress address = addressEnum.nextElement();
          System.out.println(
              nif.getName() + " - " + nif.getDisplayName() + " - " + address.getHostAddress());
        }
      }

      ServerSocket serverSocket = new ServerSocket(PUERTO);

      System.out.println("SERVIDOR: Escuchando por el puerto " + PUERTO + " ...");
      Socket clientSocket = serverSocket.accept();

      // 1. Crear flujo de SALIDA hacia el cliente (envío del OBJETO)
      ObjectOutputStream flujoSalida = new ObjectOutputStream(clientSocket.getOutputStream());

      // 2. ENVÍO del OBJETO hacia el cliente
      // Se crea un usuario con nuevo password
      Usuario user = new Usuario("paquito", generarPassword());
      System.out.println(
          "SERVIDOR: Un cliente se ha conectado y sus nuevas credenciales de acceso son: "
              + user.getUsername()
              + " - "
              + user.getPassword());
      flujoSalida.writeObject(user);

      // 3. Crear flujo de ENTRADA del cliente (recepción del OBJETO)
      ObjectInputStream flujoEntrada = new ObjectInputStream(clientSocket.getInputStream());

      // 4. RECEPCIÓN del OBJETO del cliente
      Usuario newUser = (Usuario) flujoEntrada.readObject();
      System.out.print(
          "SERVIDOR: NUEVAS CREDENCIALES DEL USUARIO: "
              + newUser.getUsername()
              + " - "
              + newUser.getPassword()
              + " - "
              + newUser.getIp());

      // 5. Cierre de flujos y conexión del cliente (en orden)
      flujoEntrada.close();
      flujoSalida.close();
      clientSocket.close();

      // 6. Cierre de conexión principal del servidor
      serverSocket.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
