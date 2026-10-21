package bai_3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IPrimativeService service = (IPrimativeService) registry.lookup("PrimativeService");
            
            System.out.println("Nhap so can kiem tra ");
            int n = scanner.nextInt();
            boolean result = service.isPrime(n);
            if (result) {
            		System.out.println("So: " + n + " la so nguyen to");
            } else {
        			System.out.println("So: " + n + " khong la so nguyen to");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			scanner.close();
		}
	}

}
