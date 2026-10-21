package bai_3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPrimativeService extends Remote{
	boolean isPrime(int n) throws RemoteException;
}
