import java.util.ArrayList;


public class Maze extends Game{
    private String[][] mazeMap;
    private int width;
    private int height;

    private int[] endPos = {0, 0};
    public Maze(int width, int height){
        this.width = width;
        this.height = height;
    }
    public Maze(){

    }
    public String[][] createMaze(){
        String maze[][] = new String[this.height][this.width];

        int bottom = maze.length-1;
        int top = 0;
        int left = 0;
        int right = maze[0].length-1;
        for(int y = 0; y < maze.length; y++){
            for(int x = 0; x < maze[y].length; x++){
                if(x == left || y == bottom || x == right || y == top){
                    maze[y][x] = this.getWall();
                }else{
                    maze[y][x] = this.getPath();
                }
            }
        }
        return maze;

    }
    public void createRandomMaze(){
        MazeGenerator generator = new MazeGenerator(this.width, this.height);
        this.setMazeMap(generator.generateMaze());
        this.createEndPos(generator.getEndPos());
        this.createPlayer(generator.getStartPos()[0], generator.getStartPos()[1]);
        
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public void renderMaze(){
        for(int i = 0; i < this.getHeight()*2; i++){
            System.out.print("\n");
        }
        System.out.println();
        mazeMap = getMazeMap();
        for(int y = 0; y < mazeMap.length; y++){
            for(int x = 0; x < mazeMap[y].length; x++){
                System.out.print(mazeMap[y][x]);
            }
            System.out.println("");
        }
    }

    public void createPlayer(int x, int y){
        String[][] maze = this.getMazeMap();
        maze[y][x] = this.getPlayer();
        int[] playerPos = {x, y};
        this.setPlayerPos(playerPos);
        this.setMazeMap(maze);

        if(playerPos[0] == this.endPos[0] && playerPos[1] == this.endPos[1]){
            this.iSOver(true);
        }
    }

    public void movePlayer(String direction){
        //current position
        int playerPos[] = getPlayerPos();
        int moveBy[] = {0, 0};

        switch(direction){
            case "w":
            moveBy[1] -=1;
            break;
            case "s":
            moveBy[1] +=1;
            break;
            case "a":
            moveBy[0] -= 1;
            break;
            case "d":
            moveBy[0] += 1;
            break;
        }

        int playerNewPos[] = {playerPos[0]+moveBy[0], playerPos[1]+moveBy[1]};
        String [][] maze = this.getMazeMap();
        if(playerNewPos[0] > 0 && playerNewPos[0] < maze[0].length && playerNewPos[1] > 0 && playerNewPos[1] < maze.length
        ||(playerNewPos[0] == endPos[0] && playerNewPos[1] == endPos[1])){
            if(maze[playerNewPos[1]][playerNewPos[0]].equals(this.getWall())){
                playerNewPos[0] -= moveBy[0];
                playerNewPos[1] -= moveBy[1];
            }else{
                maze[playerPos[1]][playerPos[0]] = this.getPath();
            }
        }else{
            playerNewPos[0] = playerPos[0];
            playerNewPos[1] = playerPos[1];
        }
        

       createPlayer(playerNewPos[0], playerNewPos[1]);
    }
    public void updateMazeMap(int x, int y, String newContent){
        String[][] maze = this.getMazeMap();
        maze[y][x] = newContent;
        this.setMazeMap(maze);
    }

    public void setRandomPlayerPosition(){
        String[][] maze = this.getMazeMap();

        //top bottom left right
        ArrayList<int[]> postion = new ArrayList<>();

        for(int y = 1; y < maze.length-1; y++){
            for(int x = 1; x < maze[y].length-1; x++){

                boolean positionIsEmpty = maze[y][x] == this.getPath();
             
                if(positionIsEmpty &&  (x == 1 || x == maze[0].length-2 || y == 1 || y == maze.length-2)){
                    int[] availablePosition = {x, y};
                    postion.add(availablePosition);
                }
            }
        }

        int randomIndex = Tools.generateRandomIndex(0, postion.size());
        int x = postion.get(randomIndex)[0];
        int y = postion.get(randomIndex)[1];
        int playerPos[] = {x, y};

        this.setPlayerPos(playerPos);
        maze[y][x] = this.getPlayer();
        this.setMazeMap(maze);
    }

    public void createEndPos(int[] endPos){

        int pushX = 0;
        int pushY = 0;
        
       
        int x = endPos[0];
        int y = endPos[1];
        String[][] maze = this.getMazeMap();
        this.endPos[0] = x;
        this.endPos[1] = y;
        maze[endPos[1]][endPos[0]] = this.getPath();

        if(y == 1){
            pushY-=1;
        }else if(y == maze.length-2){
            pushY+=1;
        }
        if(x == 1){
            pushX-=1;
        }else if(x == maze[0].length-2){
            pushX +=1;
        }

        double flip = Math.random();
        if(flip > 0.50){
            pushY = 0;
        }else{
            pushX = 0;
        }
        this.endPos[0] = x+pushX;
        this.endPos[1] = y+pushY;
        maze[this.endPos[1]][this.endPos[0]] = this.getPath();
        this.setMazeMap(maze);
      
    }
    public String[][] getMazeMap() {
        if(this.mazeMap == null){
            this.mazeMap = createMaze();
        }
        return this.mazeMap;
    }

    public void setMazeMap(String[][] mazeMap) {
        this.mazeMap = mazeMap;
    }

}

class MazeGenerator extends Maze{

