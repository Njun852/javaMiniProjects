
import java.util.ArrayList;


public class Message{
    
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> sentList = new ArrayList<>();
   
    private String name;

   

   
    public void showMessages(){
        System.out.println("All Messages:");
        int n = 1;
        for(int i = 0; i < messages.size(); i++){
            System.out.println(n+". "+messages.get(i)+"\nFrom: "+this.getName()+" To: "+sentList.get(i));
            n++;
        }
    }

    public void sendMessage(String msg, String to){

        messages.add(msg);
        sentList.add(to);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
