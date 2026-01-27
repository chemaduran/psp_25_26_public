package U3_Red.tareas.ex_24_25_hundir_la_flota;

import java.io.Serializable;

public class Posicion implements Serializable {
  private int fila;
  private int columna;

  public Posicion(int fila, int columna) {
    this.fila = fila;
    this.columna = columna;
  }

  public int getFila() {
    return fila;
  }

  public int getColumna() {
    return columna;
  }

  @Override
  public String toString() {
    return fila + ":" + columna;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Posicion p) {
      return this.fila == p.fila && this.columna == p.columna;
    }
    return false;
  }
}
