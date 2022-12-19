/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;


import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Timekeeper;

/**
 *
 * @author DELL
 */
public class DAOTimekeeper extends TDAO<Timekeeper>{

    public DAOTimekeeper(Connection conn) {
        this.conn = conn;
        try {
            this.statement =this.conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Timekeeper[] selectAll() {
        Vector<Timekeeper> tk = new Vector<Timekeeper>();
		Timekeeper[] result;
		try {
			String sql = "Select * from Timekeeper";

			rs =statement.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				Timekeeper t = new Timekeeper(
						rs.getString(1),
						rs.getDate(2),
						BigInteger.valueOf(rs.getInt(4)),
                                                rs.getString(3));
				tk.add(t);

				i++;
			}
			result = new Timekeeper[i];
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return tk.toArray(result);
    }

    @Override
    public int insert(Timekeeper t) {
        String sql = "INSERT INTO Timekeeper (Timekeeper_Id,Date_Time,In_Out,EMP_ID)"+
				"VALUES (?,?,?,?)";
		try {
			this.preStatement = this.conn.prepareStatement(sql);
			this.preStatement.setString(1, t.getTimekeeper_Id());
			this.preStatement.setDate(2,new java.sql.Date (t.getDate_Time().getTime()));                       
			this.preStatement.setString(3, t.getIn_Out());
			this.preStatement.setLong(4, t.getEmpId().longValue());
			int rowCount=this.preStatement.executeUpdate();
			
			return rowCount;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
			
		}
    }

    @Override
    public int update(Timekeeper t) {
        String sql = "UPDATE EMPLOYEE set "+
				"Timekeeper_Id = ?,"+
				"Date_Time = ?,"+
				"In_Out = ?,"+
				"EMP_ID= ?";
		try {
			this.preStatement = this.conn.prepareStatement(sql);
			this.preStatement.setString(1, t.getTimekeeper_Id());
			this.preStatement.setDate(2, new java.sql.Date(t.getDate_Time().getTime()));
			this.preStatement.setString(3, t.getIn_Out());
			this.preStatement.setLong(4, t.getEmpId().longValue());

			int rowCount=this.preStatement.executeUpdate();
			
			return rowCount;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return 0;
			
		}
    }

    @Override
    public int deletebyid(String id) {
       try {
            String mySQL = "DELETE FROM Timekeeper WHERE Timekeeper_Id ='" + id+"'";
            this.preStatement = this.conn.prepareStatement(mySQL);
            int rowCount = this.preStatement.executeUpdate();
            return rowCount;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return 0;
        }
    }

    @Override
    public void closeConnection() {
        try {
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
}
