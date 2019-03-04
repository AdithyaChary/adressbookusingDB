package addressbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {

    public static ArrayList<Employee> employeeList = new ArrayList<Employee>();
    public static Employee e = new Employee();
    static String value = null;
  static Properties properties = new Properties();
   
          
          static  FileInputStream fis = null;
           
                
    public static String propertiesFileConfig(String key) {
        try {
            fis=new FileInputStream("D:\\AdityaC\\Projects\\JavaProjects\\Demo\\AddressBook\\info.properties");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            properties.load(fis);
        } catch (IOException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                switch (key) {
                    case "filepath":
                        value = properties.getProperty("filepath");
                        break;

                    case "url":
                        value = properties.getProperty("url");
                        System.out.println(value);
                        break;
                    case "uname":
                        value = properties.getProperty("uname");
                        break;
                    case "pwd":
                        value = properties.getProperty("pwd");
                        break;
                    case "jdbc_driver":
                        value = properties.getProperty("jdbc_driver");
                        break;
                    case "databaseName":
                        value = properties.getProperty("databaseName");
                        break;

                }
               

         return value;
    }

    //readObject()method 
    public static void readObject() throws IOException {
        FileInputStream readStream = null;
        ObjectInputStream employeeObjectReading = null;
        try {
         
            value=DAO.propertiesFileConfig("filepath");
            System.out.println(value);
            readStream = new FileInputStream(value);
            employeeObjectReading = new ObjectInputStream(readStream);

            employeeList = (ArrayList<Employee>) employeeObjectReading.readObject();
            if (employeeList.isEmpty()) {
                System.out.println("\n\t\t\t\t\t\t\t[No Data in the File]");
            }
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //writeObject()method
    public static void writeObject() {
        FileOutputStream fos = null;
        ObjectOutputStream employeeObjectWriting = null;
        try {
            fos = new FileOutputStream(value);

            employeeObjectWriting = new ObjectOutputStream(fos);

            employeeObjectWriting.writeObject(employeeList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                fos.close();

            } catch (IOException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                employeeObjectWriting.close();
            } catch (IOException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
