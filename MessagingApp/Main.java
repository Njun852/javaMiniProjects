

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    static String[] menuOption = {"Manage Contacts", "Messages", "Quit"};
    static String[] contactOption = {"Show all contacts", "Add a new contact",
        "Search for a contact", "Delete a contact","Go back to the previous menu"};

    static String[] messageOption = {"See the list of all messages", "Send a new message", "Go back to the previous menu"};

        
    static int n = 1;
    private static void contactOption(){
   
        for(int i = 0; i < contactOption.length; i++){
            System.out.println(n+". "+contactOption[i]);
            n++;
        }
        n = 1;
    }

    private static void menuOption(){
        for(int i = 0; i < menuOption.length; i++){
            System.out.println(n+". "+menuOption[i]);
            n++;
        }
        n = 1;
    }

    private static void messageOption(){
        for(int i = 0; i < messageOption.length; i++){
            System.out.println(n+". "+messageOption[i]);
            n++;
        }
        n = 1;
    }

    public static void main(String[] args) {
        
   
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome! What is your name?");
        Contact contact = Contact.setInstance(scan.nextLine());
        System.out.println("Hello! "+contact.getName());

        boolean hasQuit = false;
        
        while(!hasQuit){
            System.out.println("Select:");
            menuOption();
            int choice = scan.nextInt();
            switch(choice){

            case 1:
            boolean exitContact = false;
            while(!exitContact){
                System.out.println("Select:");
                contactOption();
                choice = scan.nextInt();
                switch(choice){
                    case 1:
                    
                    contact.showContact();
                    break;
                    case 2:
                    scan.nextLine();
                    System.out.println("Please enter a new contact: ");
                    String newContact = scan.nextLine();
                    contact.addContact(newContact);
                    System.out.println("Contact added!");
                    break;
                    case 3:
                    scan.nextLine();
                    System.out.println("Search for a contact: ");
                    String search = scan.nextLine();
                    
                    contact.searchContact(search);
                    break;
                    case 4:
                    System.out.println("Please enter the name or number in the list to remove: ");
                    try{
                        contact.deleteContact(scan.nextInt());
                    }catch(InputMismatchException e){
                        contact.deleteContact(scan.nextLine());
                    }catch(IndexOutOfBoundsException e){
                        System.out.println("Contact Doesn't exist!");
                    }
                    break;
                    case 5:
                    exitContact = true;
                    break;
                }
            }
            break;
            
            case 2:
            boolean exitMessage = false;
            while(!exitMessage){
                System.out.println("Select:");
                messageOption();
                choice = scan.nextInt();
                switch(choice){
                    case 1:
                    contact.showMessages();
                    break;
                    case 2:
                    
                    System.out.println("Who are you going to send this?");
                    String to = scan.next();
                    to += scan.nextLine();
                    System.out.println("Please enter your message:");
                    String msg = scan.nextLine();
                    contact.sendMessage(msg, to);
                    System.out.println("Message sent to "+to+"!");
                    break;
                    case 3:
                    exitMessage = true;
                    break;
                }
            }
            break;
            case 3:
            hasQuit = true;
            break;
            }

        }

        System.out.println("Closing...");
        System.out.println("Closed.");
        scan.close();
    }
}
