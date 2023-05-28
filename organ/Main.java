package organ;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        String where = "menu";
        Boolean isDone = false;
        Organ Lefteye = new Organ("Left Eye", "Normal", "Color", "Brown");
        Lefteye.addoption("Close Eye");

        Organ Righteye = new Organ("Right Eye", "Blind", "Color", "Brown");
        Righteye.addoption("Close Eye");
       
        Organ heart = new Organ("Heart", "Normal", "Heartrate", "65");
        heart.addoption("Change heartrate");
        heart.addbehaviour("Enter new heartrate:");

        Organ stomach = new Organ("Stomach", "Hungry", "Size", "Small");
        stomach.addoption("Feed");
        stomach.addbehaviour("Eating....");

        Scanner scan = new Scanner(System.in);
        Organ body = new Organ();
        body.addoption(Lefteye.getName());
        body.addoption(Righteye.getName());
        body.addoption(heart.getName());
        body.addoption(stomach.getName());

        while(!isDone){
            if(where.equals("menu")){
                System.out.println("Choose an organ:");
                body.showoptions();
                int n = scan.nextInt();
                if(n == 1){
                    Lefteye.details();
                    where = "Lefteye";
                }
                else if(n == 2){
                    Righteye.details();
                    where = "Righteye";
                }else if(n == 3){
                    heart.details();
                    heart.showoptions();
                    where = "heart"; 
                }else if(n == 4){
                    stomach.details();
                    stomach.showoptions();
                    where = "stomach";
                }
            }else if(where.equals("Lefteye")){
                Lefteye.showoptions();
                    where = "menu";
                
            }else if(where.equals("Righteye")){
                Lefteye.showoptions();
                    where = "menu";
                
            }else if(where.equals("heart")){
                int n = scan.nextInt();
               
                    heart.showbehaviour(n);
                    int y = scan.nextInt();
                    heart.setDetail(Integer.toString(y));
                    System.out.println("Heartrate is now changed to "+heart.getDetail());
                   where = "menu";
                }
            else if(where.equals("stomach")){
                int n = scan.nextInt();
                stomach.showbehaviour(n);
                where = "menu";
            }
        }
        scan.close();
    }
}
