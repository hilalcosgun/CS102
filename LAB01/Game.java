import java.util.Arrays;
import java.util.Random;

public class Game {
    
    private Player[] players;
    private int numOfPlayers;
    private Board board;

    public Game(int height, int width, int numOfPlayers ){
        this.numOfPlayers = numOfPlayers;
        this.board = new Board(height, width);
        setPlayers(); 
    }

    public Player[] getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    private void setPlayers() {
        players = new Player[numOfPlayers];
        for(int i = 0; i < numOfPlayers; i++){
            players[i] = new Player();
        }
    }

    public static int playDice(){
        Random rand = new Random();
        int dice = rand.nextInt(6) + 1;
        return dice;
    }

    public void sortPlayers(){
        System.out.println("Players are rolling dice.");
        for(int i = 0; i < numOfPlayers; i++ ){
            players[i].setDice(playDice());
            System.out.printf("%c : %d , ", players[i].getSymbol(), players[i].getDice());
        }
        Player temp = new Player();
        for(int i = 0; i < numOfPlayers - 1; i++ ){
           for(int k = 0; k < numOfPlayers - i - 1; k++){
                if(players[k+1].getDice() > players[k].getDice()){
                    temp = players[k];
                    players[k] = players[k+1];
                    players[k+1] = temp;
                }
                else if(players[k+1].getDice() == players[k].getDice()){
                    System.out.printf("Breaking tie for: %s %s", players[k].getSymbol(), players[k+1].getSymbol());
                    //make random
                }
            }
        }
    }

    public void initializeFirstLocs(){
        if(numOfPlayers >= 2){
            players[0].setLocation(1, 1);
            players[1].setLocation(1,2);
        }
        if(numOfPlayers >= 3){
            players[2].setLocation(2, 1);
        }
        if(numOfPlayers >= 4){
            players[3].setLocation(2, 2);
        }
    }

    public void placePlayers(){
        for(int i = 0; i < players.length; i++){
            board.getBoardDetailed()[players[i].getLocation()[0]][players[i].getLocation()[1]] = players[i].getSymbol();
        }
    }

    public void replacePlayer(Player player){
        board.getBoardDetailed()[player.getLocation()[0]][player.getLocation()[1]] = ' ';
        player.movePlayer(this.board);
        board.getBoardDetailed()[player.getLocation()[0]][player.getLocation()[1]] = player.getSymbol();
    }

    public boolean didTrap(Player player){
        boolean didTrap = false;
        for(int i = 0; i < board.getTraps().length; i++){
            if(Arrays.equals(player.inWhichCell(), board.getTraps()[i])){
                player.setTrapNum(player.getTrapNum() + 1);
                didTrap = true;
                board.removeTrap(i);
            }
        }  
        if(didTrap){
            board.getBoardDetailed()[player.getLocation()[0]][player.getLocation()[1]] = ' ';
            System.out.println("You moved into a trap!");
            System.out.println("You moved back to the closest corner! ");
            int[] cell = player.inWhichCell();
            int[] loc = player.getLocation();
            if(cell[0] == 0){
                player.setLocation(loc[0], loc[1] - (cell[1] - 0)*3);// cell[1] = 0;
            }
            else if(cell[0] == board.getHeight()-1){
                player.setLocation(loc[0], loc[1] + (board.getWidth() - cell[1] -1 )*3); // cell[1] = board.getWidth();
            }
            else if(cell[1] == 0){
                player.setLocation(loc[0] + (board.getHeight() - cell[0] -1)*3, loc[1] );
            }
            else if(cell[1] == board.getWidth()-1){
                player.setLocation(loc[0] - (cell[0] -0)*3, loc[1]);
            }
            board.getBoardDetailed()[player.getLocation()[0]][player.getLocation()[1]] = player.getSymbol();
        } 
        return didTrap;
    }

    public boolean didEnd(){
        for(int i = 0; i < numOfPlayers; i++){
            if(players[i].isDidWin()){
                return true;
            }
        }
        return false;
    }
}    
   


    

