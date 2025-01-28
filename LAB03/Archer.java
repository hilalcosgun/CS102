import java.util.ArrayList;

public class Archer extends Unit {

    public Archer(){
        super("Archer");
    }

    public int getAttack(){
        return level + 1;
    }
    public int getMaxHealth(){
        return level + 1;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.printf("%s damaged arena opponent by %d%n" , name, getAttack());
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit random = chooseRandomly(enemyWaiting,false);
        if(random != null){
            random.damage(getAttack());
            System.out.printf("%s damaged waiting %s by %d%n" , name, random.name, getAttack());
            if(random.isDead){
                System.out.printf("Waiting %s is dead now.%n", random.name);
                System.out.printf("%s is level up%n", name);
                increaseLevel();
            }
        }
        else{
            System.out.printf("%s does nothing%n", name);
        }
        
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        heal(1);
        System.out.printf("%s healed self by 1%n", name);
    }
}

