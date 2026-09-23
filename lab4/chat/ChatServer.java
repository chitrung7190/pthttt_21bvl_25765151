package chat;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	
	private static final int PORT = 5000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server da duoc tao");
			while(true) {
				try {
					Socket socket = serverSocket.accept();
					RequestProcessing rp = new RequestProcessing(socket);
					rp.start();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("Connection error " + e);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Server creation error: " + e);
		}
	}

}
