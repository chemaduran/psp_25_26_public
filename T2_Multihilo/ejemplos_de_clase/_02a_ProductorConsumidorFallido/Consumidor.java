package U2_Multihilo._02a_ProductorConsumidorFallido;

import java.util.Queue;

class Consumidor implements Runnable {
	private final Queue<Integer> buffer;

	public Consumidor(Queue<Integer> buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		while (true) {
			if (!buffer.isEmpty()) {
				int valor = buffer.remove();
				System.out.println("Consumiendo: " + valor + " | Tamaño buffer: " + buffer.size());
			}
			// Aquí tampoco hay sincronización
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				for (StackTraceElement ste : e.getStackTrace()) {
//					System.out.println(ste);
//				}
//			}
		}
	}
}
