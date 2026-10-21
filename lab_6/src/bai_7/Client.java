package bai_7;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IShapeService service = (IShapeService) registry.lookup("ShapeService");
            System.out.println("Rectangle Area ");
            System.out.println("Nhap width ");
            double width = scanner.nextDouble();
            System.out.println("Nhap height ");
            double height = scanner.nextDouble();
            System.out.println("Rectangle Area " + service.rectangleArea(width, height));
            
            System.out.println("Circle Area ");
            System.out.println("Nhap radius ");
            double radius = scanner.nextDouble();
            System.out.println("Circle Area " + service.circleArea(radius));

            System.out.println("Triangle Area ");
            System.out.println("Nhap base ");
            double base = scanner.nextDouble();
            System.out.println("Nhap height ");
            double triangleHeight = scanner.nextDouble();
            System.out.println("Triangle Area " + service.triangleArea(base, triangleHeight));
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			scanner.close();
		}
	}

}
