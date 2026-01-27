package U3_Red.tareas.ex_24_25_hundir_la_flota;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteHundirLaFlota {
	private static ObjectOutputStream out;
	private static ObjectInputStream in;
	private static Scanner scanner;

	public static void main(String[] args) {

		try (Socket socket = new Socket("localhost", 8282);
		     ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		     ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		     Scanner scanner = new Scanner(System.in)) {

			ClienteHundirLaFlota.out = out;
			ClienteHundirLaFlota.in = in;
			ClienteHundirLaFlota.scanner = scanner;

			ClientIdentification cliente = new ClientIdentification();
			out.reset();
			out.writeObject(cliente);
			out.flush();

			// Wait for the server to respond with the clientID
			cliente = (ClientIdentification) in.readObject();
			System.out.println("ID del cliente: " + cliente.getIdCliente());
			System.out.println("ID de la partida: " + cliente.getIdPartida());
			System.out.println("Soy el jugador: " + cliente.getJugador());

			// Esperamos la respuesta del servidor para saber si es nuestro turno
			RespuestaJugada respuesta = (RespuestaJugada) in.readObject();

			while (respuesta.getResultado() != RespuestaJugada.Resultado.PARTIDA_TERMINADA) {
				if (respuesta.isTurno()) {
					realizarJugada();
				} else {
					System.out.println("Esperando al otro jugador...");
				}
				respuesta = (RespuestaJugada) in.readObject();
				if (!respuesta.isTurno()) {
					System.out.println("Resultado del ataque: " + respuesta.getResultado());
					Tablero.imprimirTablero(respuesta.getTableroOponente());
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error en el cliente: " + e.getMessage());
		}
	}

	public static void realizarJugada() throws IOException {
		System.out.print("Es tu turno. \nIngresa fila: ");
		int fila = scanner.nextInt();
		System.out.print("Ingresa columna: ");
		int columna = scanner.nextInt();
		Posicion jugada = new Posicion(fila, columna);
		ClienteHundirLaFlota.out.reset();
		ClienteHundirLaFlota.out.writeObject(jugada);
		ClienteHundirLaFlota.out.flush();
	}
}
