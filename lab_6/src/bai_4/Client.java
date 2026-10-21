package bai_4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	private static final String host = "localhost";
	private static final int port = 1099;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		try {
			Registry registry = LocateRegistry.getRegistry(host, port);
			IContactService service = (IContactService) registry.lookup("ContactService");
			service.addContact("NGUYEN VAN A", "0909123123");
			service.addContact("NGUYEN VAN B", "0909113123");
			service.addContact("NGUYEN VAN C", "0909413123");
			
			System.out.println("Nhap ten can them: ");
			String a = scanner.nextLine(); 
			System.out.println("Nhap so dien thoai can them: ");
			String b = scanner.nextLine(); 
			service.addContact(a, b);
			System.out.println("Nhap ten can tim: ");
			String nameFind = scanner.nextLine();
			String phoneMatch = service.findContact(nameFind);
			if (phoneMatch.isEmpty()) {
				System.out.println("Khong tim thay " + nameFind); 
			} else {
				System.out.println("Tim thay " + nameFind + " " + phoneMatch); 
			}
			
			System.out.println("Nhap ten can xoa: ");
			String nameDelete = scanner.nextLine();
			boolean isDeleted = service.deleteContact(nameDelete);
			if (isDeleted) {
				System.out.println("Xoa " + nameDelete + " thanh cong"); 
			} else {
				System.out.println("Xoa " + nameDelete + " khong thanh cong"); 
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

}
