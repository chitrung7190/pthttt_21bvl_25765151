package bai_4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ContactServiceImpl extends UnicastRemoteObject implements IContactService{

	private HashMap<String, String> contacts = new HashMap<>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	protected ContactServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized void addContact(String name, String phone) throws RemoteException {
		// TODO Auto-generated method stub
		contacts.put(name, phone);
		System.out.println("Added Contact " + name + " - " + phone);
	}

	@Override
	public synchronized String findContact(String name) throws RemoteException {
		// TODO Auto-generated method stub
		String phone = contacts.get(name);
		if (phone == null) {
			return "";
		}
		return contacts.get(name);
	}

	@Override
	public synchronized boolean deleteContact(String name) throws RemoteException {
		// TODO Auto-generated method stub
		if (contacts.containsKey(name)) {
			contacts.remove(name);
			System.out.println("Removed Contact " + name);
			return true;
		}
		return false;
	}

}
