package addressbook;

import db.DataBase;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Operations {

    Scanner sc = new Scanner(System.in);
    DAO dao = new DAO();
    public static String name;
    public static Long mobileNum1;
    public static String emailid1;
    public static int option;
    public static int option1;
    public static String newName;
    public static String enteredName;
    public static int actualChanges;
    public static long enteredMobileNum;
    public static String enteredEmailId;
    public static long newMobileNum;
    public static String newEmailId;
    
//createcontact

    public void nameCreation(Employee e) {
        //Operations operation = new Operations();
        System.out.print("1.enter your name: ");
        String name = sc.next();
        if (name.length() <= 20) {
            e.setName(name);
        } else {
            System.out.println("\n\t\t\t\t\t\t\t[enter upto 20characters only]");
            this.nameCreation(e);
        }
    }

    public void mobileNumCreation(Employee e) {

        System.out.print("2.enter your mobile number: ");
        long mobileNum = sc.nextLong();
        if (String.valueOf(mobileNum).length() == 10) {
            e.setMobileNum(mobileNum);
        } else {
            System.out.println("\n\t\t\t\t\t\t\t[please enter 10digits number]");
            this.mobileNumCreation(e);
        }
    }

    public void emailIdCreation(Employee e) {
        System.out.print("3.enter your emailid: ");
        e.setEmailId(sc.next());
    }

    public void createContact() {
//        Operations operation = new Operations();
        //Employee e = new Employee();

        //e.setName(sc.next();
        this.nameCreation(dao.e);
        this.mobileNumCreation(dao.e);
        this.emailIdCreation(dao.e);
        DataBase.storeInDB(dao.e);
        DAO.employeeList.add(dao.e);

        System.out.println(DAO.employeeList);
        DAO.writeObject();
        AddressBook.mainMenu();

    }
//delete

    public int deleteMenu() {
        System.out.println("delete by\n1.name\n2.phno\n3.emailid");

        int option = sc.nextInt();
        return option;
    }

    public void deleteContact() {

        int i = 0;

        int option = this.deleteMenu();

        if (option == 1) {
            System.out.print("please enter the name: ");
            name = sc.next();
            String name1 = null;

            for (i = 0; i < DAO.employeeList.size(); i++) {
                name1 = DAO.employeeList.get(i).getName();
                if (name1.equals(name)) {
                    DAO.employeeList.remove(i);
                    System.out.println("\n\t\t\t\t\t\t\t[contact deleted]");

                    DAO.writeObject();

                }

            }
            DataBase.deleteFromDB(option);
            for (Employee e : DAO.employeeList) {
                System.out.print("\n\t\t\t\t\t\t\tavailable contacts are: [" + e.getName() + e.getMobileNum() + e.getEmailId() + "]");

            }
            AddressBook.mainMenu();
        }

        if (option == 2) {
            System.out.print("please enter the mobilenum: ");
            mobileNum1 = sc.nextLong();
            Long mobileNum;
            DataBase.deleteFromDB(option);

            for (i = 0; i < DAO.employeeList.size(); i++) {
                mobileNum = DAO.employeeList.get(i).getMobileNum();
                if (mobileNum1 == mobileNum) {
                    System.out.println(DAO.employeeList.remove(i));
                    DAO.writeObject();
                }
            }
            for (Employee e : DAO.employeeList) {
                System.out.print("\n\t\t\t\t\t\t\tavailable contacts are:[ " + e.getName() + e.getMobileNum() + e.getEmailId() + "]");
            }
            AddressBook.mainMenu();
        }
        if (option == 3) {
            System.out.print("please enter the emailid:");
            emailid1 = sc.next();
            String emailid;

            for (i = 0; i < DAO.employeeList.size(); i++) {
                emailid = DAO.employeeList.get(i).getEmailId();
                if (emailid1.equals(emailid)) {
                    System.out.println(DAO.employeeList.remove(i));
                    DAO.writeObject();
                }
            }
            for (Employee e : DAO.employeeList) {
                System.out.print("\n\t\t\t\t\t\t\tavailable contacts are:[" + e.getName() + e.getMobileNum() + e.getEmailId() + "]");
            }
            AddressBook.mainMenu();
        }

    }

    //edit method
    public int editMenu() {
        System.out.println("Edit by\n1.name\n2.phno\n3.emailid");
        int option = sc.nextInt();
        return option;
    }

    public void choose(Employee edit) {
        System.out.println("\tplease choose what you want to change\n\t1.name\n\t2.mobileNum\n\t3.emailId or \n\t4.for exit");
        actualChanges = sc.nextInt();

        switch (actualChanges) {
            case 1:
                System.out.print("\tplease re-enter name:");
                newName = sc.next();
                DataBase.UpdateTable(actualChanges,option1);
                edit.setName(newName);
                System.out.print("\n\t\t\t\t\t\t\tNew data is[" + edit.getName() + " " + edit.getMobileNum() + " " + edit.getEmailId() + "]");
                DAO.writeObject();
                this.choose(edit);
                break;
            case 2:
                System.out.print("\tplease re-enter mobileNum: ");
                long newMobileNum = sc.nextLong();
                DataBase.UpdateTable(actualChanges,option1);
                edit.setMobileNum(newMobileNum);
                System.out.print("\n\t\t\t\t\t\t\tNew data is[" + edit.getName() + " " + edit.getMobileNum() + " " + edit.getEmailId() + "]");
                DAO.writeObject();
                this.choose(edit);
                break;
            case 3:
                System.out.print("\tplease re-enter emailId: ");
                String newEmailId = sc.next();
                DataBase.UpdateTable(actualChanges,option1);
                edit.setEmailId(newEmailId);
                System.out.print("\n\t\t\t\t\t\t\tNew data is[" + edit.getName() + edit.getMobileNum() + edit.getEmailId() + "]");
                DAO.writeObject();
                this.choose(edit);
                break;
            case 4:
                AddressBook.mainMenu();
            default:
                System.out.print("\tplease enter [1-3]only: ");
                break;
        }
        // edit.setName(name);

        System.out.print("\n\t\t\t\t\t\t\tthe new data is [" + edit.getName() + " " + edit.getMobileNum() + " " + edit.getEmailId() + "]");

    }

    public void choose() {
        System.out.println("\tplease choose what you want to change\n\t1.name\n\t2.mobileNum\n\t3.emailId or \n\t4.for exit");
        actualChanges = sc.nextInt();

        switch (actualChanges) {
            case 1:
                System.out.print("\tplease re-enter name:");
                newName = sc.next();
                DataBase.UpdateTable(actualChanges,option1);

                this.choose();
                break;
            case 2:
                System.out.print("\tplease re-enter mobileNum: ");
                newMobileNum = sc.nextLong();
                DataBase.UpdateTable(actualChanges,option1);
                
                this.choose();
                break;
            case 3:
                System.out.print("\tplease re-enter emailId: ");
                newEmailId = sc.next();
                DataBase.UpdateTable(actualChanges,option1);
                
                this.choose();
                break;
            case 4:
                AddressBook.mainMenu();
            default:
                System.out.print("\tplease enter [1-3]only: ");
                break;
        }
        // edit.setName(name);

        

    }

    public void editContact() {

        // Operations operation = new Operations();
        option1 = this.editMenu();

        if (option1 == 1) {
            System.out.print("\tplease enter the name: ");
            enteredName = sc.next();
            Employee edit = null;
            for (Employee e : DAO.employeeList) {
                if (e.getName().equals(enteredName)) {
                    edit = e;
                    break;
                }

            }
            if(edit!=null){
            this.choose(edit);
            }else
                this.choose();  
        }

        if (option1 == 2) {

            System.out.print("\tplease enter mobileNum: ");
            enteredMobileNum = sc.nextLong();
            Employee edit1 = null;
            for (Employee e : DAO.employeeList) {
                if (e.getMobileNum() == enteredMobileNum) {
                    edit1 = e;
                    break;
                }

            }
             if(edit1!=null){
            this.choose(edit1);
            }else
                this.choose();  

        }
        if (option1 == 3) {
            System.out.print("\tplease enter emailId:");
            String enteredEmailId = sc.next();
            Employee edit2 = null;
            for (Employee e : DAO.employeeList) {
                if (e.getEmailId().equalsIgnoreCase(enteredEmailId)) {
                    edit2 = e;
                    break;
                }

            }
            if(edit2!=null){
            this.choose(edit2);
            }else
                this.choose();  
        }

    }

    //list
    public void list() {
        try {
            DAO.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Employee e : DAO.employeeList) {
            System.out.println("\n\t\t\t\t\t\t\tavailable contacts are: [" + e.getName() + " " + e.getMobileNum() + " " + e.getEmailId() + "]");
        }
        DataBase.retrieveFromDB();
        AddressBook.mainMenu();
    }

    //search operation
    public int searchMenu() {
        System.out.println("search by\n1.name\n2.phno\n3.emailid");

        int option = sc.nextInt();
        return option;
    }

    public void search() {

        int option = this.searchMenu();
        int i = 0;
        if (option == 1) {
            System.out.print("\tplease enter the name: ");
            String name = sc.next();
            String name1 = null;

            for (i = 0; i < DAO.employeeList.size(); i++) {
                name1 = DAO.employeeList.get(i).getName();
                if (name1.equals(name)) {
                    System.out.println(DAO.employeeList.get(i));

                }
            }
            AddressBook.mainMenu();

        }
        if (option == 2) {
            System.out.print("\tplease enter the phno: ");
            long mobileNum;
            long mobileNum1 = sc.nextLong();
            for (i = 0; i < DAO.employeeList.size(); i++) {
                mobileNum = DAO.employeeList.get(i).getMobileNum();
                if (mobileNum1 == mobileNum) {
                    System.out.println(DAO.employeeList.get(i));
                }
            }
            AddressBook.mainMenu();
        }
        if (option == 3) {

            System.out.print("\tplease enter the emailid: ");

            String emailid;
            String emailid1 = sc.next();
            for (i = 0; i < DAO.employeeList.size(); i++) {
                emailid = DAO.employeeList.get(i).getEmailId();
                if (emailid1.equals(emailid)) {
                    System.out.println(DAO.employeeList.get(i));
                }
            }
            AddressBook.mainMenu();
        }
    }

    //exit method
    public void exit() {
        AddressBook.mainMenu();
    }

}
