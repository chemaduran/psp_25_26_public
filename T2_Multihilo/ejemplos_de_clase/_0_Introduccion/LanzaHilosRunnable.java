package U2_Multihilo._0_Introduccion;

public class LanzaHilosRunnable {
    public static void main(String[] args) {
        MiHiloRunnable tarea = new MiHiloRunnable();
        Thread hilo = new Thread(tarea);
        hilo.start();
    }

}
