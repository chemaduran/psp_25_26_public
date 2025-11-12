package U2_Multihilo._02b_ProductorConsumidorSincronizado;

import java.util.LinkedList;
import java.util.Queue;

public class BufferSincronizado {
	private final Queue<Integer> buffer;
	private final int capacidad;

	public BufferSincronizado(int capacidad) {
		this.buffer = new LinkedList<>();
		this.capacidad = capacidad;
	}

	public synchronized void producir(int valor) {
		while (buffer.size() == capacidad) {
			try {
				wait(); // Espera hasta que haya espacio
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		buffer.add(valor);
		System.out.println("Produciendo: " + valor + " | Tamaño buffer: " + buffer.size());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			for (StackTraceElement ste : e.getStackTrace()) {
				System.out.println(ste);
			}
		}
		notifyAll(); // Notifica al consumidor
	}

	public synchronized int consumir() {
		while (buffer.isEmpty()) {
			try {
				wait(); // Espera hasta que haya elementos
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		int valor = buffer.remove();
		System.out.println("Consumiendo: " + valor + " | Tamaño buffer: " + buffer.size());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			for (StackTraceElement ste : e.getStackTrace()) {
				System.out.println(ste);
			}
		}
		notifyAll(); // Notifica al productor
		return valor;
	}
}
