package U3_Red.tareas.ex_24_25_hundir_la_flota;

import java.util.HashMap;

public class ManejadorPartida {
  private int idPartida;
  private HashMap<ClienteHandler, ClienteHandler> oponente;

  public ManejadorPartida(int idPartida) {
    this.idPartida = idPartida;
    this.oponente = new HashMap<>();
  }

  public void setJugadores(ClienteHandler jugadorA, ClienteHandler jugadorB) {
    oponente.put(jugadorA, jugadorB);
    oponente.put(jugadorB, jugadorA);
  }

  public ClienteHandler getOponente(ClienteHandler jugador) {
    return oponente.get(jugador);
  }

  public synchronized RespuestaJugada.Resultado realizarAtaque(Tablero tablero, Posicion jugada) {
    RespuestaJugada.Resultado resultado;
    if (jugada.getFila() < 0
        || jugada.getFila() >= 10
        || jugada.getColumna() < 0
        || jugada.getColumna() >= 10) {
      resultado = RespuestaJugada.Resultado.AGUA;
    } else {
      resultado = tablero.checkPosicion(jugada);
    }
    return resultado;
  }
}
