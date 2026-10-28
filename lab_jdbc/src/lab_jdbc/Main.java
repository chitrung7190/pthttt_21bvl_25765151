package lab_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String databaseFile = "D:/workspace/he_thong_tich_hop/lab_jdbc/database/nhanvien.db";
		String url = "jdbc:sqlite:" + databaseFile;
		
		Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
		try {
			conn = DriverManager.getConnection(url);
            if (conn == null) {                
                throw new SQLException("Kết nối đến SQLite thất bại");
            }
            System.out.println("Kết nối đến SQLite thành công!");
            
            stmt = conn.createStatement();
            System.out.println("Tạo Statement thành công!");
            
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS NhanVien ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "ten TEXT NOT NULL, "
                    + "chuc_vu TEXT"
                    + ");";
            stmt.executeUpdate(sqlCreateTable);
            
            System.out.println("Đã tạo bảng NhanVien (hoặc bảng đã tồn tại).");
            
            stmt.executeUpdate("DELETE FROM NhanVien;");
            
            String sqlInsert1 = "INSERT INTO NhanVien (ten, chuc_vu) VALUES ('Nguyen Van A', 'Lap trinh vien');";
            String sqlInsert2 = "INSERT INTO NhanVien (ten, chuc_vu) VALUES ('Tran Thi B', 'Kiem thu phan mem');";
            String sqlInsert3 = "INSERT INTO NhanVien (ten, chuc_vu) VALUES ('Le Van C', 'Quan ly du an');";
            
            stmt.executeUpdate(sqlInsert1);
            stmt.executeUpdate(sqlInsert2);
            stmt.executeUpdate(sqlInsert3);
            System.out.println("Đã thêm 3 nhân viên mới vào bảng.");
            
            String sqlSelect = "SELECT id, ten, chuc_vu FROM NhanVien;";
            rs = stmt.executeQuery(sqlSelect);
            System.out.println("--- B4: DANH SÁCH NHÂN VIÊN TRONG DATABASE ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                String chucVu = rs.getString("chuc_vu");
                
                System.out.print("ID: " + id + " Tên: " + ten + " Chức vụ: " + chucVu);
                System.out.println();
            }
            System.out.println("--------------------------------------------------");
            
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Đã đóng kết nối và giải phóng tài nguyên thành công!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

}
