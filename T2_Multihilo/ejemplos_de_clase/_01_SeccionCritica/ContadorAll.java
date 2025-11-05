package U2_Multihilo._01_SeccionCritica;

import java.util.concurrent.atomic.AtomicInteger;

public class ContadorAll implements Runnable {
  private AtomicInteger atomic = new AtomicInteger(0);
  private int synchronizedCounter = 0;
  private int counter = 0;

  public void incrementAtomicCounter() {
    try {
      Thread.sleep(10);
      atomic.getAndIncrement();
      System.out.println(
          Thread.currentThread().getName()
              + " -> Valor tras incrementar Atomic -> "
              + this.getAtomicCounter());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void decrementAtomicCounter() {
    atomic.getAndDecrement();
    System.out.println(
        Thread.currentThread().getName()
            + " -> Valor tras decrementar Atomic -> "
            + this.getAtomicCounter());
  }

  public int getAtomicCounter() {
    return atomic.get();
  }

  public synchronized void incrementSynchronizedCounter() {
    try {
      Thread.sleep(10);
      synchronizedCounter++;
      System.out.println(
          Thread.currentThread().getName()
              + " -> Valor tras incrementar Synchronized -> "
              + this.getSynchronizedCounter());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized void decrementSynchronizedCounter() {
    synchronizedCounter--;
    System.out.println(
        Thread.currentThread().getName()
            + " -> Valor tras decrementar Synchronized -> "
            + this.getSynchronizedCounter());
  }

  public int getSynchronizedCounter() {
    return synchronizedCounter;
  }

  public void incrementCounter() {
    try {
      Thread.sleep(10);
      counter++;
      System.out.println(
          Thread.currentThread().getName()
              + " -> Valor tras incrementar Sin proteccíon -> "
              + this.getCounter());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public void decrementCounter() {
    counter--;
    System.out.println(
        Thread.currentThread().getName()
            + " -> Valor tras decrementar Sin protección -> "
            + this.getCounter());
  }

  public int getCounter() {
    return counter;
  }

  @Override
  public void run() {
    this.incrementCounter();

    this.decrementCounter();

    this.incrementAtomicCounter();

    this.decrementAtomicCounter();

    this.incrementSynchronizedCounter();

    this.decrementSynchronizedCounter();
  }
}
