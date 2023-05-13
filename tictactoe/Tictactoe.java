import java.util.ArrayList;
import java.util.Scanner;
class Tictactoe{

    public static void main(String args[]){
        char toes[] = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        boolean gameOver = false;
        int i = 0;
        Scanner scan = new Scanner(System.in);

        boolean currentMove = true;
        while(!gameOver){
            drawScreen(toes);
            System.out.println(" ");
            System.out.println(" ");
            if(currentMove){
                System.out.println("Player 1's turn:");
            }else{
                System.out.println("Player 2's turn:");
            }
            
            Character toe;
            if(currentMove){
                toe = 'X';
                currentMove = false;
            }else{
                toe = 'O';
                currentMove = true;
            }
            int index = move(scan.next());
            if(toes[index] == ' '){
                toes[index] = toe;
            }else{
                System.out.println("\nSlot already taken!");
                currentMove = !currentMove;
                continue;
            }
            gameOver = checkMatch(toes);
            if(i > 7){
                break;
            } 
            i+=1;
        }
        
        drawScreen(toes);
        System.out.println("\n");
        if(gameOver){
            if(!currentMove){
                System.out.println("Player 1 Win!");
            }else if(currentMove){
                System.out.println("Player 2 Win!");
            }
        }else{
            System.out.println("Draw!");
        }
        scan.close();
        
    }

    public static void drawScreen(char[] t){

        char[] toes = t;
        System.out.println(" ");
        char[] letters = {'a', 'b', 'c'};
        System.out.println("    1   2   3");
        int index = 0;
        for(int i = 0; i < 9; i++){
            String cell;
            if(i == 1|| i == 4|| i == 7){
                cell = ""+toes[i];
            }else{
                cell = " | "+toes[i]+" | ";
            }
            String letter = "";
            if(i==0||i==3||i==6){
                letter = letters[index]+"";
                index+=1;
            }
            if(i%3==0 && i!= 0){
                System.out.print("\n"+letter+cell);
            }else{
                System.out.print(letter+cell);
            }
        }
    }

    public static int move(String pos){
        char letter = pos.charAt(0);
        ArrayList<Character> letters = new ArrayList<>();
        letters.add('a');
        letters.add('b');
        letters.add('c');
        int findex = 0;
        int sindex = Character.getNumericValue(pos.charAt(1))-1;
        for(int i = 0; i < letters.size(); i++){
            if(letter == letters.get(i)){
                findex = letters.indexOf(letters.get(i))*3;
            }
        }
        return findex+sindex;
    }

    public static boolean checkMatch(char toes[]){
        boolean hasMatched = false;
        for(int i = 0; i < toes.length; i++){
            try{
                if(toes[i] != ' '){
                  if(i < 5 && i > 2){
                    if(toes[i] == toes[i+3]&& toes[i] == toes[i-3]){
                        hasMatched = true;
                        break;
                    }
                  }
                  if(i == 1|| i == 4|| i == 7){
                    if(toes[i]== toes[i+1]&& toes[i] == toes[i-1]){
                        hasMatched = true;
                        break;
                    }
                  }
                  if(i == 4){
                    if(toes[i] == toes[i-4]&& toes[i] == toes[i+4]){
                        hasMatched = true;
                        break;
                    }
                  }
                }

            }catch(IndexOutOfBoundsException e){

            }
        
        }
        return hasMatched;
    }
}