package lab_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductManagement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String databaseFile = "D:/workspace/he_thong_tich_hop/lab_jdbc/database/sanpham.db";
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

			String sqlCreateTable = "CREATE TABLE IF NOT EXISTS SanPham (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "maSP TEXT, tenSP TEXT, gia INTEGER, soLuong INTEGER"
					+ ");";
			stmt.executeUpdate(sqlCreateTable);

			System.out.println("Đã tạo bảng SanPham (hoặc bảng đã tồn tại).");

			stmt.executeUpdate("DELETE FROM SanPham;");

			String sqlInsert1 = "INSERT INTO SanPham (maSP, tenSP, gia, soLuong) VALUES ('SP001', 'Sản phẩm 001', 100000, 30);";
			String sqlInsert2 = "INSERT INTO SanPham (maSP, tenSP, gia, soLuong) VALUES ('SP002', 'Sản phẩm 002', 100000, 30);";
			String sqlInsert3 = "INSERT INTO SanPham (maSP, tenSP, gia, soLuong) VALUES ('SP003', 'Sản phẩm 003', 100000, 30);";

			stmt.executeUpdate(sqlInsert1);
			stmt.executeUpdate(sqlInsert2);
			stmt.executeUpdate(sqlInsert3);
			System.out.println("Đã thêm 3 sản phẩm mới vào bảng.");

			showProducts(stmt, "Danh sách sản phẩm");
			
			String sqlUpdate1 = "UPDATE SanPham SET gia = 900000 WHERE maSP = 'SP002';";
			stmt.executeUpdate(sqlUpdate1);
			System.out.println("Đã cập nhật sản phẩm maSP=SP002.");
			showProducts(stmt, "Danh sách sản phẩm sau khi cập nhật");
			
			String sqlDelete1 = "DELETE FROM SanPham WHERE maSP = 'SP002';";
			stmt.executeUpdate(sqlDelete1);
			System.out.println("Đã xóa sản phẩm maSP=SP002.");
			showProducts(stmt, "Danh sách sản phẩm sau khi xóa");


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

	private static void showProducts(Statement stmt, String title) throws SQLException {
		// TODO Auto-generated method stub
		String sqlSelect = "SELECT * FROM SanPham;";
		ResultSet rs = stmt.executeQuery(sqlSelect);
		System.out.println("--- " + title + " ---");
		while (rs.next()) {
			int id = rs.getInt("id");
			String maSP = rs.getString("maSP");
			String tenSP = rs.getString("tenSP");
			int gia = rs.getInt("gia");
			int soLuong = rs.getInt("soLuong");

			System.out.print("ID: " + id + " Mã SP: " + maSP + " Tên SP: " + tenSP + " Giá: " + gia + " Số lượng: " + soLuong);
			System.out.println();
		}
		System.out.println("--------------------------------------------------");
	}
	
	

}
