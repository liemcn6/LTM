package control;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.ServerView;

public class ServerControl extends ImplDanhBa {

	    ServerView view;
	    
	    public ServerControl(ServerView view) {
	        try {
	            this.view = view;
	            ImplDanhBa implDanhBa = new ImplDanhBa();
	            IDanhBa idanhba = (IDanhBa) UnicastRemoteObject.exportObject(implDanhBa, 0);
	            Registry registry =  LocateRegistry.createRegistry(7777); 
	            registry.bind("danhBa", idanhba);
	        } catch (RemoteException ex) {
	            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (AlreadyBoundException ex) {
	            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
}
