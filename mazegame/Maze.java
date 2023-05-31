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
        this.createPlayer(generator.getStartPos()[0], generator.getStartPos()[1]);
        this.endPos[0] = generator.getEndPos()[0];
        this.endPos[1] = generator.getEndPos()[1];
        String maze[][] = this.getMazeMap();
        
        this.setMazeMap(maze);
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
        generateEndPos();
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

        int[][] edge = {{1, 1}, {maze[0].length-2, 1}, {1, maze.length-2}, {maze[0].length-2, maze.length-2}};
        int randomIndex = Tools.generateRandomIndex(0, edge.length);
      
        startPos[0] = edge[randomIndex][0];
        startPos[1] = edge[randomIndex][1];

        this.setMazeMap(maze);
    }

    public void randomWalk(){
      ArrayList<int[]> paths = new ArrayList<>();
      paths.add(startPos);
      String[][] maze = this.getMazeMap();
   
      while(paths.size() > 0){
        Pathfinder pathfinder = new Pathfinder(maze, paths.get(paths.size()-1)[0], paths.get(paths.size()-1)[1]);
        int next[] = pathfinder.nextPosition();
        if(pathfinder.getHasNextMove()){
            paths.add(next);
            maze[next[1]][next[0]] = this.getPath();
        }else{
          paths.remove(paths.size()-1);
        }
     this.setMazeMap(maze);
    }
}

public void generateEndPos(){
    String[][] maze = this.getMazeMap();
    int x = 1;
    int y = 0;
    int max = maze.length/2;
    int min = 1;
    int randomIndex = 0;
    ArrayList<Integer> availableList = new ArrayList<>();

    if(startPos[0] == 1){
        x = maze[0].length-2;
    }
    if(startPos[1] == 1){
        min = maze.length/2;
        max = maze.length-2;
    }

    System.out.println(max);
    System.out.println(min);
    for(int i = min; i < max; i++){
        if(maze[i][x].equals(this.getPath())){
            
            availableList.add(i);
        }
    }
    randomIndex = Tools.generateRandomIndex(0, availableList.size());
    y = availableList.get(randomIndex);
    
    if(x == 1){
        x-=1;
    }else{
        x+=1;
    }
    endPos[0] = x;
    endPos[1] = y;
    maze[endPos[1]][endPos[0]] = this.getPath();
    this.setMazeMap(maze);
}
public int[] getStartPos() {
    return startPos;
}

public int[] getEndPos() {
    return endPos;
}

}
