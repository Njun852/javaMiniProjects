import java.util.ArrayList;

public class Game {
    private String player = "()";
    private String username;
    private int bestTime = -1;
    private int solvedMazes = 0;
    private String path = "  ";
    private String wall = "██";
    private boolean gameOver = false;

    private GameScreen screen;
    Maze maze;
    public Game(){
        screen = new GameScreen(this);
    }

    private int[] playerPos = new int[2];

    public String getPath(){
        return path;
    }
    public String getWall(){
        return wall;
    }
    public String getPlayer(){
        return player;
    }
    public int[] getPlayerPos() {
        return playerPos;
    }
    public void setPlayerPos(int[] playerPos) {
        this.playerPos = playerPos;
    }
    public boolean iSOver(){
        return this.gameOver;
    }
    public void iSOver(boolean isOver){
        this.gameOver = isOver;
    }
    public void playGame(int w, int h){
        maze = new Maze(w, h);
        maze.createRandomMaze();
        maze.renderMaze();
    }
    public GameScreen getScreen() {
        return screen;
    }
    public String getUsername() {
        return username;
    }
    public int getBestTime() {
        return bestTime;
    }
    public int getSolvedMazes() {
        return solvedMazes;
    }
    public void setBestTime(int bestTime) {
        this.bestTime = bestTime;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setSolvedMazes(int solvedMazes) {
        this.solvedMazes = solvedMazes;
    }
}


class GameScreen{
    private Game game;
    public GameScreen(Game game){
        this.game = game;
    }
    public void mainMenu(){
        System.out.println("Main Menu:");
        makeList("Play", "Profile", "Quit Game");
    }
    public void playMenu(){
        System.out.println("Select difficulty:");
        makeList("Easy", "Normal", "Hard", "Extreme", "Back");
    }
    public void profileMenu(){
        System.out.println("Your Profile:");
        String time = Timer.getTime(game.getBestTime());
        String solvedmazes = game.getSolvedMazes()+"";
    
        if(game.getSolvedMazes() == 1){
            solvedmazes += " maze.";
        }else{
            solvedmazes += " mazes";
        }
        if(game.getSolvedMazes() == 0){
            solvedmazes = "No games played.";
        }
        if(game.getBestTime() == -1){
            time = "No games played";
        }
    
        makeList("Username: "+game.getUsername(), "Maze Solved: "+solvedmazes, "Best Time: "+time);
    }

    public void makeList(String ... list){
        for(int i = 0; i < list.length; i++){
            System.out.println("  "+(i+1)+": "+list[i]);
        }
    }
    public void gameOverMenu(){
        System.out.println("Select:");
        makeList("Play Again", "Main Menu");
    }
}

class Timer implements Runnable{
    private int timer;
    private boolean stopTimer = false;

    public static String getTime(int timer){
        String timeTaken = "";
        double seconds = timer;
        double minutes = seconds/60;
        if(Math.floor(minutes)> 0){
            timeTaken += (int)(Math.floor(minutes))+" minutes";
            seconds = (minutes-(Math.floor(minutes)))*60;
            if(Math.floor(seconds) > 0){
                timeTaken+= " and ";
            }else{
                timeTaken+= ".";
            }
        }
        if(Math.floor(seconds) >= 0){
            timeTaken += (int)(Math.floor(seconds))+" seconds.";
        }
        return timeTaken;
    }
    public void stop(){
        stopTimer = true;
        
    }
    public void showTime(){
        System.out.println("Time taken: "+getTime(timer));
    }

    public void run(){
        while(!stopTimer){
            timer+=1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
               System.out.println("Something happened");
            }   
        }
    }
    
    public void start(){
       
        Thread thread = new Thread(this);
        thread.start();
    }
public int getTimer() {
    return timer;
}
}

class Tools{

    public static int generateRandomIndex(int min, int max){

        return (int)Math.max(min, Math.floor(Math.random()*max));
    }

    public static int indexOfGreatestElement(double[] arr){
        double bestElement = arr[0];
        int bestIndex = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > bestElement){
                bestElement = arr[i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }

}


class Pathfinder extends Game{

    private String[][] maze;
    private int x;
    private int y;

    private String[] directions = {"top", "bottom", "left", "right"};
    private int[] directionPoints = {1, 1, 1, 1};

    //top bottom left right
    private int moves[][] = new int[4][2];
    private int edges[] = new int[4];
    private boolean hasThisMove[] = new boolean[4];
    private boolean comparator[] = new boolean[4];
    private int posIndex[] = {0, 1, 2, 3};
    private boolean hasNextMove;



