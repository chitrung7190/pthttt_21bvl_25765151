package bai_8;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFlightService extends Remote{
	boolean bookTicket(String flightNumber, int seats) throws RemoteException;
	int availableSeats(String flightNumber) throws RemoteException;
	boolean cancelBooking(String flightNumber, int seats) throws RemoteException;
}
