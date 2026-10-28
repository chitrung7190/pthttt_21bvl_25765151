package bai_8;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class FlightServiceImpl extends UnicastRemoteObject implements IFlightService {
	private HashMap<String, Integer> flights = new HashMap<>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected FlightServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		flights.put("VN123", 50); 
        flights.put("VJ456", 20);
	}

	@Override
	public boolean bookTicket(String flightNumber, int seats) throws RemoteException {
		// TODO Auto-generated method stub
		int currentSeats = flights.getOrDefault(flightNumber, -1);
        if (currentSeats >= seats) {
            flights.put(flightNumber, currentSeats - seats);
            return true;
        }
        return false;
	}

	@Override
	public int availableSeats(String flightNumber) throws RemoteException {
		// TODO Auto-generated method stub
		return flights.getOrDefault(flightNumber, 0);
	}

	@Override
	public boolean cancelBooking(String flightNumber, int seats) throws RemoteException {
		// TODO Auto-generated method stub
		if (flights.containsKey(flightNumber)) {
            int currentSeats = flights.get(flightNumber);
            flights.put(flightNumber, currentSeats + seats);
            return true;
        }
        return false;
	}

}
