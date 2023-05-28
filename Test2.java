import java.util.Scanner;

public class Test2 {
    
    public static String reverse(String txt){

        if(txt.length() < 2){
            return txt;
        }
        int index = txt.length()-1;
        String currentChar = txt.charAt(index)+"";
        String nextString = "";
        int i = 0;
        while(i < index){
            nextString += txt.charAt(i);
            i++;
        }
        return currentChar+reverse(nextString); 
    }
    // public static void main(String args[]){

    //     askForWord();
    // }

    public static void askForWord(){
        String word;
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the word: ");
        word = scan.nextLine().trim();

        if(word.equals("") || hasForbiddenChars(word)){
            System.out.println("\nPlease enter a valid input!");
            askForWord();
        }

        if(word.equals(reverse(word))){
            System.out.println("Word: "+word);
            System.out.println("Word in reverse: "+reverse(word));
        }else{
            System.out.println("Not matched. Not palindrome.");
        }
        scan.close();
    }

    public static boolean hasForbiddenChars(String word){
        boolean hasForbidden = false;
        char allowedCharacters[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                                   'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' '};

        for(int i = 0; i < word.length(); i++){
            int chance = 0;
            for(int j = 0; j < allowedCharacters.length; j++){
                if(word.charAt(i) == allowedCharacters[j]){
                    hasForbidden = false;
                }else{
                    chance += 1;
                }
            }
            if(chance >= allowedCharacters.length){
                hasForbidden = true;
                break;
            }
        }
        return hasForbidden;
    }
}
