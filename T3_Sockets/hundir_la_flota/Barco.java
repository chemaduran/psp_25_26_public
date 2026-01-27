package U3_Red.tareas.ex_24_25_hundir_la_flota;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Barco implements Serializable {
  private TipoBarco tipo;
  private int tamano;
  private int vida;
  private List<Posicion> posiciones;

  public Barco(TipoBarco tipo) {
    posiciones = new ArrayList<>();
    this.tipo = tipo;
    switch (tipo) {
      case PORTAAVIONES -> tamano = 5;
      case ACORAZADO -> tamano = 4;
      case SUBMARINO, DESTRUCTOR -> tamano = 3;
      case FRAGATA -> tamano = 2;
    }
    vida = tamano;
  }

  public int getTamano() {
    return tamano;
  }

  public boolean estaHundido() {
    return vida == 0;
  }

  public void addPosicion(Posicion posicion) {
    posiciones.add(posicion);
  }

  public boolean isInPosition(Posicion posicion) {
    for (Posicion p : posiciones) {
      System.out.println(
          "Soy el barco " + tipo + " y estoy en la posición " + p + " y busco " + posicion);
      if (p.equals(posicion)) {
        return true;
      }
    }
    return false;
  }

  // Dada una posición, comprueba si el barco recibe impacto
  public RespuestaJugada.Resultado checkPosicion(Posicion posicion) {
    for (Posicion p : posiciones) {
      if (p.equals(posicion)) {
        vida--;
        if (estaHundido()) {
          return RespuestaJugada.Resultado.HUNDIDO;
        } else {
          return RespuestaJugada.Resultado.TOCADO;
        }
      }
    }
    return RespuestaJugada.Resultado.AGUA;
  }

  public enum TipoBarco {
    PORTAAVIONES,
    ACORAZADO,
    SUBMARINO,
    DESTRUCTOR,
    FRAGATA
  }
}
