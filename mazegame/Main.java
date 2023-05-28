package mazegame;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        //size of the maze
        int width = 40;
        int height = 20;

        Maze myMaze = new Maze(width, height);
        myMaze.createRandomMaze();

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter any key to start");
        while(!myMaze.iSOver()){
            
            String direction = scan.next();
            myMaze.movePlayer(direction);
            myMaze.renderMaze();
        }

        System.out.println("Maze solved!");
        scan.close();
    }
}
