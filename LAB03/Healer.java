import java.util.ArrayList;

public class Healer extends Unit {

    public Healer(){
        super("Healer");
    }

    public int getAttack(){
        return level;
    }
    public int getMaxHealth(){
        return level + 2;
    }
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        if(health == getMaxHealth()){
            Unit random = chooseRandomly(allyWaiting,false);
            if(!random.equals(null)){
                random.heal(level);
                System.out.printf("%s healed waiting %s by %d%n", name, random.name, level);
            }
        }
        else{
            this.heal(level);
            System.out.printf("%s healed self by %d%n", name, level);
        }
    }
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        arenaOpponent.damage(getAttack());
        System.out.printf("%s damaged arena opponent by %d%n" , name, getAttack());
    }
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        Unit random = chooseRandomly(allyWaiting,true);
        if(random != null){
            random.revive();
            System.out.printf("%s revives waiting %s%n", name, random.name);
        }
        else{
            System.out.printf("%s does nothing%n", name);
        }
    }
}