    public Pathfinder(String[][] maze, int x, int y){
        this.maze = maze;
        this.x = x;
        this.y = y;

        int edges[] = {0, maze.length-1, 0, maze[0].length-1};
        this.edges = edges;

        boolean hasThisMoves[] = { y > edges[0]+1, y < edges[1]-1, x > edges[2]+1, x < edges[3]-1};
        this.hasThisMove = hasThisMoves;

        int moves[][] = {{x, y-1},{x, y+1}, {x-1, y}, {x+1, y}};
        this.moves = moves;
 
        this.comparator[0] = this.moves[0][1] > edges[0];
        this.comparator[1] = this.moves[1][1] < edges[1];
        this.comparator[2] = this.moves[2][0] > edges[2];
        this.comparator[3] = this.moves[3][0] < edges[3];

    }
    
    public void boundsCheck(){

        for(int i = 0; i < hasThisMove.length; i++){
            if(!hasThisMove[i]){
                directionPoints[posIndex[i]] = 0;
            }
        }
    
    }
    public void neighborPositionCheck(int[] direction, int index){

    boolean hasDirection = 
    this.maze[direction[1]][direction[0]].equals(this.getPath()) &&
    direction[0] != this.x && direction[1] != this.y;

        if(hasDirection){
            directionPoints[index] = 0;
        }
    }

    public void neighborCheck(){
        ArrayList<Neighbor> n = new ArrayList<>();
        final int maxNeighbors = 4;
        for(int j = 0; j < maxNeighbors; j++){
            if(comparator[j]){
                Neighbor neighbor = new Neighbor(moves[j], this, posIndex[j]);
                n.add(neighbor);
            }
        }
        for(int i = 0; i < n.size(); i++){
            Neighbor current = n.get(i);
            int index = current.getIndex();
     
            for(int j = 0; j < 4; j++){
            if(current.hasNextPosition()[j]){
                neighborPositionCheck(current.getPos()[j], index);
            }
         }
        }
    }

    public boolean hasAPath(int direction[], int edge, int index){
        boolean hasAPath = false;
        int posIndex = 0;
        if(index < 2){
            posIndex = 1;
        }

        boolean isAPath =  direction[posIndex] < edge;
        if(index%2 == 0){
            isAPath = direction[posIndex] > edge;
        }

        if(isAPath){
            hasAPath = this.maze[direction[1]][direction[0]].equals(this.getPath());
        }
        return hasAPath;
    }
    public void nextCheck(){

        for(int i = 0; i < moves.length; i++){
            if(hasAPath(moves[i], edges[i], i)){
                directionPoints[posIndex[i]] = 0;
            }
        }

    }

    public int[] nextPosition(){
        boundsCheck();
        neighborCheck();
        nextCheck();
        
        double[] randomChance = new double[4];
        int[] nextPos = {0, 0};
        for(int i =0; i < directions.length; i++){
            randomChance[i] = directionPoints[i]*Math.random();
        }

        int randomIndex = Tools.indexOfGreatestElement(randomChance);
        String pos = directions[randomIndex];

        for(int j = 0; j < directions.length; j++){

        if(pos.equals(directions[j])){
            nextPos = moves[j];
            break;
        }

       }
        if(directionPoints[0]+directionPoints[1]+directionPoints[2]+directionPoints[3] == 0){
            this.hasNextMove = false;
        }else{
            this.hasNextMove = true;
        }
        return nextPos;
    }

    public boolean getHasNextMove(){
        return hasNextMove;
    }
   
    public int[] getCurrentPos(){
        int[] currentPos = {x, y};
        return currentPos;
    }

    public int[] getEdges() {
        return edges;
    }
}


class Neighbor{
   
    private int index;
    private int up[] = {0, 0};
    private int down[] = {0, 0};
    private int right[] = {0, 0};
    private int left[] = {0, 0};
    private boolean[] comparator = new boolean[4];
    private boolean[] nextcomparator = new boolean[4];  


    public Neighbor(int[] position, Pathfinder p, int index){
        this.index = index;
        up[1] = position[1]-1;
        up[0] = position[0];
        down[0] = position[0];
        down[1] = position[1]+1;
        right[0] = position[0]+1;
        right[1] = position[1];
        left[1] = position[1];
        left[0] = position[0]-1;
        int[]edges = p.getEdges();
        int[] toCompare = {edges[0], edges[1], edges[2], edges[3]};
        boolean comparator[] = {position[1] > toCompare[0], position[1] < toCompare[1],
        position[0] > toCompare[2], position[0] < toCompare[3]};
        this.comparator = comparator;
        boolean nextcomparator[] = {up[1] > toCompare[0], down[1] < toCompare[1], left[0] > toCompare[2], right[0] < toCompare[3]};
        this.nextcomparator = nextcomparator;
       
    }

    public boolean[] nextHasPosition(){

        return this.nextcomparator;    
    }

    public boolean[] hasNextPosition(){
        return this.comparator;
    }

    public int getIndex() {
        return index;
    }
    public int[][] getPos(){
        int[][] pos = {this.up, this.down, this.left, this.right};
        return pos;
    }
    
}
