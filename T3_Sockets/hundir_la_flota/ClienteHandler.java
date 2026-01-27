package U3_Red.tareas.ex_24_25_hundir_la_flota;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

public class ClienteHandler implements Runnable {
  private static AtomicLong numClient;
  private Socket socket;
  private ManejadorPartida partida;
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private ClientIdentification clientID;
  private ClientIdentification.Jugador jugador;

  public ClienteHandler(
      Socket socket, ManejadorPartida partida, ClientIdentification.Jugador jugador) {
    this.socket = socket;
    this.partida = partida;
    this.jugador = jugador;
    numClient = new AtomicLong(0);
  }

  public ClientIdentification getClientID() {
    return clientID;
  }

  public void setClientID(ClientIdentification clientID) {
    this.clientID = clientID;
  }

  @Override
  public void run() {
    try {
      out = new ObjectOutputStream(socket.getOutputStream());
      in = new ObjectInputStream(socket.getInputStream());

      ClientIdentification cliente = (ClientIdentification) in.readObject();
      cliente.setIdCliente(numClient.addAndGet(1));
      cliente.setJugador(jugador);
      clientID = cliente; // Save the clientID for later use
      cliente.setIdPartida(cliente.getIdPartida());
      System.out.println("Soy " + jugador + " - ID del cliente: " + cliente.getIdCliente());
      System.out.println("ID de la partida: " + cliente.getIdPartida());
      out.writeObject(cliente);
      out.flush();

      RespuestaJugada respuesta =
          new RespuestaJugada(
              RespuestaJugada.Resultado.INICIO_PARTIDA, cliente.getIdCliente() % 2 == 0);
      out.reset();
      out.writeObject(respuesta);
      out.flush();

      while (respuesta.getResultado() != RespuestaJugada.Resultado.PARTIDA_TERMINADA) {
        Posicion jugada = (Posicion) in.readObject();
        ClienteHandler oponente = partida.getOponente(this);

        RespuestaJugada.Resultado resultadoAtaque =
            partida.realizarAtaque(oponente.getClientID().getTablero(), jugada);
        System.out.println("Jugador " + jugador + " ha atacado en " + jugada);
        System.out.println("Resultado del ataque: " + resultadoAtaque);
        System.out.println("Tablero del oponente:");
        Tablero.imprimirTablero(oponente.getClientID().getTablero().getTablero());

        // Avisamos al cliente que ha realizado el ataque con el resultado
        respuesta = new RespuestaJugada(resultadoAtaque, false);
        respuesta.setTableroOponente(oponente.getClientID().getTablero().getTableroBlanco());
        sendRespuesta(respuesta);

        // Hay que avisar también al oponente del resultado del ataque, con su turno
        respuesta = new RespuestaJugada(resultadoAtaque, true);
        oponente.sendRespuesta(respuesta);
      }
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error en el cliente: " + e.getMessage());
    } finally {
      try {
        socket.close();
      } catch (IOException e) {
        System.err.println("Error al cerrar el socket: " + e.getMessage());
      }
    }
  }

  public void sendRespuesta(RespuestaJugada respuesta) {
    try {
      out.reset();
      out.writeObject(respuesta);
      out.flush();
    } catch (IOException e) {
      System.err.println("Error al enviar la respuesta: " + e.getMessage());
    }
  }
}
