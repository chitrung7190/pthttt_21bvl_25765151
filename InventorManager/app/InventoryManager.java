package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Product;

public class InventoryManager {
	
	private static final Path DATA_DIR = Paths.get("data");
	private static final Path CSV_PATH = Paths.get("data", "inventory.csv");    
    private static final Path REPORT_PATH = Paths.get("data", "inventory-report.txt");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ensureDataDirectoryExists();

        List<Product> inputProducts = inputProductsFromKeyboard();

        if (!inputProducts.isEmpty()) {
            saveToCsv(inputProducts, CSV_PATH);
        }

        System.out.println("\n--- ĐỌC DỮ LIỆU TỪ TỆP CSV ---");
        List<Product> loadedProducts = loadFromCsv(CSV_PATH);

        if (loadedProducts.isEmpty()) {
            System.out.println("Không có sản phẩm nào hợp lệ để hiển thị và tạo báo cáo.");
            return;
        }

        System.out.println("\n--- DANH SÁCH SẢN PHẨM TỒN KHO ---");
        double totalValue = 0;
        Product maxProduct = loadedProducts.get(0);

        for (Product p : loadedProducts) {
            System.out.println(p);
            double val = p.getInventoryValue();
            totalValue += val;
            if (val > maxProduct.getInventoryValue()) {
                maxProduct = p;
            }
        }

        System.out.printf("%n=> TỔNG GIÁ TRỊ TỒN KHO: %,.2f VNĐ%n", totalValue);
        System.out.println("=> SẢN PHẨM CÓ GIÁ TRỊ TỒN KHO CAO NHẤT:");
        System.out.println("   " + maxProduct);

        writeReport(loadedProducts, totalValue, maxProduct, REPORT_PATH);
	}
	
	private static void ensureDataDirectoryExists() {
        try {
            if (Files.notExists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
            }
        } catch (IOException e) {
            System.err.println("Lỗi: Không thể tạo thư mục data: " + e.getMessage());
        }
    }
	
	// 4. Nhập danh sách sản phẩm từ bàn phím
    private static List<Product> inputProductsFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        List<Product> list = new ArrayList<>();

        System.out.print("Nhập số lượng sản phẩm muốn thêm: ");
        int n = 0;
        try {
            n = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Số lượng không hợp lệ. Hủy thao tác nhập.");
            scanner.close();
            return list;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Nhập sản phẩm thứ " + i + " ---");
            while (true) {
                try {
                    System.out.print("Nhập mã SP: ");
                    String code = scanner.nextLine();

                    System.out.print("Nhập tên SP: ");
                    String name = scanner.nextLine();

                    System.out.print("Nhập đơn giá (> 0): ");
                    double price = Double.parseDouble(scanner.nextLine());

                    System.out.print("Nhập số lượng (>= 0): ");
                    int qty = Integer.parseInt(scanner.nextLine());

                    Product p = new Product(code, name, price, qty);
                    list.add(p);
                    System.out.println("-> Thêm sản phẩm thành công!");
                    break;
                } catch (NumberFormatException e) {
                    System.err.println("Lỗi dữ liệu: Giá hoặc số lượng phải là số hợp lệ. Vui lòng nhập lại!");
                } catch (IllegalArgumentException e) {
                    System.err.println("Lỗi dữ liệu: " + e.getMessage() + " Vui lòng nhập lại!");
                }
            }
        }
        scanner.close();
        return list;
    }
    
 // 5. Lưu danh sách vào data/inventory.csv bằng UTF-8
    private static void saveToCsv(List<Product> products, Path path) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write("Code,Name,UnitPrice,Quantity");
            writer.newLine();
            for (Product p : products) {
                writer.write(p.toCsvRow());
                writer.newLine();
            }
            System.out.println("\n-> Đã lưu danh sách vào tệp: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi tệp " + path + ": " + e.getMessage());
        }
    }

    // 6. Đọc lại tệp CSV và tái tạo danh sách đối tượng Product
    private static List<Product> loadFromCsv(Path path) {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line = reader.readLine(); // Bỏ qua dòng tiêu đề
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;

                String[] parts = line.split(",");
                if (parts.length < 4) {
                    System.err.printf("Lỗi dòng %d trong tệp [%s]: Thiếu cột dữ liệu (có %d/4 cột). Đã bỏ qua.%n",
                            lineNumber, path.getFileName(), parts.length);
                    continue;
                }

                try {
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    int qty = Integer.parseInt(parts[3].trim());

                    products.add(new Product(code, name, price, qty));
                } catch (NumberFormatException e) {
                    System.err.printf("Lỗi dòng %d trong tệp [%s]: Dữ liệu số không hợp lệ. Đã bỏ qua.%n",
                            lineNumber, path.getFileName());
                } catch (IllegalArgumentException e) {
                    System.err.printf("Lỗi dòng %d trong tệp [%s]: %s Đã bỏ qua.%n",
                            lineNumber, path.getFileName(), e.getMessage());
                }
            }
        } catch (NoSuchFileException e) {
            System.err.println("Lỗi: Không tìm thấy tệp dữ liệu tại đường dẫn: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc tệp [" + path + "]: " + e.getMessage());
        }

        return products;
    }

    // 9. Ghi báo cáo tổng hợp vào data/inventory-report.txt
    private static void writeReport(List<Product> products, double totalValue, Product maxProduct, Path path) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write("=================================================");
            writer.newLine();
            writer.write("           BÁO CÁO TỒN KHO SẢN PHẨM              ");
            writer.newLine();
            writer.write("=================================================");
            writer.newLine();
            writer.write(String.format("Tổng số lượng loại sản phẩm : %d%n", products.size()));
            writer.write(String.format("Tổng giá trị tồn kho        : %,.2f VNĐ%n", totalValue));
            writer.newLine();
            writer.write("SẢN PHẨM CÓ GIÁ TRỊ TỒN KHO CAO NHẤT:%n");
            writer.write(String.format("- Mã SP     : %s%n", maxProduct.getCode()));
            writer.write(String.format("- Tên SP    : %s%n", maxProduct.getName()));
            writer.write(String.format("- Đơn giá   : %,.2f VNĐ%n", maxProduct.getUnitPrice()));
            writer.write(String.format("- Số lượng  : %d%n", maxProduct.getQuantity()));
            writer.write(String.format("- Giá trị   : %,.2f VNĐ%n", maxProduct.getInventoryValue()));
            writer.write("=================================================");

            System.out.println("\n-> Đã ghi báo cáo thành công vào tệp: " + path.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi báo cáo vào tệp [" + path + "]: " + e.getMessage());
        }
    }

}
