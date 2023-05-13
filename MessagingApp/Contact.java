

import java.util.ArrayList;

public class Contact extends Message{


    public static Contact instance;
    public static Contact setInstance(String n){

        if(instance == null){
            instance = new Contact(n);
        }

        return instance;
    }
   private ArrayList<String> contact = new ArrayList<>();

   public void addContact(String contact) {
       this.contact.add(contact);
   }

   public void showContact(){
    if(contact.size() > 0){
        System.out.println("Contact List:");
    }else{
        System.out.println("You have no contact! Add one now.");
    }
    
    int n = 1;
    for(int i = 0; i < contact.size(); i++){
        System.out.println(n+". "+contact.get(i));
        n++;
    }
   }

   public void searchContact(String keyword){
    ArrayList<String> a = new ArrayList<>();
    int n = 1;
    boolean hasFound = true;
    boolean noFound = true;
    for(int i = 0; i < contact.size(); i++){

        for(int j = 0; j < keyword.length(); j++){
            if(contact.get(i).length() < keyword.length()){
                hasFound = false;
                break;
            }
            if(Character.toLowerCase(contact.get(i).charAt(j)) != Character.toLowerCase(keyword.charAt(j))){
                hasFound = false;
                break;
            }
        }
        if(hasFound){
            a.add(contact.get(i));
            n++;
            noFound = false;
        }
        hasFound = true;
    }


    if(noFound){
        System.out.println("No result found.");
    }else{
        System.out.println("Found Contact: ");
        n = 1;
        for(int i = 0; i < a.size(); i++){
            System.out.println(n+". "+a.get(i));
            n++;
        }
    }
 
   }

 
   public void deleteContact(int n){

    this.contact.remove(n-1);
    System.out.println("Contact has been deleted");
   }

   public void deleteContact(String n){
    boolean found = false;
    int index = 0;
    for(int i =0; i < contact.size(); i++){
        if(n.toLowerCase().equals(contact.get(i).toLowerCase())){
            index = i;
            found = true;
        }
    }
    if(!found){
        System.out.println("Not in contact list!");
    }else{
        this.contact.remove(index);
        System.out.println("Contact has been deleted");
    }
    
   }

   private Contact(String n){
    this.setName(n);
}

}