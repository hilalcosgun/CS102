
import java.util.Arrays;
import java.util.Random;

public class Board {
    private int height;
    private int width;
    private char[][] boardDetailed;
    private int[][] traps;
    
    public Board(int height, int width){
        this.height = height;
        this.width = width;
        boardDetailed = CreateBoard(height, width);
        this.addTraps();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public char[][] getBoardDetailed(){
        return boardDetailed;
    }

    public int[][] getTraps(){
        return traps;
    }

    public boolean isInCorner(int[] cell){
        if((cell[0] == 0 || cell[0] == height -1) && (cell[1] == 0 || cell[1] == width -1)){
            return true;
        }
        return false;
    }

    private char[][] CreateBoard(int height, int width){
        boardDetailed = new char[height*3+1][width*3+1];
        for(int i = 0; i < boardDetailed.length; i++){
            for(int k = 0; k < boardDetailed[i].length; k++){
                if(i > 3 && i < boardDetailed.length - 4 && k > 3 && k < boardDetailed[i].length - 4 ){
                    boardDetailed[i][k] = ' ';
                }
                else if(i % 3 == 0){
                    boardDetailed[i][k] = '#';
                }
                else if(k % 3 == 0){
                    boardDetailed[i][k] = '#';
                }
                else{
                    boardDetailed[i][k] = ' ';
                }
            }
            
        }
        return boardDetailed;
    }

    public void displayBoard(){
        for(int i = 0; i < boardDetailed.length; i++){
            for(int k = 0; k < boardDetailed[i].length; k++){
                System.out.print(boardDetailed[i][k]);
            }
            System.out.println();
        }
    }

    public void replacePlayer(Player player){
        boardDetailed[player.getLocation()[0]][player.getLocation()[1]] = ' ';
        player.movePlayer(this);
        boardDetailed[player.getLocation()[0]][player.getLocation()[1]] = player.getSymbol();
    }
    
     private void addTraps(){
        int numOfTraps = ((height + width - 1) *2 - 4)/ 3;
        traps = new int[numOfTraps][2];
        Random rand = new Random();
        int currentTrap = 0;
        while(currentTrap < numOfTraps){
            int[] loc = new int[2]; 
            loc[0] = rand.nextInt(height);
            if(rand.nextInt(2) == 0){
                loc[1] = 0;
            }
            else{
                loc[1] = width-1;
            }
            if(!isInCorner(loc) && !(loc[0] == 1 && loc[1] == 0)){
                boolean contains = false;
                for(int k = 0; !contains && k < currentTrap; k++){
                    if(Arrays.equals(traps[k], loc)){
                        contains = true;
                    }
                }
                if(!contains){
                    traps[currentTrap] = loc;
                    currentTrap++;
                }
            } 
            
        }
    }     
    public void removeTrap(int index){
        traps[index] = null;
    }
}



    
