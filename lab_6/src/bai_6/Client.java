package bai_6;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IBankService service = (IBankService) registry.lookup("BankService");
            
            System.out.println("So tien hien co " + service.getBalance());
            
            System.out.println("Nhap so tien deposit ");
            double depositAmount = scanner.nextDouble();
            service.deposit(depositAmount);
            System.out.println("So tien hien co " + service.getBalance());
            
            System.out.println("Nhap so tien withdraw ");
            double withdrawAmount = scanner.nextDouble();
            service.withdraw(withdrawAmount);
            System.out.println("So tien hien co " + service.getBalance());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			scanner.close();
		}
	}

}
