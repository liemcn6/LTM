/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author DELL
 */
public abstract class TDAO <T>{
    Statement statement;
    PreparedStatement  preStatement;
    Connection conn;
    ResultSet rs;
    public abstract T[] selectAll();
    public abstract int insert(T object);
    public abstract int update(T object);
    public abstract int deletebyid(String id);
    public abstract void closeConnection();
}
