package chat_room;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TCPServer {

	private static final int serverPort = 7000;

	public static Map<String, List<RequestProcessing>> chatRooms = new HashMap<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		chatRooms.put("Study", new ArrayList<>());
		chatRooms.put("Game", new ArrayList<>());
		chatRooms.put("TraiNghiem", new ArrayList<>());

		try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
			System.out.println("Server dang chay va cho ket noi tai cong " + serverPort + "...");

			while (true) {

				Socket s = serverSocket.accept();
				System.out.println("Co 1 Client vua ket noi thanh cong!");

				RequestProcessing rp = new RequestProcessing(s);
				rp.start();
			}

		} catch (IOException e) {
			System.out.println("Loi Server: " + e.getMessage());
		}

	}

}
