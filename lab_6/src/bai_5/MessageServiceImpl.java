package bai_5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MessageServiceImpl extends UnicastRemoteObject implements IMessageService {

	private String lastMessage = "";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected MessageServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendMessage(String message) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Client gui: " + message);
		this.lastMessage = "Server da nhan " + message;
	}

	@Override
	public String getLastestMessage(){
		// TODO Auto-generated method stub
		return this.lastMessage;
	}

}