    private int startPos[] = new int[2];
    private int endPos[] = new int[2];
    public MazeGenerator(int width, int height){
        super(width, height);
    }
    
    public String[][] generateMaze(){
        initializeMaze();
        randomWalk();
        return this.getMazeMap();
    }
    public void initializeMaze(){
        String[][] maze = this.getMazeMap();
        for(int y = 0; y < maze.length; y++){
            for(int x = 0; x < maze[y].length; x++){
                if(maze[y][x].equals(this.getPath())){
                    maze[y][x] = this.getWall();
                }
            }
        }

        // int indexX = Tools.generateRandomIndex(1, maze[0].length-1);
        // int indexY = Tools.generateRandomIndex(1, maze.length-1);
        
        int[][] edge = {{1, 1}, {maze[0].length-2, 1}, {1, maze.length-2}, {maze[0].length-2, maze.length-2}};
        int randomIndex = Tools.generateRandomIndex(0, edge.length);
      
        startPos[0] = edge[randomIndex][0];
        startPos[1] = edge[randomIndex][1];

        endPos[0] = edge[3-randomIndex][0];
        endPos[1] = edge[3-randomIndex][1];
        //maze[edge[randomIndex][1]][edge[randomIndex ][0]] = this.getPath();
    
        this.setMazeMap(maze);
    }

    public void randomWalk(){
      ArrayList<int[]> paths = new ArrayList<>();
      ArrayList<int[]> traversed = new ArrayList<>();
      paths.add(startPos);
      String[][] maze = this.getMazeMap();
  
      while( paths.size() > 0){
        Pathfinder pathfinder = new Pathfinder(maze, paths.get(paths.size()-1)[0], paths.get(paths.size()-1)[1], traversed);
        pathfinder.setEndPos(this.endPos);
        int next[] = pathfinder.nextPosition();
        if(pathfinder.getHasNextMove()){
            paths.add(next);
            maze[pathfinder.getCurrentPos()[1]][pathfinder.getCurrentPos()[0]] = this.getPath();
            
        }else{
          traversed.add(pathfinder.getCurrentPos());
          paths.remove(paths.size()-1);
        }
     this.setMazeMap(maze);
    }
}
public int[] getStartPos() {
    return startPos;
}

public int[] getEndPos() {
    return endPos;
}
}
