package U2_Multihilo._0_Introduccion;

public class LanzaHilos {
    public static void main(String[] args) {
        System.out.println("MAIN - Voy a crear un hilo");
        MiHilo tarea = new MiHilo();
        System.out.println("MAIN - Voy a lanzar el hilo");
        tarea.start();
        System.out.println("MAIN - TERMINO");
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " - Soy el hilo principal");
        }
        System.out.println("MAIN - TERMINO 2");
    }
}
