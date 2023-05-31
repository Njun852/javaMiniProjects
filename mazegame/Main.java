import java.util.Scanner;

public class Main {
    static int width;
    static int height;
    static Scanner scan;
    static Game game;
    static GameScreen screen;
    public static void main(String[] args) {
        game = SaveSystem.loadFile();
        scan = new Scanner(System.in);
        if(game.getUsername() == null){
            System.out.println("Hello!");
            newUser();
        }else{
            System.out.println("Welcome back "+game.getUsername()+"!");
        }      
        screen = game.getScreen();
        mainMenu();
        scan.close();
        
    }
    public static void linebreak(){
        System.out.print("\n");
    }
    public static void mainMenu(){
        screen.mainMenu();
        String input = scan.next();
        switch(input){
            case "1":
            linebreak();
            playMenu();
            break;
            case "2":
            linebreak();
            profileMenu();
            break;
        }
        SaveSystem.saveFile(game);
    }

    public static void profileMenu(){
        screen.profileMenu();
        System.out.println("\n1. Back");
        scan.next();
        mainMenu();
    }
    public static void playMenu(){
        screen.playMenu();
        String input = scan.next();
        switch(input){
            case "1":
            width = 15;
            height = 10;
            break;
            case "2":
            width = 35;
            height = 20;
            break;
            case "3":
            width = 40;
            height = 25;
            break;
            case "4":
            width = 100;
            height = 45;
            break;
            default:
            linebreak();
            playMenu();
            break;
        }
        startGame(width, height);
    }

    public static void startGame(int width, int height){
        game.playGame(width, height);
        Timer timer = new Timer();
        timer.start();
        while(!game.maze.iSOver()){
            game.maze.movePlayer(scan.next());
            game.maze.renderMaze();
        }
        System.out.println("Maze Solved!");
        timer.stop();
        timer.showTime();
        game.setSolvedMazes(game.getSolvedMazes()+1);
        if(timer.getTimer() < game.getBestTime() || game.getBestTime() == -1){
            game.setBestTime(timer.getTimer());
        }
        linebreak();
        screen.gameOverMenu();
        String input = scan.next();
        switch(input){
            case "1":
            linebreak();
            playMenu();
            break;
            default:
            linebreak();
            mainMenu();
        }
    }

    public static void newUser(){

        System.out.print("Please enter your username: ");
        String username = scan.nextLine();
        if(username.trim().equals("")){
            System.out.println("\nInvalid username try again!");
            newUser();
        }else{
            game.setUsername(username);
            System.out.println("\nWelcome "+game.getUsername()+"!\n");
        }
        
    }
}
