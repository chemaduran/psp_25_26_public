package U2_Multihilo._01_SeccionCritica;

class Contador implements Runnable {
  private int c = 0;

  // Nos paramos antes de incrementar, por gusto...
  public void increment() {
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
  public void decrement() {
    c--;
    System.out.println(
        Thread.currentThread().getName() + " -> Valor tras decrementar -> " + this.getValue());
  }

  // obtenemos el valor
  public int getValue() {
    return c;
  }

  @Override
  public void run() {
    this.increment();

    this.decrement();
  }
}
