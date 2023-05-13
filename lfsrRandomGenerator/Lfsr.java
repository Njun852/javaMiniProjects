package lfsrRandomGenerator;
import java.util.ArrayList;

public class Lfsr{

    public static void main(String args[]) {
        int range = 100;
        String seed = "1001";
        int max_total = 1;
        
        int max_iteration = (int)Math.pow(2, seed.length())-1;
        int max_loc = 1;
        for(int i = 0; i < seed.length(); i++){
            max_total+=max_loc;
            if(max_loc == 1){
                max_loc+=1;
            }else{
                max_loc*=max_loc;
            }
        }
        for(int i = 0; i < max_iteration; i++){
            ArrayList<Integer> bytes = new ArrayList<>();
            for(int k = 0; k < seed.length(); k++){
                bytes.add(Character.getNumericValue(seed.charAt(k)));
            }
           double total = 0;
            int location = 1;
            for(int p = bytes.size()-1; p >= 0; p--){
                if(bytes.get(p) == 1){
                    total += location;
                }
             if(location == 1){
                location+=1;
             }else{
               location*=location;
             }   
            }
            System.out.println((int)Math.floor((Math.min(1,(total/(max_total))))*range));
            String updated = "";
            int a = Character.getNumericValue(seed.charAt(seed.length()-1));
            int b = Character.getNumericValue(seed.charAt(seed.length()-2));
            if(a == b){
                updated = "0";
            }else{
                updated = "1";
            }
            String temp = updated;
            for(int j = 0; j < seed.length()-1; j++){
                temp+= seed.charAt(j);
            }
            seed = temp;
        }
    }
}