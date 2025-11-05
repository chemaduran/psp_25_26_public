package U2_Multihilo._01_SeccionCritica;

import java.util.concurrent.atomic.AtomicInteger;

class ContadorAtomic implements Runnable {
    private AtomicInteger c = new AtomicInteger(0);

    // Nos paramos antes de incrementar, por gusto...
    public void increment() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(
                Thread.currentThread().getName()
                        + " -> ANTES Valor tras incrementar -> "
                        + this.getValue());
        c.incrementAndGet();

        System.out.println(
                Thread.currentThread().getName()
                        + " -> DESPUÉS Valor tras incrementar -> "
                        + this.getValue());
    }

    // Decrementamos
    public void decrement() {
        System.out.println(
                Thread.currentThread().getName()
                        + " -> ANTES Valor tras decrementar -> "
                        + this.getValue());
        c.decrementAndGet();

        System.out.println(
                Thread.currentThread().getName()
                        + " -> DESPUÉS Valor tras decrementar -> "
                        + this.getValue());
    }

    // obtenemos el valor
    public int getValue() {
        return c.get();
    }

    @Override
    public void run() {
        this.increment();

        this.decrement();
    }
}
