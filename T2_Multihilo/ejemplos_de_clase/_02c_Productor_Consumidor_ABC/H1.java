package U2_Multihilo._02c_Productor_Consumidor_ABC;

import java.util.Random;

public class H1 extends Thread {
  private BufferLetra buffer;
  private int nveces;

  public H1(BufferLetra b, int n) {
    this.buffer = b;
    this.nveces = n;
  }

  public void run() {
    for (int i = 0; i < nveces; i++) {
      // Se genera una letra (en mayúsculas)
      int letraASCII = aleatorio(65, 90);

      // Se deja la letra calculada
      buffer.dejarLetra((char) letraASCII);
      System.out.println("H1: Letra " + (char) letraASCII + " depositada");
    }
  }

  private int aleatorio(int min, int max) {
    Random r = new Random();
    return min + r.nextInt(max - min + 1); // Entre min y max -> [min,max];
  }
}
