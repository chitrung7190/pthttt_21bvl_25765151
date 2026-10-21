package hello;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IHelloService extends Remote{
	String sayHello() throws RemoteException;
}
