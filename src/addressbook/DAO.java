package addressbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {

    static ArrayList<Employee> employeeList = new ArrayList<Employee>();
    Employee e = new Employee();

    //readObject()method 
    public static void readObject() throws IOException {
        FileInputStream readStream = null;
        ObjectInputStream employeeObjectReading = null;
        try {
            readStream = new FileInputStream("D:\\AdityaC\\Projects\\JavaProjects\\Demo\\ContactsData.txt");
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
            fos = new FileOutputStream("D:\\AdityaC\\Projects\\JavaProjects\\Demo\\ContactsData.txt");

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
