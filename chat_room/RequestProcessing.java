package chat_room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class RequestProcessing extends Thread{
	Socket channel;
	BufferedReader reader;
	PrintWriter writer;
	
	String clientName;
	String currentRoom = null;
	
	public RequestProcessing(java.net.Socket socket) {
		this.channel = socket;
	}
	
	@Override
	public void run() {
		try {
            reader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            writer = new PrintWriter(channel.getOutputStream(), true);

            // Bước 1: Yêu cầu client nhập Tên
            writer.println("SUBMIT_NAME");
            clientName = reader.readLine(); // Nhận tên từ client gửi lên
            System.out.println("Client co ten la: " + clientName);
            writer.println("Chao mung ban " + clientName + " den voi he thong chat!");

            // Bước 2: Vòng lặp liên tục lắng nghe yêu cầu của client
            while (true) {
                // Nếu client chưa vào phòng nào -> Hiển thị danh sách phòng
                if (currentRoom == null) {
                    writer.println("=== DANH SACH PHONG ===");
                    writer.println("Study, Game, TraiNghiem");
                    writer.println("Nhap ten phong ban muon vao:");
                }

                // Đọc tin nhắn client nhập vào từ bàn phím
                String message = reader.readLine();
                if (message == null) break; // Nếu mất kết nối thì thoát vòng lặp

                message = message.trim();

                // Nếu client gõ /exit -> Ngắt kết nối
                if (message.equalsIgnoreCase("/exit")) {
                		System.out.println("Client " + clientName + " da ngat ket noi");
                    break;
                }
                
                // Nếu client gõ /quit -> Rời phòng hiện tại
                else if (message.equalsIgnoreCase("/quit")) {
                    thoatKhoiPhong();
                    writer.println("Ban da roi phong, quay lai danh sach chon phong.");
                } 
                
                // Nếu chưa vào phòng -> Coi nội dung nhập là Tên Phòng muốn vào
                else if (currentRoom == null) {
                    if (TCPServer.chatRooms.containsKey(message)) {
                        currentRoom = message; // Lưu tên phòng lại
                        TCPServer.chatRooms.get(currentRoom).add(this); // Thêm client này vào danh sách phòng
                        writer.println("Ban da vao phong: " + currentRoom + ". Go /quit de roi phong.");
                        
                        // Báo cho các bạn khác trong phòng biết có người mới vào
                        guiTinNhanChoCaPhong("He thong: " + clientName + " da vao phong.", this);
                    } else {
                        writer.println("Phong khong ton tai! Vui long nhap lai.");
                    }
                } 
                
                // Nếu đang ở trong phòng -> Gửi tin nhắn này cho tất cả mọi người cùng phòng
                else {
                    String noiDungChat = clientName + ": " + message;
                    guiTinNhanChoCaPhong(noiDungChat, this);
                }
            }

        } catch (IOException e) {
            System.out.println(clientName + " da ngat ket noi dot ngot.");
        } finally {
            // Khi thoát, nhớ dọn dẹp xóa client khỏi phòng và đóng kết nối
            thoatKhoiPhong();
            try {
                channel.close();
            } catch (IOException e) {}
            System.out.println("Da dong ket noi cua " + clientName);
        }
	}
	
	// Hàm phụ trợ: Gửi tin nhắn đến mọi người trong cùng phòng (trừ người gửi)
    public void guiTinNhanChoCaPhong(String msg, RequestProcessing nguoiGui) {
        List<RequestProcessing> danhSachClientTrongPhong = TCPServer.chatRooms.get(currentRoom);
        if (danhSachClientTrongPhong != null) {
            for (RequestProcessing c : danhSachClientTrongPhong) {
                // Không gửi lại tin nhắn cho chính mình
                if (c != nguoiGui) {
                    c.writer.println(msg);
                }
            }
        }
    }

    // Hàm phụ trợ: Rời khỏi phòng hiện tại
    public void thoatKhoiPhong() {
        if (currentRoom != null && TCPServer.chatRooms.containsKey(currentRoom)) {
        	TCPServer.chatRooms.get(currentRoom).remove(this); // Xóa khỏi danh sách phòng cũ
            guiTinNhanChoCaPhong("He thong: " + clientName + " da roi phong.", this);
            currentRoom = null; // Đặt lại trạng thái chưa vào phòng
        }
    }
}
