package javaClasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connectionEstablishment 
{
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=project;integratedSecurity=true";
    private static Connection con;
    public Connection createConnection()
    {
        try
        {
            con = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            Logger.getLogger(connectionEstablishment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
