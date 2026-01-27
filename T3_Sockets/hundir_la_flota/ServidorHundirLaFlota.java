package U3_Red.tareas.ex_24_25_hundir_la_flota;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ServidorHundirLaFlota {
  private static final int PUERTO = 8282;
  private static int contadorClientes = 0;
  private static int idPartida = 0;
  private static ManejadorPartida nuevaPartida;
  private static ConcurrentHashMap<Integer, ManejadorPartida> partidas = new ConcurrentHashMap<>();

  public static void main(String[] args) {
    try (ServerSocket servidor = new ServerSocket(PUERTO)) {
      System.out.println("Servidor en ejecución...");
      ClienteHandler jugadorA = null;
      ClienteHandler jugadorB = null;
      while (true) {
        Socket socket = servidor.accept();

        // A cada cliente que se conecta se le asigna un hilo para manejarlo, con el recurso
        // compartido de la partida
        if (contadorClientes % 2 == 0) {
          idPartida++;
          System.out.println(
              "Partida: " + idPartida + ". Conectado jugador A. Esperando al jugador B...");
          nuevaPartida = new ManejadorPartida(idPartida);
          jugadorA = new ClienteHandler(socket, nuevaPartida, ClientIdentification.Jugador.A);
        } else {
          System.out.println("Conectado jugador B. Comenzando partida...");
          jugadorB = new ClienteHandler(socket, nuevaPartida, ClientIdentification.Jugador.B);
          nuevaPartida.setJugadores(jugadorA, jugadorB);
          new Thread(jugadorB).start();
          new Thread(jugadorA).start();
          partidas.put(idPartida, nuevaPartida);
        }
        contadorClientes++;
      }
    } catch (IOException e) {
      System.err.println("Error al abrir el socket del servidor.");
    }
  }
}
