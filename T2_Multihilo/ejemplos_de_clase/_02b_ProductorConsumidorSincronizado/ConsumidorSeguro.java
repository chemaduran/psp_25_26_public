package U2_Multihilo._02b_ProductorConsumidorSincronizado;

class ConsumidorSeguro implements Runnable {
  private final BufferSincronizado buffer;

  public ConsumidorSeguro(BufferSincronizado buffer) {
    this.buffer = buffer;
  }

  @Override
  public void run() {
    while (true) {
      buffer.consumir();
    }
  }
}
