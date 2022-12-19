/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import model.Department;

/**
 *
 * @author Admin
 */
public class DAODepartment extends IDAO<Department> {

    public DAODepartment(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Department[] selectAll() {
        Vector<Department> ee = new Vector<Department>();
        Department[] result;
        try {
            String sql = "Select * from Department";

            rs = statement.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                Department e = new Department(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                ee.add(e);
                i++;
            }
            result = new Department[i];
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return ee.toArray(result);

    }

    @Override
    public Department[] selectByName(String name) {
        Vector<Department> ee = new Vector<Department>();
        Department[] result;
        try {
            String sql = "Select * from Employee where Emp_Name='" + name + "'";

            rs = statement.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                Department e = new Department(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                ee.add(e);

                i++;
            }
            result = new Department[i];
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return ee.toArray(result);
    }

    @Override
    public int insert(Department d) {
        String sql = "INSERT INTO DEPARTMENT (DEPT_ID,"
                + "DEPT_NAME,"
                + "DEPT_NO,"
                + "LOCATION,"
                + "VALUES (?,?,?,?)";
        try {
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setInt(1, d.getDeptId());
            this.preStatement.setString(2, d.getDeptName());
            this.preStatement.setString(3, d.getDeptNo());
            this.preStatement.setString(4, d.getLocation());

            int rowCount = this.preStatement.executeUpdate();

            return rowCount;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return 0;

        }
    }

    @Override
    public int update(Department d) {
        String sql = "UPDATE DEPARTMENT set "
                + "DEPT_NAME = ?,"
                + "DEPT_NO = ?,"
                + "LOCATION = ?,"
                + "Where EMP_ID = ?";
        try {
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setInt(1, d.getDeptId());
            this.preStatement.setString(2, d.getDeptName());
            this.preStatement.setString(3, d.getDeptNo());
            this.preStatement.setString(4, d.getLocation());

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
            String mySQL = "DELETE FROM DEPARTMENT WHERE DEPT_ID = " + id;
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
}

