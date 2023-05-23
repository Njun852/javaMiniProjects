import java.util.ArrayList;



public class Game {
    private final String player = "()";
    private final String path = "  ";
    private final String wall = "██";
    private boolean gameOver = false;

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
}

class Tools{
    public static int[] getDistance(int x1, int x2, int y1, int y2){
        int diffX = Math.abs(x1-x2);
        int diffY = Math.abs(y1-y2);
        int diff[] = {diffX, diffY};
        return diff;
    }
    public static int generateRandomIndex(int min, int max){

        return (int)Math.max(min, Math.floor(Math.random()*max));
    }

    public static int indexOfGreatestElement(double[] arr){
        double bestElement = arr[0];
        int bestIndex = -1;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] >= bestElement){
                bestElement = arr[i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }

 
}


//Clean this code
class Pathfinder extends Game{

    private String[][] maze;
    private int x;
    private int y;

    private boolean hasLeft;
    private boolean hasRight;
    private boolean hasTop;
    private boolean hasBottom;
    
    private int[] moveLeft = {0, 0};
    private int[] moveUp = {0, 0};
    private int[] moveDown = {0, 0};
    private int[] moveRight = {0, 0};

    //use <
    private int bottomOfMaze;
    private int rightOfMaze;
    //use >
    private int topOfMaze;
    private int leftOfMaze;

    private String[] directions = {"top", "bottom", "left", "right"};
    private int[] directionPoints = {1, 1, 1, 1};
    private final int leftIndex = 2;
    private final int rightIndex = 3;
    private final int topIndex = 0;
    private final int bottomIndex = 1;

    private boolean hasNextMove;
    private ArrayList<int[]> traversed;

    //Extra
    private int[] endPos = new int[2];

    public Pathfinder(String[][] maze, int x, int y, ArrayList<int[]> traversed){
        this.maze = maze;
        this.x = x;
        this.y = y;

        bottomOfMaze = maze.length-1;
        topOfMaze = 0;
        rightOfMaze = maze[0].length-1;
        leftOfMaze = 0;

        hasLeft = x > leftOfMaze+1;
        hasRight = x < rightOfMaze-1;
        hasBottom = y <bottomOfMaze-1;
        hasTop = y > topOfMaze+1;

        moveLeft[0] = x-1;
        moveLeft[1] = y;

        moveRight[1] = y;
        moveRight[0] = x+1;
        
        moveDown[1] = y+1;
        moveDown[0] = x;
        moveUp[1] = y-1;
        moveUp[0] = x;
        this.traversed = traversed;
       
    }
    
    public void boundsCheck(){
        if(!hasLeft){
            directionPoints[leftIndex] = 0;
        }
        if(!hasRight){
            directionPoints[rightIndex] = 0;
        }
        if(!hasTop){
            directionPoints[topIndex] = 0;
        }
        if(!hasBottom){
            directionPoints[bottomIndex] = 0;
        }
    
    }

    public void neighborCheck(){
        //up down left right
        ArrayList<Neighbor> n = new ArrayList<>();

        if(moveRight[0] < rightOfMaze){
            Neighbor newNeighbor = new Neighbor(moveRight, this,rightIndex);
            n.add(newNeighbor);
        }
        if(moveDown[1] < bottomOfMaze){
            Neighbor newNeighbor = new Neighbor(moveDown, this, bottomIndex);
            n.add(newNeighbor);
        }
        if(moveUp[1] > topOfMaze){
            Neighbor newNeighbor = new Neighbor(moveUp, this, topIndex);
            n.add(newNeighbor);
        }
        if(moveLeft[0] > leftOfMaze){
            Neighbor newNeighbor = new Neighbor(moveLeft, this, leftIndex);
            n.add(newNeighbor);
        }


        for(int i = 0; i < n.size(); i++){
            Neighbor current = n.get(i);
            int[] currentPos = {this.x, this.y};
            if(current.nexthasUp()){
              
                int up[] = current.getUp();
                
                boolean hasPathUp = this.maze[up[1]][up[0]].equals(this.getPath()) && up[0] != currentPos[0] && up[1] != currentPos[1];
                if(hasPathUp){
                    directionPoints[current.getIndex()] = 0;
                }
            }
            if(current.nexthasDown()){
                int down[] = current.getDown();
                boolean hasPathDown = this.maze[down[1]][down[0]].equals(this.getPath()) && down[0] != currentPos[0] && down[1] != currentPos[1];
        
                if(hasPathDown){
                    directionPoints[current.getIndex()] = 0;
                }
            }
            if(current.nexthasLeft()){
                int left[] = current.getLeft();
                
                boolean hasPathLeft = this.maze[left[1]][left[0]].equals(this.getPath()) && left[0] != currentPos[0] && left[1] != currentPos[1];
               
                if(hasPathLeft){
                    directionPoints[current.getIndex()] = 0;
                }
            }
            if(current.nexthasRight()){
                int right[] = current.getRight();
                boolean hasPathRight = this.maze[right[1]][right[0]].equals(this.getPath()) && right[0] != currentPos[0] && right[1] != currentPos[1];
                if(hasPathRight){
                    directionPoints[current.getIndex()] = 0;
                }
            }
        }
    }


