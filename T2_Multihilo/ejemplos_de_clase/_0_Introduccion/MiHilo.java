package U2_Multihilo._0_Introduccion;

public class MiHilo extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " - Soy un hilo y esto es lo que hago");
        }
    }
}
