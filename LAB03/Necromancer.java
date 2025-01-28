import java.util.ArrayList;

public class Necromancer extends Unit {

    public Necromancer(){
        super("Necromancer");
    }
    @Override
    public int getAttack(){
        return level;
    }
    @Override
    public int getMaxHealth(){
        return level + 1;
    }
    @Override
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.printf("%s damaged arena opponent by %d%n" , name, getAttack());
    }
    @Override
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit random = chooseRandomly(allyWaiting,true);
        if(random != null){
            random.revive();
            System.out.printf("%s revives waiting %s%n", name, random.name);
        }
        else{
            health--;
            System.out.printf("%s decreases its health by 1%n", name);
        }
    }
    @Override
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.decreaseLevel();
        System.out.printf("%s decreases arena opponent's level by 1%n", name, arenaOpponent.name);
    }
}
