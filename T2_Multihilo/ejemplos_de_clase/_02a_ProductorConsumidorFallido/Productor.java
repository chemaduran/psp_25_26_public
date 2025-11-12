package U2_Multihilo._02a_ProductorConsumidorFallido;

import java.util.Queue;

class Productor implements Runnable {
	private final Queue<Integer> buffer;
	private final int capacidad;

	public Productor(Queue<Integer> buffer, int capacidad) {
		this.buffer = buffer;
		this.capacidad = capacidad;
	}

	@Override
	public void run() {
		int valor = 0;
		while (true) {
			if (buffer.size() < capacidad) {
				buffer.add(valor++);
				System.out.println("Produciendo: " + valor + " | Tamaño buffer: " + (buffer.size()));
			}
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				for (StackTraceElement ste : e.getStackTrace()) {
//					System.out.println(ste);
//				}
//			}
			// Aquí no hay sincronización, por lo que puede haber problemas de concurrencia
		}
	}
}
