package chat_room;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

	private static final int serverPort = 7000;
	private static final String host = "localhost";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket(host, serverPort);
			System.out.println("Da ket noi thanh cong toi Server!");

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
			try (Scanner scanner = new Scanner(System.in)) {
				// Khởi tạo Thread lắng nghe bằng cách gọi class riêng ClientListener
				ClientProcessing listener = new ClientProcessing(reader);
				Thread threadLangNghe = new Thread(listener);
				threadLangNghe.start(); // Bắt đầu chạy luồng lắng nghe

				// Luồng chính (Main Thread): Chuyên dùng để đọc chữ bạn gõ từ bàn phím rồi gửi
				// lên Server
				while (true) {
					if (scanner.hasNextLine()) {
						String msg = scanner.nextLine();
						writer.println(msg); // Gửi tin nhắn lên Server

						if (msg.equalsIgnoreCase("/exit")) {
							break; // Thoát chương trình client
						}
					}
				}
			}

			socket.close();
		} catch (Exception e) {
			System.out.println("Khong the ket noi den server. Hay chac chan Server da duoc chay!");
		}
	}

}
