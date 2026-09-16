package socket_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CalcTCPServer {

	private static final int PORT = 8000;
	public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Calculator TCP Server listening on port " + PORT);
            while (true) {
                try (Socket socket = server.accept()) {
                    serve(socket);
                } catch (IOException e) {
                    System.err.println("Lỗi phiên client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Không thể khởi chạy Server: " + e.getMessage());
        }
    }

    private static void serve(Socket socket) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {

            String line;
            while ((line = in.readLine()) != null) {
                String response = process(line);
                out.println(response);
                if (line.trim().equalsIgnoreCase("QUIT")) break;
            }
        }
    }

    static String process(String input) {
        String trimmed = input.trim();
        if (trimmed.equalsIgnoreCase("QUIT")) {
            return "OK BYE";
        }

        String[] parts = trimmed.split("\\s+");
        if (parts.length != 4 || !parts[0].equalsIgnoreCase("CALC")) {
            return "ERR INVALID_FORMAT";
        }

        String operator = parts[1];
        double op1, op2;

        try {
            op1 = Double.parseDouble(parts[2]);
            op2 = Double.parseDouble(parts[3]);
        } catch (NumberFormatException e) {
            return "ERR INVALID_NUMBER";
        }

        switch (operator) {
            case "+":
                return "OK " + (op1 + op2);
            case "-":
                return "OK " + (op1 - op2);
            case "*":
                return "OK " + (op1 * op2);
            case "/":
                if (op2 == 0) {
                    return "ERR DIVIDE_BY_ZERO";
                }
                return "OK " + (op1 / op2);
            default:
                return "ERR UNSUPPORTED_OPERATOR";
        }
    }

}
