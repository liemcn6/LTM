package control;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.danhBa;
import view.ClientView;

public class ClientControl {

	    ClientView view;

	    public ClientControl(ClientView view) {
	        this.view = view;
	    }

	    public void run() {
	        try {
	            Registry registry = LocateRegistry.getRegistry(7777);
	            IDanhBa idanhba = (IDanhBa) registry.lookup("danhBa");
	            Scanner sc = new Scanner(System.in);
	            int i = 0;
	            while (true) {
	                System.out.println("1. Them mot danhBa.");
	                System.out.println("2. Sua mot danhBa.");
	                System.out.println("3. Xoa mot danhBa.");
	                System.out.println("4. Hien thi danh sach danhBa.");
	                System.out.println("5. Tim kiem danhBa theo ten.");
	                System.out.println("6. Thoat");
	                i = sc.nextInt();
	                if (i == 1) {
	                    idanhba.add(view.getDanhBa());
	                } else if (i == 2) {
	                	System.out.println("Sua theo id,id bandau , name-sdt-diachi thi sua theo nhu cau");
	                    idanhba.edit(view.getDanhBa());
	                } else if (i == 3) {
	                	System.out.println("Xoa theo id, nhap dung id la duoc");
	                    idanhba.delete(view.getDanhBa());
	                } else if (i == 4) {
	                    ArrayList<danhBa> danhba = idanhba.findAll();
	                    if (danhba != null) {
	                        for (danhBa p : danhba) {
	                            view.showMessage(p.toString());
	                        }
	                    }
	                } else if (i == 5) {
	                    String name = view.getName();
	                    System.out.println(name);
	                    ArrayList<danhBa> danhba = idanhba.findByName(name);
	                    if (true) {
	                        for (danhBa p : danhba) {
	                            view.showMessage(p.toString());
	                        }
	                    }

	                } else if (i == 6) {
	                    break;
	                } else {
	                    view.showMessage("Nhap sai, nhap lai!");
	                }
	            }
	        } catch (RemoteException ex) {
	            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (NotBoundException ex) {
	            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

}
