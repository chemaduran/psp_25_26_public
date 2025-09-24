package U1_Multiproceso._00_ArgumentosEntrada;

public class ArgumentosEntrada {
  public static void main(String[] args) {
    System.out.println("Ha introducido: " + args.length + " argumentos");
    int i = 1;
    for (String argumento : args) {
      System.out.println("Argumento " + i++ + " es:" + argumento);
    }

    int max = Math.max(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    max = Math.max(max, Integer.parseInt(args[2]));

    System.out.println("El máximo es: " + max);
  }
}
