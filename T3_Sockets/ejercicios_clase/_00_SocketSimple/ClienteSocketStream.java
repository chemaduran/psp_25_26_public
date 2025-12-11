package U3_Red.clase._00_SocketSimple;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteSocketStream {

	private static final int PUERTO = 6000;
	private static final String SERVER = "172.22.160.100";

	public static void main(String[] args) {
		try {
			// 1. Creación del socket cliente
			Socket clientSocket = new Socket();

			// 2. Asignación de la dirección y puerto (BIND)
			InetSocketAddress addr = new InetSocketAddress(SERVER, PUERTO);

			// 3. Conexión al socket servidor
			// Tiene que haber un ServerSocket escuchando en ese puerto
			clientSocket.connect(addr);

			// 4. Flujo de salida hacia el servidor (envío del mensaje)
			DataOutputStream flujoSalida = new DataOutputStream(clientSocket.getOutputStream());
			String mensaje =
							"Hola soy el CLIENTE con dirección " + InetAddress.getLocalHost().getHostAddress();
			flujoSalida.writeUTF(mensaje);

			// 5. Cierre de flujos de datos
			flujoSalida.close();

			// 6. Cierre de conexiones
			clientSocket.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
