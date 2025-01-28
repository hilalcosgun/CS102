import java.util.Random;
import java.util.Scanner;

public class FantasyBattleGame {
    public static void main(String[] args) {
        Arena arena = new Arena();
        for(int i = 1; !arena.gameEnds && i <= 50; i++){
            int playerChoice;
            int compChoice;
            System.out.println("Turn: " + i);
            System.out.println(arena.toString());
            Scanner scan = new Scanner(System.in);
            System.out.print("Which unit you choose:");
            playerChoice = scan.nextInt() -1;
            while(playerChoice < 0 || playerChoice > 6){
                System.out.println("Your choice must be 1-7. Enter again: ");
                playerChoice = scan.nextInt() -1;
            }
            while(arena.playerUnits.get(playerChoice).isDead){
                System.out.printf("%s is cannot battle since it is dead. Choose another unit:", arena.playerUnits.get(playerChoice).name);
                playerChoice = scan.nextInt() -1;
            }
            System.out.printf("%nYou choose %s", arena.playerUnits.get(playerChoice).getInfo());
            Random rand = new Random();
            compChoice = rand.nextInt(7);
            while(arena.computerUnits.get(compChoice).isDead){
                compChoice = rand.nextInt(7);
            }
            System.out.printf("%nComputer choose %s%n", arena.computerUnits.get(compChoice).getInfo());
            arena.battle(playerChoice, compChoice);
        }
    }
}

