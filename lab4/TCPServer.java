package socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCPServer {
	
	private static final int PORT = 6000;
	private static final String[] DIGIT_WORDS = {
	        "Không", "Một", "Hai", "Ba", "Bốn", 
	        "Năm", "Sáu", "Bảy", "Tám", "Chín"
	    };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (ServerSocket server = new ServerSocket(PORT)) {
			System.out.println("TCP server listening on port " + PORT);
			while (true) {
				try (Socket socket = server.accept()) {
					serve(socket);
				} catch (IOException e) {
					System.err.println("Lỗi phiên client: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			System.err.println("Không mở được server: " + e.getMessage());
		}
	}
	
	private static void serve(Socket socket) throws IOException {
		// TODO Auto-generated method stub
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
				PrintWriter out = new PrintWriter(
						new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
			String request;
			while ((request = in.readLine()) != null) {
				String response = process(request);
				out.println(response);
				if (request.equalsIgnoreCase("QUIT"))
					break;
			}
		}
	}
	
	private static String process(String request) {
		// TODO Auto-generated method stub
		String trimmed = request.trim();
		if (trimmed.equalsIgnoreCase("QUIT")) {
            return "OK BYE";
        }

        if (trimmed.length() == 1 && Character.isDigit(trimmed.charAt(0))) {
            int digit = trimmed.charAt(0) - '0';
            return "OK " + DIGIT_WORDS[digit];
        }

        return "ERR INVALID_DIGIT";
	}

}
