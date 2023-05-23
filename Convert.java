public class Convert {




    public static String lowercase(String txt){
        String lowercased = "";
        for(int i = 0; i < txt.length(); i++){
            if((32+(int)txt.charAt(i)) > 64 && (32+(int)txt.charAt(i) < 123)){
                lowercased += (char)(32+(int)txt.charAt(i));
            }else{
                lowercased+=txt.charAt(i);
            }
        }

        return lowercased;
    }
    public static void main(String args[]){

        String txt = "HELLO GOODBYE";

    
        System.out.println("Text lowercased: "+lowercase(txt));
    
    }
}
