package addressbook;

import java.util.*;

public class AddressBook {
    static Scanner scanner = new Scanner(System.in);
            
    public static int inputValidation(){
        Scanner sc = new Scanner(System.in);
        int j=0;
        try{
            j=sc.nextInt();
            
        }catch(InputMismatchException e){
            System.out.println("\t(please enter only numbers)");
            AddressBook book=new AddressBook();
            book.mainMenu();
        }
      return j;
                }         

    public static void mainMenu() {
        Employee employee = new Employee();
        Operations operation=new Operations();
        
       
        System.out.println("1.Create Contact\n2.Edit Contact\n3.Search\n4.Delete Contact\n5.List\n6.Exit");
         System.out.print("\t(please select one option):");
        int i = inputValidation();
        if(i>6 ||i<0){
            System.out.println("\t(please enter [1-6] only)");
            AddressBook book=new AddressBook();
            book.mainMenu();
        }else{
        switch (i) {
            default:
                operation.exit();
                break;
            case 1:
                operation.createContact();
                break;
            case 2:
                operation.editContact();
                break;
            case 3:
                operation.search();
                break;
            case 4:
                operation.deleteContact();
                break;
            case 5:
                operation.list();
                break;
            case 6:
                operation.exit();
                break;
            
        }
        }
    }

    public static void main(String[] args) {
        System.out.print("********************************************************************************************");
        System.out.println("******************************************************************************************");
        System.out.println("\t\t\t[Welcome to Erwin Contacts]");
        AddressBook.mainMenu();

    }
}
