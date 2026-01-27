package U3_Red.tareas.ex_24_25_hundir_la_flota;

import java.io.Serializable;

public class RespuestaJugada implements Serializable {
  private Resultado resultado;
  private boolean turno;
  private char[][] tableroOponente;

  public RespuestaJugada(Resultado resultado, boolean turno) {
    this.resultado = resultado;
    this.turno = turno;
  }

  public char[][] getTableroOponente() {
    return tableroOponente;
  }

  public void setTableroOponente(char[][] tableroOponente) {
    this.tableroOponente = tableroOponente;
  }

  public Resultado getResultado() {
    return resultado;
  }

  public boolean isTurno() {
    return turno;
  }

  public enum Resultado {
    INICIO_PARTIDA,
    AGUA,
    TOCADO,
    HUNDIDO,
    PARTIDA_TERMINADA
  }
}
