package view;

import java.util.Scanner;
import model.danhBa;


public class ClientView {
    Scanner sc = new Scanner(System.in);

    public ClientView() {
        sc = new Scanner(System.in);
    }  
    public String getName() {
        System.out.println("Nhap ten cua danh ba");
       
        String name = sc.nextLine();
        return name;
    }
    
    public danhBa getDanhBa() {
        System.out.println("Nhap thong tin danh ba (id, name, sdt, dia chi):");
       
        int id = Integer.parseInt(sc.nextLine());
        String name = sc.nextLine().trim();
        String sdt = sc.nextLine().trim();
        String diachi = sc.nextLine().trim();
        return new danhBa(id, name, sdt, diachi);
    }
    
    public void showMessage(String msg) {
        System.out.println(msg);
    }
}

