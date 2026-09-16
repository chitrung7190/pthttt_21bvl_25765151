package network;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class HostInspector2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 2) {
			System.out.println("Usage: java.network.HostInspector <hostname>");
			return;
		}
		
		System.out.println("=== KHẢO SÁT HOST ===");
		
		try {
			InetAddress[] addresses = InetAddress.getAllByName(args[0]);
			
			System.out.println("Host: " + args[0]);
			
			for (InetAddress address : addresses) {
				System.out.println("- IP: " + address.getHostAddress());
				System.out.println(" Canonical: " + address.getCanonicalHostName());
				System.out.println(" Loopback: " + address.isLoopbackAddress());
				System.out.println(" Site local: " + address.isSiteLocalAddress());
			}
		} catch (UnknownHostException e) {
			// TODO: handle exception
			System.err.println("Không phân giải được host: " + args[0]);
		}
		
		System.out.println("\n=== PHÂN TÍCH URI ===");
		try {
			URI uri = new URI(args[1]);
			System.out.println("URI gốc: " + uri.toString());
			System.out.println("Scheme: " + uri.getScheme());
			System.out.println("Host: " + uri.getHost());
			System.out.println("Port: " + (uri.getPort() == -1 ? "Không tìm thấy" : uri.getPort()));
			System.out.println("Path: " + uri.getPath());
			System.out.println("Query: " + uri.getQuery());
			System.out.println("Fragment: " + uri.getFragment());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Không phân giải được URI: " + args[1]);
		}
	}

}
