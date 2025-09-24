package U1_Multiproceso._02_EjecutandoConsola;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class EjecutaConsola {
  private static final Logger logger = Logger.getLogger(EjecutaConsola.class.getName());

  public static void main(String[] args) {
    // Con "/C" le indicamos que se cierre el cmd cuando acabe
    // Con "/K" le indicamos que se mantenga abierto el cmd
    // ProcessBuilder pb = new ProcessBuilder ("cmd", "/K");
    ProcessBuilder pb = new ProcessBuilder("cmd", "/C", "dir", "c:\\");
    Process p = null;
    try {
      p = pb.start();
      p.waitFor();
      mostrarResultado(p);
//       mostrarResultadoBuffer(p);
       mostrarErrores(p);
    } catch (Exception e) {
      System.out.println("Error al ejecutar el proceso");
      if (p != null) {
        mostrarErrores(p);
      }
    }
  }

  public static void mostrarResultado(Process p) {
    InputStream is = p.getInputStream();
    int letra = 0;
    try {
      while ((letra = is.read()) != -1) {
        System.out.print((char) letra);
      }
    } catch (IOException ex) {
      System.out.println("Error al leer los datos del proceso");
    }
  }

  public static void mostrarResultadoBuffer(Process p) throws UnsupportedEncodingException {
    InputStream is = p.getInputStream();
    InputStreamReader ir = new InputStreamReader(is, "Cp850");
    BufferedReader buffer = new BufferedReader(ir);
    String linea;
    try {
      while ((linea = buffer.readLine()) != null) {
        System.out.println(linea);
      }
    } catch (IOException ex) {
      System.out.println("Error leyendo el buffer");
    }
  }

  public static void mostrarErrores(Process p) {
    System.out.println("Entramos en mostrar errores");
    InputStream is = p.getErrorStream();
    int letra = 0;
    try {
      while ((letra = is.read()) != -1) {
        System.out.print((char) letra);
      }
    } catch (IOException ex) {
      System.out.println("Error al leer los datos del proceso");
    }
  }
}
