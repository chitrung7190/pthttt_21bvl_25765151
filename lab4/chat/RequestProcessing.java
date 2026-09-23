package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class RequestProcessing extends Thread {
	Socket channel;
	public RequestProcessing(Socket s) {
		// TODO Auto-generated constructor stub
		channel = s;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String clientAddr = String.valueOf(channel.getRemoteSocketAddress());
        System.out.println("Kết nối mới từ: " + clientAddr);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream(), StandardCharsets.UTF_8));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(channel.getOutputStream(), StandardCharsets.UTF_8), true)) {
        	
        	writer.println("Server: Chào mừng bạn! Hãy gửi tin nhắn cho Server.");
        	
            String line;
            while ((line = in.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) continue;

                if (trimmed.equalsIgnoreCase("QUIT")) {
                	writer.println("OK BYE");
                    break;
                }
                System.out.println("[" + clientAddr + "]: " + trimmed);
                writer.println("Server nhận được: " + trimmed);
            }
        }catch (IOException e) {
            System.err.println("Client " + clientAddr + " gặp lỗi: " + e.getMessage());
        }finally {
            System.out.println("Client " + clientAddr + " đã ngắt kết nối.");
            try {
                channel.close();
            } catch (IOException ignored) {}
        }
	}
}
