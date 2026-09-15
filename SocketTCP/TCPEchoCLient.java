package socket_tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPEchoCLient {

	public static final String serverIP = "127.0.0.1";
	public static final int serverPort = 4000;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket s = null;
		try {
			s = new Socket(serverIP, serverPort);
			System.out.println("Client da duoc tao");
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();

			for (int i = '0'; i <= '9'; i++) {
				os.write(i);
				int ch = is.read();
				System.out.println((char) ch);
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: CAN NOT create socket");
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

}
