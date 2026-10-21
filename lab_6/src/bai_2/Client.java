package bai_2;

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
			ICalculatorService calculatorService = (ICalculatorService) registry.lookup("CalculatorService");
			
			
			System.out.println("Nhap a: ");
			int a = scanner.nextInt(); 
			System.out.println("Nhap b: ");
			int b = scanner.nextInt(); 
			int sum = calculatorService.add(a, b);
			System.out.println("Tong " + a + " + " + b + " = " + sum);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			scanner.close();
		}
	}

}
