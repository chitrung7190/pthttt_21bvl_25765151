package hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
            Registry registry = LocateRegistry.createRegistry(1099);
            IHelloService service = new HelloServiceImpl();
            registry.rebind("HelloService", service);
            System.out.println("Server RMI đang chạy...");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
