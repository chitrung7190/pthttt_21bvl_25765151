package bai_3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrimativeServiceImpl extends UnicastRemoteObject implements IPrimativeService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected PrimativeServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isPrime(int n) throws RemoteException {
		// TODO Auto-generated method stub
		if (n <= 1) return false;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (i % n == 0) return false;
		}
		return true;
	}

}
