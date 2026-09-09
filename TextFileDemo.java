package bai_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path file = Path.of("data", "ghi_chu.txt");
		try {
			Files.createDirectories(file.getParent());
			try(BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)){
				writer.write("Java I/O làm việc với các luồng dữ liệu.");
				writer.newLine();
				writer.write("BufferedWriter giúp ghi văn bản hiệu quả.");
				writer.newLine();
				writer.write("UTF-8 hỗ trợ tiếng Việt ổn định.");
			}
			
			try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
				String line;
				int number = 1;
				while ((line = reader.readLine()) != null) {
					System.out.printf("%d. %s%n", number++, line);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println("Lỗi xử lý tệp " + file + ": "
					+ e.getMessage());
		}
	}

}
