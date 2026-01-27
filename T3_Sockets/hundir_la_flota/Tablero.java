package U3_Red.tareas.ex_24_25_hundir_la_flota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tablero implements Serializable {
  private static final Random rand = new Random();
  private List<Barco> barcos;
  private char[][] tablero;
  private char[][] tableroBlanco;
  private int TAMANIO = 11;
  private char AGUA = '.';
  private char BARCO = 'B';
  private ClientIdentification.Jugador jugador;

  public Tablero() {
    this.barcos = new ArrayList<>();
    this.tablero = inicializarTablero();
    this.tableroBlanco = inicializarTablero();
    colocarBarcosAleatoriamente();
  }

  public static void imprimirTablero(char[][] tablero) {
    if (tablero == null) {
      System.out.println("Tablero vacío");
      return;
    }
    for (char[] chars : tablero) {
      for (char aChar : chars) {
        System.out.print(aChar + " ");
      }
      System.out.println();
    }
  }

  public void setJugador(ClientIdentification.Jugador jugador) {
    this.jugador = jugador;
  }

  private char[][] inicializarTablero() {
    char[][] resultado = new char[TAMANIO][TAMANIO];
    for (int i = 0; i < TAMANIO; i++) {
      for (int j = 0; j < TAMANIO; j++) {
        resultado[i][j] = AGUA;
      }
    }
    return resultado;
  }

  public char[][] getTablero() {
    return tablero;
  }

  public void setTablero(char[][] tablero) {
    this.tablero = tablero;
  }

  public char[][] getTableroBlanco() {
    return tableroBlanco;
  }

  private void setAtaqueEnTablero(Posicion posicion, char ataque) {
    tableroBlanco[posicion.getFila()][posicion.getColumna()] = ataque;
  }

  public RespuestaJugada.Resultado checkPosicion(Posicion posicion) {
    RespuestaJugada.Resultado resultado = RespuestaJugada.Resultado.AGUA;
    System.out.println(
        "Comprobando posición "
            + posicion
            + " en tablero del jugador "
            + jugador
            + ". Barcos restantes: "
            + barcos.size());
    for (Barco barco : barcos) {
      if (barco.isInPosition(posicion)) {
        resultado = barco.checkPosicion(posicion);
        if (resultado == RespuestaJugada.Resultado.HUNDIDO) { // Borrar barco de la lista de barcos
          barcos.remove(barco);
          setAtaqueEnTablero(posicion, 'X');
        }
        if (resultado == RespuestaJugada.Resultado.TOCADO) {
          setAtaqueEnTablero(posicion, 'X');
        }

        if (barcos.isEmpty()) {
          resultado = RespuestaJugada.Resultado.PARTIDA_TERMINADA;
        }
        break;
      }
    }

    if (resultado == RespuestaJugada.Resultado.AGUA) {
      setAtaqueEnTablero(posicion, 'A');
    }
    return resultado;
  }

  public boolean colocarBarco(
      char[][] tablero, int fila, int columna, Barco barco, boolean horizontal) {
    if (horizontal) {
      if (columna + barco.getTamano() > TAMANIO) return false; // Verificar límites
      for (int i = 0; i < barco.getTamano(); i++) {
        if (tablero[fila][columna + i] != AGUA) return false; // Verificar superposición
      }
      for (int i = 0; i < barco.getTamano(); i++) {
        tablero[fila][columna + i] = BARCO;
        Posicion posicion = new Posicion(fila, columna + i);
        barco.addPosicion(posicion);
      }
    } else {
      if (fila + barco.getTamano() > TAMANIO) return false;
      for (int i = 0; i < barco.getTamano(); i++) {
        if (tablero[fila + i][columna] != AGUA) return false;
      }
      for (int i = 0; i < barco.getTamano(); i++) {
        tablero[fila + i][columna] = BARCO;
        Posicion posicion = new Posicion(fila + i, columna);
        barco.addPosicion(posicion);
      }
    }
    return true;
  }

  public void colocarBarcosAleatoriamente() {
    Barco[] barcos = {
      new Barco(Barco.TipoBarco.PORTAAVIONES),
      new Barco(Barco.TipoBarco.ACORAZADO),
      new Barco(Barco.TipoBarco.SUBMARINO),
      new Barco(Barco.TipoBarco.SUBMARINO),
      new Barco(Barco.TipoBarco.DESTRUCTOR),
      new Barco(Barco.TipoBarco.DESTRUCTOR),
      new Barco(Barco.TipoBarco.FRAGATA)
    };

    for (Barco barco : barcos) {
      boolean colocado = false;
      while (!colocado) {
        int fila = rand.nextInt(TAMANIO);
        int columna = rand.nextInt(TAMANIO);
        boolean horizontal = rand.nextBoolean();
        colocado = colocarBarco(tablero, fila, columna, barco, horizontal);
      }
    }
    this.barcos = List.of(barcos);
  }
}
