package U3_Red.tareas.ex_24_25_hundir_la_flota;

import java.io.Serializable;

public class ClientIdentification implements Serializable {
  private long idCliente;
  private int idPartida;
  private Tablero tablero;
  private Jugador jugador;

  // Constructor, Getters y Setters
  ClientIdentification() {
    this.idPartida = 0;
    this.tablero = new Tablero();
  }

  public Jugador getJugador() {
    return jugador;
  }

  public void setJugador(Jugador jugador) {
    this.jugador = jugador;
    tablero.setJugador(jugador);
  }

  public Tablero getTablero() {
    return tablero;
  }

  public void setTablero(Tablero tablero) {
    this.tablero = tablero;
  }

  public long getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(long idCliente) {
    this.idCliente = idCliente;
  }

  public int getIdPartida() {
    return idPartida;
  }

  public void setIdPartida(int idPartida) {
    this.idPartida = idPartida;
  }

  public enum Jugador {
    A,
    B
  }
}
