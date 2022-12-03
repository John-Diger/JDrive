import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class JDriveServer {
	static void listen() {
		try {
			DatagramSocket socket = new DatagramSocket(8080);
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			byte[] buffer = byteStream.toByteArray();
			InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 3000);
			while (true) {

				socket.send(packet);
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
