package chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient {
	
	public static final String host = "localhost";
	public static final int port = 5000;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            Socket socket = new Socket(host, port);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            String welcomeMsg = in.readLine();
            if (welcomeMsg != null) {
                System.out.println(welcomeMsg);
            }
            String userInput;
            System.out.print("> ");
            while ((userInput = console.readLine()) != null) {
                out.println(userInput);
                if (userInput.trim().equalsIgnoreCase("QUIT")) {
                    break;
                }
                String response = in.readLine();
                if (response == null) {
                    System.out.println("Server close.");
                    break;
                }


                System.out.println(response);


                if (userInput.trim().equalsIgnoreCase("QUIT")) {
                    break;
                }
                System.out.print("> ");
            }

            socket.close();
		} catch (Exception e) {
			System.err.println("Chat Client erro: " + e.getMessage());
		}

	}

}
