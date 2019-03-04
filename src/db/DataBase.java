package db;

import addressbook.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBase {

    static String url;
    static String uname;
    static String pwd;
    static String driverName;
    static String updateQuery;

    public static Connection dbConnection() {
        Connection con = null;

        url = DAO.propertiesFileConfig("url");
        uname = DAO.propertiesFileConfig("uname");
        pwd = DAO.propertiesFileConfig("pwd");
        driverName = DAO.propertiesFileConfig("jdbc_driver");

        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, uname, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    public static void storeInDB(Employee e) {
        PreparedStatement pst = null;
        Connection con = null;
        con = dbConnection();

        try {

            String query = "insert into contacts values(?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, e.getName());
            pst.setLong(2, e.getMobileNum());
            pst.setString(3, e.getEmailId());
            int i = pst.executeUpdate();
            if (i != 0) {
                System.out.println("record inserted");
            } else {
                System.out.println("record not inserted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteFromDB(int option) {
        Connection con = dbConnection();
        Statement st = null;
        String query = null;
        try {
            if (option == 1) {
                query = "delete  from contacts where  name='" + Operations.name + "'";
            }
            if (option == 3) {
                query = "delete  from contacts where  name='" + Operations.emailid1 + "'";
            }
            if (option == 2) {
                query = "delete from contacts where mobileNum='" + Operations.mobileNum1 + "'";
            }
            st = con.createStatement();
            int i = st.executeUpdate(query);
            if (i != 0) {
                System.out.println("record deleted");
            } else {
                System.out.println("record not deleted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String updateByName(int updateByNameVar) {
        if (updateByNameVar == 1) {
            updateQuery = "update contacts set name='" + Operations.newName + "' where name='" + Operations.enteredName + "'";

        }
        if (updateByNameVar == 2) {
            updateQuery = "update contacts set mobileNum=" + Operations.newMobileNum + " where name=" + Operations.enteredName;
        }
        if (updateByNameVar == 3) {
            updateQuery = "update contacts set emailId='" + Operations.newEmailId + "'  where name='" + Operations.enteredName + "'";

        }
        return updateQuery;
    }
    public static String updateByMobileNum(int updateByMobileNumVar) {
        if (updateByMobileNumVar == 1) {
            updateQuery = "update contacts set name='" + Operations.newName + "' where mobileNum='" + Operations.enteredMobileNum + "'";

        }
        if (updateByMobileNumVar == 2) {
            updateQuery = "update contacts set mobileNum=" + Operations.newMobileNum + " where mobileNum=" + Operations.enteredMobileNum;
        }
        if (updateByMobileNumVar == 3) {
            updateQuery = "update contacts set emailId='" + Operations.newEmailId + "'  where mobileNum='" + Operations.enteredMobileNum + "'";

        }
        return updateQuery;
    }
    public static String updateByEmailId(int updateByEmailIdVar) {
        if (updateByEmailIdVar == 1) {
            updateQuery = "update contacts set name='" + Operations.newName + "' where emailId='" + Operations.enteredEmailId + "'";

        }
        if (updateByEmailIdVar == 2) {
            updateQuery = "update contacts set mobileNum=" + Operations.newMobileNum + " where emailId=" + Operations.enteredEmailId;
        }
        if (updateByEmailIdVar == 3) {
            updateQuery = "update contacts set emailId='" + Operations.newEmailId + "'  where emailId='" + Operations.enteredEmailId + "'";

        }
        return updateQuery;
    }

    public static void UpdateTable(int updateOptionDB,int option1DB) {
        Statement st = null;
        Connection updateCon = dbConnection();
       if(option1DB==1)
        updateQuery = updateByName(updateOptionDB);
       if(option1DB==2)
        updateQuery=updateByMobileNum(updateOptionDB);
       if(option1DB==3)
        updateQuery=updateByEmailId(updateOptionDB);
        try {
            st = updateCon.createStatement();
            int updateRecords = st.executeUpdate(updateQuery);
            if (updateRecords != 0) {
                System.out.println(updateRecords + "  are updated");
            } else {
                System.out.println(updateRecords + "  found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void retrieveFromDB(){
     Connection retrieveCon=dbConnection();
     Statement st=null;
     String retrieveQuery=null;
     ResultSet rs=null;
     try{
         retrieveQuery="select * from contacts";
         st=retrieveCon.createStatement();
         rs=st.executeQuery(retrieveQuery);
         while(rs.next()){
             
             System.out.println(rs.getString(1)+" "+rs.getLong(2)+" "+rs.getString(3));
             DAO.e.setName(rs.getString(1));
                DAO.e.setMobileNum(rs.getLong(2));
                DAO.e.setEmailId(rs.getString(3));
                DAO.employeeList.add(DAO.e);
                System.out.println( DAO.employeeList);
                
         }
     }catch(SQLException e){
         e.printStackTrace();
     }
     
    }

}
