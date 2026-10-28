package bai_9;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class AuctionServiceImpl extends UnicastRemoteObject implements IAuctionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Double> products = new HashMap<>();

	protected AuctionServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addProduct(String productName, double startPrice) throws RemoteException {
		// TODO Auto-generated method stub
		products.put(productName, startPrice);
		System.out.println("Da them san pham : " + productName + " voi gia khoi diem: " + startPrice);
	}

	@Override
	public Map<String, Double> getProductList() throws RemoteException {
		// TODO Auto-generated method stub
		return products;
	}

	@Override
	public boolean placeBid(String productName, double bidAmount) throws RemoteException {
		// TODO Auto-generated method stub
		if (products.containsKey(productName)) {
            double currentPrice = products.get(productName);
            if (bidAmount > currentPrice) {
                products.put(productName, bidAmount);
                System.out.println("San pham [" + productName + "] co gia thau moi cao hon: " + bidAmount);
                return true;
            }
        }
        return false;
	}

}
