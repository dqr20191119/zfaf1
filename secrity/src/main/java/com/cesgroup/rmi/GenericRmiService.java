package com.cesgroup.rmi;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GenericRmiService extends Remote, Serializable {

	public Object doService(String serviceName, String methodName, Object[] args)throws RemoteException;
	
}
