package U2_Multihilo._02c_Productor_Consumidor_ABC;

public class H2 extends Thread {
  private BufferLetra buffer;
  private int nveces;

  public H2(BufferLetra b, int n, String nombreHilo) {
    this.buffer = b;
    this.nveces = n;
    this.setName(nombreHilo);
  }

  public void run() {
    for (int i = 0; i < nveces; i++) {
      // Se recoge la letra
      char letra = buffer.cogerLetra();

      if (letra == 0)
        System.out.println(
            this.getName()
                + ": no ha podido coger letra ya que no se había depositado aún la letra");
      else System.out.println(this.getName() + " ha cogido la letra: " + letra);
    }
  }
}
