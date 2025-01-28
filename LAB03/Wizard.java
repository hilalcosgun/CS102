import java.util.ArrayList;

public class Wizard extends Unit {

    public Wizard(){
        super("Wizard");
    }
    @Override
    public int getAttack(){
        return 1;
    }
    @Override
    public int getMaxHealth(){
        return level + 2;
    }
    @Override
    public void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        System.out.printf("%s does nothing%n", name);
    }
    @Override
    public void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        System.out.printf("%s does nothing%n", name);
    }
    @Override
    public void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting){
        for(int i = 0; i < enemyWaiting.size(); i++){
            Unit enemy = enemyWaiting.get(i);
            if(!enemy.isDead){
                enemy.damage(getAttack());
                System.out.printf("%s damaged waiting %s by %d%n" , name, enemy.name, getAttack());
                if(enemy.isDead){
                    System.out.printf("Waiting %s is dead now.%n", enemy.name);
                    System.out.printf("%s is level up%n", name);
                    this.increaseLevel();
                }
            }
        }
    }
}

