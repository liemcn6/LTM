/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import model.SalaryGrade;
/**
 *
 * @author Admin
 */
public class DAOSalaryGrade extends IDAO<SalaryGrade> {
    public DAOSalaryGrade(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public SalaryGrade[] selectAll() {
        Vector<SalaryGrade> ee = new Vector<SalaryGrade>();
        SalaryGrade[] result;
        try {
            String sql = "Select * from SalaryGrade";

            rs = statement.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                SalaryGrade e = new SalaryGrade(
                        rs.getInt(1),
                        rs.getFloat(2),
                        rs.getFloat(3)
                );
                ee.add(e);
                i++;
            }
            result = new SalaryGrade[i];
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return ee.toArray(result);
    }

    @Override
    public int insert(SalaryGrade s) {
        String sql = "INSERT INTO SALARYGRADE(GRADE,"
                + "HIGH_SALARY,"
                + "LOW_SALARY,"
                + "VALUES (?,?,?)";
        try {
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setInt(1, s.getGrade());
            this.preStatement.setFloat(2, s.getHighSalary());
            this.preStatement.setFloat(3, s.getLowSalary());
           
            int rowCount = this.preStatement.executeUpdate();

            return rowCount;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return 0;

        }
    }

    @Override
    public int update(SalaryGrade s) {
         String sql = "UPDATE SALARYGRADE set"
                +"GRADE= ?"
                + "HIGH_SALARY= ?"
                + "LOW_SALARY= ?";
        try {
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setInt(1, s.getGrade());
            this.preStatement.setFloat(2, s.getHighSalary());
            this.preStatement.setFloat(3, s.getLowSalary());
           
            int rowCount = this.preStatement.executeUpdate();

            return rowCount;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return 0;

        }
    }

    @Override
    public void delete(int id) {
        try {
            String mySQL = "DELETE FROM SALARYGRADE WHERE GRADE = " +id;
            this.preStatement = this.conn.prepareStatement(mySQL);
            this.preStatement.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
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

    @Override
    public SalaryGrade[] selectByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
