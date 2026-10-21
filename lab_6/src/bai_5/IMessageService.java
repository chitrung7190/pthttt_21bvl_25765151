package bai_5;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMessageService extends Remote {
	void sendMessage(String message) throws RemoteException;
	String getLastestMessage() throws RemoteException;
}
