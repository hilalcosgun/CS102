import java.util.Scanner;

public class Player {
    private char symbol;
    private int[] location;
    private int dice;
    private int moveNum;
    private int trapNum;
    private boolean didWin;

    public Player(){
        location = new int[2];
        didWin = false;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        //int[] temp = {x,y};
        //board.isInCell(temp){
            location[0] = x;
            location[1] = y;
        //}
    }

    public int getDice() {
        return dice;
    }

    public int getMoveNum() {
        return moveNum;
    }

    public int getTrapNum() {
        return trapNum;
    }

    public boolean isDidWin() {
        return didWin;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public int setTrapNum(int num){
        trapNum = num;
        return trapNum;
    }
    
    public boolean isInCell(Board board){
        int x = location[0];
        int y = location[1];
        if(x % 3 != 0 && y % 3 != 0 && board.getBoardDetailed()[x][y-1] == '#' || board.getBoardDetailed()[x][y+1] == '#' ){
            return true;
        }
        return false;
    }

    public int[] inWhichCell(){
        //char[][] boardDetailed = board.getBoardDetailed();
        int[] cell = new int[2];
        cell[0] = location[0] / 3;
        cell[1] = location[1] / 3;
        return cell;
    }

    public void movePlayer(Board board ){
        dice = Game.playDice();
        System.out.printf("Player %s rolls %d, how many cells you move? (0-%d): ", symbol, dice, dice);
        Scanner scan = new Scanner(System.in);
        int choice;
        while(!scan.hasNextInt()){
            System.out.printf("Your choice should be an integer between 0-%d : ", dice);
            scan.next();
        }
        int temp = scan.nextInt();
        while( temp < 0 || temp > dice){
            System.out.printf("Your choice should be an integer between 0-%d : ", dice);
            temp = scan.nextInt();
        }
        choice = temp; 
        int[] cell = this.inWhichCell();
        while(!didWin && choice > 0 ){
            if(cell[0] == 0 && cell[1] < board.getWidth()-1){
                location[1] += 3;
                choice--;
                moveNum++;
                cell = this.inWhichCell();
            }
            else if(cell[0] < board.getHeight() -1 && cell[1] == board.getWidth()-1){
                location[0] += 3;
                choice--;
                moveNum++;
                cell = this.inWhichCell();
            }
            else if(cell[0] == board.getHeight()-1 && cell[1] > 0){
                location[1] -= 3;
                choice--;
                moveNum++;
                cell = this.inWhichCell();
            }
            else if(cell[0] > 0  && cell[1] == 0 ){
                location[0] -= 3;
                choice--;
                moveNum++;
                cell = this.inWhichCell();
            }
            if(cell[0] == 1 && cell[1] == 0){
                didWin = true;
            }
        }
        scan.close();
    } 
}
