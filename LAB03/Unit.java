import java.util.ArrayList;
import java.util.Random;

public abstract class Unit {

    protected String name;
    protected int health;
    protected int level;
    protected boolean isDead;

    public Unit(String name){
        this.name = name;
        level = 1;
        health = getMaxHealth();
        isDead = false;
    }
    public abstract int getAttack();
    public abstract int getMaxHealth();
    public abstract void firstPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting);
    public abstract void secondPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting);
    public abstract void thirdPhase(Unit arenaOpponent, ArrayList<Unit> allyWaiting, ArrayList<Unit> enemyWaiting);

    public void damage(int damageAmount){
        health = health - damageAmount;
        if(health <= 0){
            isDead = true;
        }
    }
    public void increaseLevel(){
        level++;
    }
    public void decreaseLevel(){
        if(level > 1){
            level--; 
        }
        if(health > getMaxHealth()){
            health = getMaxHealth();
        }

    }
    public void revive(){
        if(isDead){
            decreaseLevel();
            isDead = false;
            health = getMaxHealth();
        }

    }
    public void heal(int healAmount){
        if(health + healAmount > getMaxHealth()){
            health = getMaxHealth();
        }
        else{
            health = health + healAmount;
        }
        if(health > 0){
            isDead = false;
        }
    }
    public String getInfo(){
        if(health < 0){
            health = 0;
        }
        return name + "," + " LVL: " + level + "," + " ATK: " + getAttack() + "," +  " HEALTH: " + health + "/" + getMaxHealth();
    }
    public Unit chooseRandomly(ArrayList<Unit> list, boolean condition){
        ArrayList<Unit> sublist = new ArrayList<Unit>();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).isDead == condition){
                sublist.add(list.get(i));
            }
        }
        if(sublist.size() == 0){
            return null;
        }
        else{
            Random rand = new Random();
            int index = rand.nextInt(sublist.size());
            return sublist.get(index);
        }
    }
}