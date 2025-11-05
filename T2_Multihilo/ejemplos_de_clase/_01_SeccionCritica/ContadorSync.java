package U2_Multihilo._01_SeccionCritica;

class ContadorSync implements Runnable {
  private int c = 0;

  // Nos paramos antes de incrementar, por gusto...
  public synchronized void increment() {
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    c++;
    System.out.println(
        Thread.currentThread().getName() + " -> Valor tras incrementar -> " + this.getValue());
  }

  // Decrementamos
  public synchronized void decrement() {
    c--;
    System.out.println(
        Thread.currentThread().getName() + " -> Valor tras decrementar -> " + this.getValue());
  }

  // obtenemos el valor
  public synchronized int getValue() {
    return c;
  }

  @Override
  public void run() {
    this.increment();

    this.decrement();
  }
}
