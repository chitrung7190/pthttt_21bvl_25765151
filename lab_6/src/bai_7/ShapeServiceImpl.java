package bai_7;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ShapeServiceImpl extends UnicastRemoteObject implements IShapeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ShapeServiceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public double rectangleArea(double width, double height) throws RemoteException {
		// TODO Auto-generated method stub
		return width * height;
	}

	@Override
	public double circleArea(double radius) throws RemoteException {
		// TODO Auto-generated method stub
		return Math.PI * radius * radius;
	}

	@Override
	public double triangleArea(double base, double height) throws RemoteException {
		// TODO Auto-generated method stub
		return 0.5 * base * height;
	}

}
