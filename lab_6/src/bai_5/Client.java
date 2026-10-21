package bai_5;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IMessageService service = (IMessageService) registry.lookup("MessageService");
            
            service.sendMessage(scanner.nextLine());
            System.out.println(service.getLastestMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			scanner.close();
		}
	}

}
