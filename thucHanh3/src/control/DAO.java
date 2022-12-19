package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {
	Connection con;
    String hostName = "localhost";

    String dbName = "mysql_database";
    String userName = "root";
    String password = "Quangliem3173";

    public DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName+"?useSSL=false"; 
            Connection conn = DriverManager.getConnection(connectionURL, userName,
                    password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
