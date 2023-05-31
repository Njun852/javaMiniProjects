import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveSystem{
    public static File file = new File("save.txt");

    public static Game loadFile(){
        Game mygame = new Game();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            mygame.setUsername(line);
            line = reader.readLine();
            mygame.setSolvedMazes(Integer.parseInt(line));
            line = reader.readLine();
            mygame.setBestTime(Integer.parseInt(line));
            reader.close();
        } catch (IOException e) {
         
        }
       
        return mygame;
    }

    public static void saveFile(Game game){
        try {
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String username = game.getUsername()+"\n";
            String mazeSolved = game.getSolvedMazes()+"\n";
            String bestTime = game.getBestTime()+"";
            writer.write(username+mazeSolved+bestTime);
            writer.close();
        } catch (IOException e) {
        }
       
    }
}