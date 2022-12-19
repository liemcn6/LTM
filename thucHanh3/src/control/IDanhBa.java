package control;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.danhBa;

public interface IDanhBa extends Remote{
	 public int add(danhBa p) throws RemoteException;
	 public int edit(danhBa p) throws RemoteException;
	 public int delete(danhBa p) throws RemoteException;
	 public ArrayList<danhBa> findAll() throws RemoteException;
	 public ArrayList<danhBa> findByName(String name) throws RemoteException;
}
