package addressbook;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressBook {

    static Scanner scanner = new Scanner(System.in);

    public static int inputValidation() {
        Scanner sc = new Scanner(System.in);
        int j = 0;
        try {
            j = sc.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("\t(please enter only numbers)");
            AddressBook book = new AddressBook();
            book.mainMenu();
        }
        return j;
    }

    public static void mainMenu() {

        try {
            //Employee employee = new Employee();

            Operations operation = new Operations();

            System.out.println("1.Create Contact\n2.Edit Contact\n3.Search\n4.Delete Contact\n5.List\n6.Exit");
            System.out.print("\t(please select one option):");
            int i = inputValidation();

            if (i > 6 || i < 0) {
                System.out.println("\t(please enter [1-6] only)");

                AddressBook book = new AddressBook();
                book.mainMenu();
            } else {
                Operation constants[] = Operation.values();
                Operation operation1 = constants[i - 1];

                switch (operation1) {
                    default:
                        operation.exit();
                        break;
                    case CREATE_CONTACT:

                        operation.createContact();
                        DAO.readObject();
                        break;
                    case EDIT_CONTACT:
                        DAO.readObject();
                        operation.editContact();
                        break;
                    case CONTACT_SEARCH:
                        DAO.readObject();
                        operation.search();
                        break;
                    case DELETE_CONTACT:
                        DAO.readObject();
                        operation.deleteContact();
                        break;
                    case CONTACT_LIST:

                        operation.list();
                        break;
                    case EXIT_APPLICATION:

                        operation.exit();
                        break;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        System.out.print("********************************************************************************************");
        System.out.println("******************************************************************************************");
        System.out.println("\t\t\t[Welcome to Erwin Contacts]");
        AddressBook.mainMenu();

    }
}
