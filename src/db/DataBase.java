package db;

import addressbook.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    public static String url = DAO.propertiesFileConfig("url");
    public static String uname = DAO.propertiesFileConfig("uname");
    public static String pwd = DAO.propertiesFileConfig("pwd");
    public static String driverName=DAO.propertiesFileConfig("jdbc_driver");

    public static void dbConnection(Employee e) {
        Connection con = null;
        PreparedStatement pst = null;
        try {

            Class.forName(driverName);
            con = DriverManager.getConnection("url", "uname", "pwd");
            String query = "insert into contacts values(?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, e.getName());
            pst.setLong(2, e.getMobileNum());
            pst.setString(3, e.getEmailId());
            int i=pst.executeUpdate();
            if(i!=0){
                System.out.println("record inserted");
            }else
                System.out.println("record not inserted");
                
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void storeInDB(Employee e) {
        String name = e.getName();
        dbConnection(e);
        e.getMobileNum();
        e.getEmailId();

    }
}
