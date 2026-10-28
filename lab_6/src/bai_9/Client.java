package bai_9;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IAuctionService service = (IAuctionService) registry.lookup("AuctionService");
            int choice = 0;
            do {
                System.out.println("1. Xem danh sach san pham & gia hien tai");
                System.out.println("2. Them san pham dau gia moi (Seller)");
                System.out.println("3. Dat gia thau (Bidder)");
                System.out.println("0. Thoat");
                System.out.print("Nhap lua chon cua ban: ");
                
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Vui long nhap so hop le");
                    scanner.nextLine();
                    continue;
                }

                switch (choice) {
                    case 1:
                        Map<String, Double> productList = service.getProductList();
                        System.out.println("\n--- DANH SACH SAN PHAM DAU GIA ---");
                        if (productList.isEmpty()) {
                            System.out.println("Hien chua co san pham nao trong he thong.");
                        } else {
                            for (Map.Entry<String, Double> entry : productList.entrySet()) {
                                System.out.println("- Ten san pham: " + entry.getKey() + " | Gia cao nhat hien tai: " + entry.getValue());
                            }
                        }
                        break;

                    case 2:
                        // Seller thêm sản phẩm mới
                        System.out.print("Nhap ten san pham: ");
                        String productName = scanner.nextLine();
                        System.out.print("Nhap gia khoi diem: ");
                        double startPrice = scanner.nextDouble();
                        scanner.nextLine();
                        
                        service.addProduct(productName, startPrice);
                        System.out.println("Da them san pham thanh cong len Server!");
                        break;

                    case 3:
                        // Bidder đặt giá thầu
                        System.out.print("Nhap ten san pham muon dau gia: ");
                        String targetProduct = scanner.nextLine();
                        System.out.print("Nhap muc gia thau cua ban: ");
                        double bidAmount = scanner.nextDouble();
                        scanner.nextLine();
                        
                        boolean success = service.placeBid(targetProduct, bidAmount);
                        if (success) {
                            System.out.println("Dat gia thau thanh cong! Ban dang giu muc gia cao nhat.");
                        } else {
                            System.out.println("Dat gia thau that bai! Gia thau phai cao hon gia hien tai hoac san pham khong ton tai.");
                        }
                        break;

                    case 0:
                        System.out.println("Thoat chuong trinh.");
                        break;

                    default:
                        System.out.println("Lua chon khong hop le, vui long chon lai.");
                }
            } while (choice != 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			scanner.close();
		}
	}

}
