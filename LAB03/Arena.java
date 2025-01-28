import java.util.ArrayList;
import java.util.Random;
import java.lang.Class;

public class Arena {
    protected ArrayList<Unit> playerUnits;
    protected ArrayList<Unit> computerUnits;
    protected boolean gameEnds;
    
    public Arena(){
        playerUnits = new ArrayList<Unit>(7);
        computerUnits = new ArrayList<Unit>(7);
        playerUnits = fillUnitList(playerUnits);
        computerUnits = fillUnitList(computerUnits);
        gameEnds = false;
    }
    
    private boolean didGameEnd(){
        boolean doesPlayerWin = true;
        boolean doesCompWin = true;
        for(int i = 0; i < playerUnits.size(); i++){
            if(!computerUnits.get(i).isDead){
                doesPlayerWin = false;
            }
        }
        for(int i = 0; i < computerUnits.size(); i++){
            if(!playerUnits.get(i).isDead){
                doesCompWin = false;
            }
        }
        if(doesPlayerWin && !doesCompWin){
            System.out.println("The player wins the game.");
            return true;
        }
        else if(!doesPlayerWin && doesCompWin){
            System.out.println("The computer wins the game.");
            return true;
        }
        else if(doesPlayerWin && doesCompWin){
            System.out.println("The game ended in a draw.");
            return true;
        }
        return false;
    }
    private boolean didBattleEnd(Unit playerTemp, Unit computerTemp){
        if(playerTemp.isDead || computerTemp.isDead ){
            if(playerTemp.isDead && computerTemp.isDead){
                System.out.println("Both units in the arena are dead now");
                return true;
            }
            else if(!playerTemp.isDead && computerTemp.isDead){
                System.out.println(computerTemp.name + " is dead now");
                System.out.println(playerTemp.name + " levels up");
                playerTemp.increaseLevel();
                return true;
            }
            else if(playerTemp.isDead && !computerTemp.isDead){
                System.out.println(playerTemp.name + " is dead now");
                System.out.println(computerTemp.name + " levels up");
                computerTemp.increaseLevel();
                return true;
            }
        }
        return false;
    }
    private ArrayList<Unit> fillUnitList(ArrayList<Unit> unitList ){
        boolean containsWarrior = false;
        int count = 0;
        Random rand = new Random();
        while(count < 7){
            int choice = rand.nextInt(7);
            if(choice == 0){
                Warrior warrior = new Warrior();
                unitList.add(warrior);
            }
            if(choice == 1){
                Archer archer = new Archer();
                unitList.add(archer);
            }
            if(choice == 2){
                Healer healer = new Healer();
                unitList.add(healer);
            }
            if(choice == 3){
                Rogue rogue = new Rogue();
                unitList.add(rogue);
            }
            if(choice == 4){
                Wizard wizard = new Wizard();
                unitList.add(wizard);
                containsWarrior = true;
            }
            if(choice == 5){
                Bard bard = new Bard();
                unitList.add(bard);
            }
            if(choice == 6){
                Necromancer necromancer = new Necromancer();
                unitList.add(necromancer);
            }
            count++;
        }
        if(!containsWarrior){
            int index = rand.nextInt(7);
            Wizard wizard = new Wizard();
            unitList.set(index,wizard);
        }
        return unitList;
    }
   
    public void battle(int playerIndex, int computerIndex){
        Unit playerTemp = playerUnits.get(playerIndex);
        Unit compTemp = computerUnits.get(computerIndex);
        playerUnits.remove(playerIndex);
        computerUnits.remove(computerIndex);
        // first phase
        System.out.println("\nPhase 1: ");
        System.out.print("Player: ");
        playerTemp.firstPhase(compTemp, playerUnits, computerUnits);
        System.out.print("Computer: ");
        compTemp.firstPhase(playerTemp, computerUnits, playerUnits);
        if(didBattleEnd(playerTemp, compTemp)){
            System.out.println("Battle ended after phase 1");
        }
        else{
            //second phase
            System.out.println("\nPhase 2: ");
            System.out.print("Player: ");
            playerTemp.secondPhase(compTemp, playerUnits, computerUnits);
            System.out.print("Computer: ");
            compTemp.secondPhase(playerTemp, computerUnits, playerUnits);
            if(didBattleEnd(playerTemp, compTemp)){
                System.out.println("Battle ended after phase 2");
            }
            else{
                //third phase
                System.out.println("\nPhase 3: ");
                System.out.print("Player: ");
                playerTemp.thirdPhase(compTemp, playerUnits, computerUnits);;
                System.out.print("Computer: ");
                compTemp.thirdPhase(playerTemp, computerUnits, playerUnits);
                didBattleEnd(playerTemp, compTemp);
                System.out.println("Battle ended after phase 3");
            }
        }
        playerUnits.add(playerIndex, playerTemp);
        computerUnits.add(computerIndex, compTemp);
        gameEnds = didGameEnd();
    } 
    public String toString(){
        String output ="";
        output += "Computer's Units:";
        for(int i = 0; i < computerUnits.size(); i++){
            output += "\n" + (i+1) + ". " + computerUnits.get(i).getInfo();
        }
        output += "\nPlayer's Units:\n";
        for(int i = 0; i < playerUnits.size(); i++){
            output += "\n" + (i+1) + ". " + playerUnits.get(i).getInfo();
        }
        return output;
    }
        
}

