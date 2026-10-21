package hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IHelloService service = (IHelloService) registry.lookup("HelloService");
            String response = service.sayHello();
            System.out.println("Phản hồi từ Server: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
