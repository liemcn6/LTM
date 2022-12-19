    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import java.util.ArrayList;
import java.util.Date;
import model.user.Account;
import model.user.Address;
import model.user.FullName;
import model.user.User;

/**
 *
 * @author DELL
 */
public interface UserDAO <T>{
    boolean checkUsernameExist(String username);
    User getUserByUsername(String username);
    User getUserById(int id);
    User createUserAccount(Account account);
    int getUserID(String phone,String mail);
    int updateCustomer(int id,String phone,String mail,String numberhouse,String street,String distinct,String city,String firstname,String midname,String lastname);
    T viewOrder(int orderID);
    ArrayList<T> getAllOrder(int UserID);
}
