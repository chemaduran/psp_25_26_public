package U1_Multiproceso._01_LanzaProcesos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanzaProcesos {
  private static final Logger logger = Logger.getLogger(LanzaProcesos.class.getName());

  public void ejecutar(String ruta) {
    ProcessBuilder pb;
    try {
      pb = new ProcessBuilder(ruta);
      pb.start();
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error executing process", e);
    }
  }

  public void ejecutarRunTime(String ruta) {
    String[] comandos = new String[2];
    comandos[0] = (ruta);
    comandos[1] = ("C:\\prueba\\ejercicio.pdf");
    try {
      Runtime rt = Runtime.getRuntime();
      rt.exec(comandos);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error executing runtime command", e);
    }
  }

  public void ejecutarComando(String comando) {
    String[] comandos = new String[4];
    comandos[0] = ("cmd");
    comandos[1] = ("/c");
    comandos[2] = (comando);
    comandos[3] = ("C:\\Users\\jgdur\\repos\\mm\\psp_24_25\\ficheros\\hola.txt");
    Process proc;
    StringBuilder resultado = new StringBuilder();
    try {
      Runtime rt = Runtime.getRuntime();
      proc = rt.exec(comandos);
      BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));

      String s = null;
      while ((s = br.readLine()) != null) {
        resultado.append(s); // Guarda el contenido del fichero en un StringBuilder
      }

      System.out.println(resultado);

    } catch (Exception e) {
      logger.log(Level.SEVERE, "Error executing command", e);
    }
  }

  public static void main(String[] args) {
    String rutaAdobe = "C:\\Program Files\\Adobe\\Acrobat DC\\Acrobat\\Acrobat.exe";
    String rutaNavegador = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    String rutaExplorador = "explorer.exe";

    LanzaProcesos lp = new LanzaProcesos();
//    lp.ejecutarRunTime(rutaExplorador);
    //    lp.ejecutarComando("more");
//    lp.ejecutar("C:\\Program Files\\Adobe\\Acrobat DC\\Acrobat\\Acrobat.exe");
    System.out.println("Finalizado");
  }
}
