package U1_Multiproceso._03_PadreHijo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Padre {
  public static void main(String[] args) {
    // Flujo de entrada para solicitar datos al usuario por teclado
    Scanner sc = new Scanner(System.in);

    try {
      // Introducción de datos (es necesario el control de errores)
      char opcion;

      do {
        Process p = ProcesoJava.exec(Hijo.class);

        // Se obtiene stdout del proceso hijo
        getSalidaProceso(p);

        // El proceso padre espera a que el hijo finalice
        int salida = p.waitFor();

        System.out.println("a ver que pinta " + p.getInputStream());
        System.out.println("Proceso hijo finalizado con valor: " + salida);
        System.out.println("Proceso padre finalizado");
        //        mostrarResultadoBuffer(p);

        // CONTROL DE NUEVA INSERCIÓN
        System.out.println("¿Desea lanzar más procesos (S/N)?: ");
        opcion = sc.nextLine().toUpperCase().charAt(0);
      } while (opcion == 'S');
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Función que obtiene la salida stdout del proceso hijo
   *
   * @param p Proceso lanzado
   * @throws IOException
   */
  private static void getSalidaProceso(Process p) throws IOException {
    String line;
    InputStream is = p.getInputStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(is));

    // Se muestra la salida del proceso por pantalla
    while ((line = br.readLine()) != null) {
      System.out.println(line);
    }

    // Cuando finaliza se cierra el descriptor del proceso
    is.close();
  }

  public static void mostrarResultadoBuffer(Process p) {
    // Creamos el flujo de lectura con el proceso
    BufferedReader leer = new BufferedReader(new InputStreamReader(p.getInputStream()));

    try {
      // Guardamos la primera línea
      String linea = leer.readLine();
      // Leemos las líneas y las mostramos por panatalla
      while (linea != null) {
        System.out.println(linea);
        linea = leer.readLine();
      }
    } catch (IOException e) {
      // Controlamos el error por si hay error en el flujo de lectura
      System.out.println("Error en el flujo de lectura");
      // e.printStackTrace();
    }
  }
}
