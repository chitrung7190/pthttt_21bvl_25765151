package bai_8;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IFlightService service = (IFlightService) registry.lookup("FlightService");
            System.out.println("Available Seats" + service.availableSeats("VN123")); 
            boolean isBookTicket = service.bookTicket("VN123", 10);
            if (isBookTicket) {
            		System.out.println("Book Ticket VN123 10 ticket success"); 
            } else {
            		System.out.println("Book Ticket VN123 10 ticket fail");
            }
            System.out.println("Available Seats " + service.availableSeats("VN123"));
            boolean isCancelBooking = service.cancelBooking("VN123", 5);
            if (isCancelBooking) {
            		System.out.println("Cancel booking VN123 5 ticket success"); 
            } else {
            		System.out.println("Cancel booking VN123 10 ticket fail");
            }
            System.out.println("Available Seats " + service.availableSeats("VN123"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			scanner.close();
		}
	}

}
