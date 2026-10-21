package bai_6;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankServiceImpl extends UnicastRemoteObject implements IBankService {
	
	private double balance;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected BankServiceImpl(double initBalance) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.balance = initBalance;
	}

	@Override
	public double getBalance() throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Client getBalance " + balance);
		return balance;
	}

	@Override
	public void deposit(double amount) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Client deposit " + amount);
		if (amount > 0) {
			balance += amount;
		}
	}

	@Override
	public boolean withdraw(double amount) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Client withdraw " + amount);
		if (amount > 0 && balance >= amount) {
			balance -= amount;
			return true;
		}
		return false;
	}

}
