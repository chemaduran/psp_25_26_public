package U2_Multihilo._02b_ProductorConsumidorSincronizado;

public class ProductorSeguro implements Runnable {
  private final BufferSincronizado buffer;

  public ProductorSeguro(BufferSincronizado buffer) {
    this.buffer = buffer;
  }

  @Override
  public void run() {
    int valor = 0;
    while (true) {
      buffer.producir(valor++);
    }
  }
}
