
import java.util.Scanner;

public class BoardGameApp {
    public static void main(String[] args) {
        char choice;
        do{
            System.out.println("Welcome to the board game.");
            Scanner scan = new Scanner(System.in);
            System.out.print("Please enter board height: ");
            int height = scan.nextInt();
            while(height < 0){
                System.out.print("Enter a positive value:");
                height = scan.nextInt();
            }
            System.out.println();
            System.out.print("Please enter board width: ");
            int width = scan.nextInt();
            while(width < 0){
                System.out.print("Enter a positive value:");
                width = scan.nextInt();
            }
            System.out.println();
            System.out.print("How many players? (2-4): ");
            int numOfPlayers = scan.nextInt();
            while(numOfPlayers < 2 || numOfPlayers > 4){
                System.out.print("It should be between 2-4: ");
                numOfPlayers = scan.nextInt();
            }
            System.out.println();
            Game game = new Game(height, width, numOfPlayers);
            System.out.println("Please enter a character (symbol) for each player. ");
            for(int i = 1; i <= numOfPlayers; i++){
                System.out.printf("For player %d: " , i);
                char symbol;
                symbol = scan.next().charAt(0);
                while((i != 1 && symbol == game.getPlayers()[i-2].getSymbol())){
                    System.out.println("This symbol is taken. Please choose another one: ");
                    symbol = scan.next().charAt(0);
                }
                game.getPlayers()[i-1].setSymbol(symbol);
            }
            game.sortPlayers();
            game.initializeFirstLocs();
            game.placePlayers();
            System.out.println();
            game.getBoard().displayBoard();
            /*for(int i = 0; i < game.getBoard().getTraps().length;i++ ){
                System.out.println(game.getBoard().getTraps()[i][0] + "," + game.getBoard().getTraps()[i][1] );
            }*/
            for(int i = 0; !game.didEnd() && i < numOfPlayers; i = (i + 1) % numOfPlayers){
                game.replacePlayer(game.getPlayers()[i]);
                game.didTrap(game.getPlayers()[i]);
                if(game.getPlayers()[i].isDidWin()){
                    System.out.printf("Winner is %s, congratulations! ", game.getPlayers()[i].getSymbol());
                    System.out.println();
                }
                game.getBoard().displayBoard(); 
            }    
            System.out.printf("%-10s%-10s%-10s%n", "Player", "Move", "Trap");
            for(int i = 0; i < numOfPlayers; i++){
                System.out.printf("%-10c%-10d%-10d", game.getPlayers()[i].getSymbol(), game.getPlayers()[i].getMoveNum(), game.getPlayers()[i].getTrapNum() );
                System.out.println();
            }
            System.out.println("Play Again?(Y/N)");
            choice = scan.next().charAt(0); 
            scan.close();
        }while(choice == 'Y');
    }
}
    

