import java.util.ArrayList;

public class Bard extends Unit{

    public Bard(){
        super("Bard");
    }
    public int getAttack(){
        return level;
    }
    public int getMaxHealth(){
        return level;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.printf("%s damaged arena opponent by %d%n" , name, getAttack());
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        this.heal(1);
        System.out.printf("%s healed self by 1%n", name);
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit random = chooseRandomly(allyWaiting, false);
        if(random != null){
            random.increaseLevel();
            System.out.printf("%s increases waiting %s's level by 1%n", name, random.name);
        }
        else{
            System.out.printf("%s does nothing%n", name);
        }
        
    }
}
