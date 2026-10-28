package bai_9;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface IAuctionService extends Remote {
	void addProduct(String productName, double startPrice) throws RemoteException;
	Map<String, Double> getProductList() throws RemoteException;
	boolean placeBid(String productName, double bidAmount) throws RemoteException;

}
