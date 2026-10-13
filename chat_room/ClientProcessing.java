package chat_room;

import java.io.BufferedReader;
import java.io.IOException;

public class ClientProcessing implements Runnable {
	
	private BufferedReader reader;

    public ClientProcessing(BufferedReader reader) {
        this.reader = reader;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
            String response;
            // Liên tục lắng nghe tin nhắn từ Server gửi về
            while ((response = reader.readLine()) != null) {
                // Nếu Server yêu cầu nhập tên
                if (response.equals("SUBMIT_NAME")) {
                    System.out.print("Nhap ten cua ban: ");
                } else {
                    // In ra màn hình các thông báo hoặc tin nhắn chat khác
                    System.out.println(response);
                }
            }
        } catch (IOException e) {
            System.out.println("Da mat ket noi voi Server.");
        }
	}

}
