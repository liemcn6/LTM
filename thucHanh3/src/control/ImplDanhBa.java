package control;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.danhBa;

public class ImplDanhBa implements IDanhBa {
	
	    Connection con;
	    String hostName = "localhost";
	    String dbName = "mysql_database";
	    String userName = "root";
	    String password = "Quangliem3173";

	    public ImplDanhBa() {
	    }
	    
	    private void connect() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName+"?useSSL=false";
	            con = DriverManager.getConnection(connectionURL, userName,
	                    password);
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
	        } catch (SQLException ex) {
	            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    private void disconnect() {
	        try {
	            con.close();
	        } catch (SQLException ex) {
	            Logger.getLogger(ImplDanhBa.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

	    @Override
	    public int add(danhBa p) throws RemoteException {
	        try {
	            connect();
	            PreparedStatement stmt = con.prepareStatement("insert into danhba values(?,?,?, ?)");
	            stmt.setInt(1, p.getId());
	            stmt.setString(2, p.getName());
	            stmt.setString(3, p.getSdt());
	            stmt.setString(4, p.getDiachi());
	            int result =  stmt.executeUpdate();
	            stmt.close();
	            disconnect();
	            return result;
	        } catch (SQLException ex) {
	            Logger.getLogger(ImplDanhBa.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return -1;
	    }

	    @Override
	    public int edit(danhBa p) throws RemoteException {
	        try {
	            connect();
	            PreparedStatement stmt = con.prepareStatement("UPDATE danhba\n"
	                    + "SET name = ?, sdt= ?, diachi= ?\n"
	                    + "WHERE id = ?;");
	            stmt.setString(1, p.getName());
	            stmt.setString(2, p.getSdt());
	            stmt.setString(3, p.getDiachi());
	            stmt.setInt(4, p.getId());
	            int result =  stmt.executeUpdate();
	            stmt.close();
	            disconnect();
	            return result;
	        } catch (SQLException ex) {
	            Logger.getLogger(ImplDanhBa.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return -1;
	    }

	    @Override
	    public int delete(danhBa p) throws RemoteException {
	        try {
	            connect();
	            PreparedStatement stmt = con.prepareStatement("DELETE FROM danhba "
	                    + "WHERE id = ?;");
	            stmt.setInt(1, p.getId());
	            int result =  stmt.executeUpdate();
	            stmt.close();
	            disconnect();
	            return result;
	        } catch (SQLException ex) {
	            Logger.getLogger(ImplDanhBa.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return -1;
	    }


	    @Override
	    public ArrayList<danhBa> findAll() throws RemoteException {
	        connect();
	        ArrayList<danhBa> danhba = new ArrayList<danhBa>();
	        try {
	            String sql = "Select * from danhba";
	            PreparedStatement statement = con.prepareStatement(sql);
	            ResultSet rs = statement.executeQuery();
	            while (rs.next()) {
	                danhBa d = new danhBa(
	                        rs.getInt(1),
	                        rs.getString(2),
	                        rs.getString(3),
	                        rs.getString(4)
	                );
	                danhba.add(d);
	            }
	            disconnect();
	            return danhba;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    @Override
	    public ArrayList<danhBa> findByName(String name) throws RemoteException {
	        connect();
	        ArrayList<danhBa> danhba = new ArrayList<danhBa>();
	        try {
	            String sql = "Select * from danhba where name = ?;";
	            PreparedStatement statement = con.prepareStatement(sql);
	            statement.setString(1, name);
	            ResultSet rs = statement.executeQuery();
	            while (rs.next()) {
	                danhBa d = new danhBa(
	                        rs.getInt(1),
	                        rs.getString(2),
	                        rs.getString(3),
	                        rs.getString(4)
	                );
	                danhba.add(d);
	            }
	            return danhba;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }}

	}

