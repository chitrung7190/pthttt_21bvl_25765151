package bai_2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculatorService extends Remote {
	int add(int a, int b) throws RemoteException;
}
