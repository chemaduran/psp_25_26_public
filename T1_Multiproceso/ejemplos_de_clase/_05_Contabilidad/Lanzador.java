package U1_Multiproceso._05_Contabilidad;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Lanzador {
  private static final String SUFIJO_RESULTADO = ".res";
  private static final String SUFIJO_ERRORES = "_errores.txt";
  private static final String RESULTADOS_GLOBALES = "resultados.txt";

  public static void main(String[] args) throws IOException {
    String classpath = System.getProperty("java.class.path");
    String[] ficheros = {
      "informatica.txt", "gerencia.txt", "contabilidad.txt", "comercio.txt", "rrhh.txt"
    };
    // Los nombres de los ficheros de resultados
    // se generarán y luego se almacenarán aquí
    String[] ficherosResultado;
    ficherosResultado = new String[ficheros.length];
    /* Lanzamos los procesos*/
    ProcessBuilder[] constructores;
    constructores = new ProcessBuilder[ficheros.length];
    for (int i = 0; i < ficheros.length; i++) {
      String fichResultado, fichErrores;
      fichResultado = ficheros[i] + SUFIJO_RESULTADO;
      fichErrores = ficheros[i] + SUFIJO_ERRORES;
      ficherosResultado[i] = fichResultado;
      constructores[i] = new ProcessBuilder();
      constructores[i].command(
          "java",
          "-cp",
          classpath,
          "U1_Multiproceso._05_Contabilidad.ProcesadorContabilidad",
          ficheros[i],
          fichResultado);
      // El fichero de errores se generará, aunque
      // puede que vacío
      constructores[i].redirectError(new File(fichErrores));
      constructores[i].start();
      // Fin del for que recorre los ficheros
    }
    // Calculamos las sumas de cantidades
    long total = UtilidadesFicheros.getSuma(ficherosResultado);
    // Y las almacenamos
    PrintWriter pw = UtilidadesFicheros.getPrintWriter(RESULTADOS_GLOBALES);
    pw.println(total);
    pw.close();
    // Fin del main
  }
}
