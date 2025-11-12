package U2_Multihilo._02b_ProductorConsumidorSincronizado;

public class PCSincronizado {
	public static void main(String[] args) {
		BufferSincronizado buffer = new BufferSincronizado(5);

		final int NUM_PRODUCTORES = 1;
		final int NUM_CONSUMIDORES = 1;

		Thread[] productores = new Thread[NUM_PRODUCTORES];
		Thread[] consumidores = new Thread[NUM_CONSUMIDORES];

		for (int i = 0; i < productores.length; i++) {
			productores[i] = new Thread(new ProductorSeguro(buffer));
			productores[i].start();
		}

		for (int i = 0; i < consumidores.length; i++) {
			consumidores[i] = new Thread(new ConsumidorSeguro(buffer));
			consumidores[i].start();
		}
	}
}
