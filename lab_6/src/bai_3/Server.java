package bai_3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	
	final static int port = 1099;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Registry registry = LocateRegistry.createRegistry(port);
			IPrimativeService service = new PrimativeServiceImpl();
			registry.rebind("PrimativeService", service);
            System.out.println("Server RMI đang chạy...");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
