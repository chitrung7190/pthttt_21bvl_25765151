package socket_tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServer {

	public final static int serverPort = 4000;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(serverPort);
			System.out.println("Server da duoc tao");
			while(true) {
				try {
					Socket s = ss.accept();
					OutputStream os = s.getOutputStream();
					InputStream is = s.getInputStream();
					int ch = 0;
					while(true) {
						ch = is.read();
						if (ch == -1) {
							break;
						}
						System.out.println((char) ch);
						os.write(ch);
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("Connection error: " + e);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Server creation error: " + e);
		} finally {
			ss.close();
		}
	}

}