    public void traverseChecking(){

        for(int i = 0; i < traversed.size(); i++){

            if(moveDown[0] == traversed.get(i)[0] && moveDown[1] == traversed.get(i)[1]){
                directionPoints[bottomIndex] = 0;
            }
            if(moveUp[0] == traversed.get(i)[0] && moveUp[1] == traversed.get(i)[1]){
                directionPoints[topIndex] = 0;
            }
            if(moveRight[0] == traversed.get(i)[0] && moveRight[1] == traversed.get(i)[1]){
                directionPoints[rightIndex] = 0;
            }
            if(moveLeft[0] == traversed.get(i)[0] && moveLeft[1] == traversed.get(i)[1]){
                directionPoints[leftIndex] = 0;
            }
        }
    }

    public void nextCheck(){

        if(moveDown[1] < bottomOfMaze && moveDown[0] < rightOfMaze && moveDown[0] > leftOfMaze){
            if(this.maze[moveDown[1]][moveDown[0]].equals(this.getPath())){
                directionPoints[bottomIndex] = 0;
            }
        }
        if(moveLeft[0] > leftOfMaze && moveLeft[1] < bottomOfMaze && moveLeft[1] > topOfMaze){
            if(this.maze[moveLeft[1]][moveLeft[0]].equals(this.getPath())){
                directionPoints[leftIndex] = 0;
            }
        }
        if(moveRight[0] < rightOfMaze && moveRight[1] < bottomOfMaze && moveRight[1] > topOfMaze){
            if(this.maze[moveRight[1]][moveRight[0]].equals(this.getPath())){
                directionPoints[rightIndex] = 0;
            }
        }
        if(moveUp[1] > topOfMaze && moveUp[0] < rightOfMaze && moveUp[0] > leftOfMaze){
            if(this.maze[moveUp[1]][moveUp[0]].equals(this.getPath())){
                directionPoints[topIndex] = 0;
            }
            
        }

    }

    public int[] nextPosition(){
        boundsCheck();
        nextCheck();
        traverseChecking();
        nextCheck();
        neighborCheck();

        // if(moveUp[0] == this.endPos[0] && moveUp[1] == this.endPos[1]){
        //     directionPoints[topIndex] = 1;
        // }
        // if(moveDown[0] == this.endPos[0] && moveDown[1] == this.endPos[1]){
        //     directionPoints[bottomIndex] = 1;
        // }
        // if(moveRight[0] == this.endPos[0] && moveRight[1] == this.endPos[1]){
        //     directionPoints[rightIndex] = 1;
        // }
        // if(moveLeft[0] == this.endPos[0] && moveLeft[1] == this.endPos[1]){
        //     directionPoints[leftIndex] = 1;
        // }
        double[] randomChance = new double[4];
        int[] nextPos = {0, 0};
        for(int i =0; i < directions.length; i++){
            randomChance[i] = directionPoints[i]*Math.random();
        }

        int randomIndex = Tools.indexOfGreatestElement(randomChance);
        String pos = directions[randomIndex];
        switch(pos){
            case "top":
            nextPos = moveUp;
            break;
            case "bottom":
            nextPos = moveDown;
            break;
            case "left":
            nextPos = moveLeft;
            break;
            case "right":
            nextPos = moveRight;
            break;
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
    public int getTopOfMaze() {
        return topOfMaze;
    }
    public int getBottomOfMaze() {
        return bottomOfMaze;
    }
    public int getRightOfMaze() {
        return rightOfMaze;
    }
    public int getLeftOfMaze() {
        return leftOfMaze;
    }
    public int[] getCurrentPos(){
        int[] currentPos = {x, y};
        return currentPos;
    }
    public int[] getEndPos() {
        return endPos;
    }
    public void setEndPos(int[] endPos) {
        this.endPos = endPos;
    }
}


class Neighbor{
    private Pathfinder pathfinder;
    private int index;
    private int up[] = {0, 0};
    private int down[] = {0, 0};
    private int right[] = {0, 0};
    private int left[] = {0, 0};
    private int[] neighbor;

    public Neighbor(int[] position, Pathfinder p, int index){
        this.index = index;
        this.pathfinder = p;
        up[1] = position[1]-1;
        up[0] = position[0];
        down[0] = position[0];
        down[1] = position[1]+1;
        right[0] = position[0]+1;
        right[1] = position[1];
        left[1] = position[1];
        left[0] = position[0]-1;
        neighbor = position;
    }

    public boolean nexthasUp(){
        return up[1] > pathfinder.getTopOfMaze();
    }

    public boolean nexthasDown(){
        return down[1] < pathfinder.getBottomOfMaze();
    }
    public boolean nexthasRight(){
        return right[0] < pathfinder.getRightOfMaze();
    }
    public boolean nexthasLeft(){
        return left[0] > pathfinder.getLeftOfMaze();
    }


    public boolean hasUp(){
        return neighbor[1] > pathfinder.getTopOfMaze();
    }

    public boolean hasDown(){
        return neighbor[1] < pathfinder.getBottomOfMaze();
    }
    public boolean hasRight(){
        return neighbor[0] < pathfinder.getRightOfMaze();
    }
    public boolean hasLeft(){
        return neighbor[0] > pathfinder.getLeftOfMaze();
    }

    public int getIndex() {
        return index;
    }
    public int[] getUp() {
        return up;
    }
    public int[] getDown() {
        return down;
    }
    public int[] getRight() {
        return right;
    }
    public int[] getLeft() {
        return left;
    }
    
}
