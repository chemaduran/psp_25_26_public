package U1_Multiproceso._04_CuentaVocales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import static U1_Multiproceso._04_CuentaVocales.UtilidadesFicheros.getLineasFichero;

public class ProcesadorFichero {
  private String nombreFichero;

  public ProcesadorFichero(String nombreFichero) {
    this.nombreFichero = nombreFichero;
  }

  public int hacerRecuento(String letra) {
    int recuento = 0;
    try {
      ArrayList<String> lineas = getLineasFichero(nombreFichero);
      for (String linea : lineas) {
        for (int i = 0; i < linea.length(); i++) {
          if (linea.charAt(i) == letra.charAt(0)) {
            recuento++;
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Error al abrir el fichero");
    }
    return recuento;
  }

  public void escribirResultado(String nombreFicheroResultado, int recuento) {
    try {
      PrintWriter printWriter = UtilidadesFicheros.getPrintWriter(nombreFicheroResultado);
      printWriter.println(recuento);
      printWriter.close();
    } catch (Exception e) {
      System.out.println("Error al abrir el fichero");
    }
  }
}
