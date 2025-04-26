package oliot.lutemons.models;



import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// We don't need for this to be an abstract parent.
// Made the class into a Serializable so we can implement saving.
public class Lutemon implements Serializable {

    // Removed color because it makes more sense for it to be declared in the subclass
    protected String name, type;

    // imageID will be used to locate the appropriate image for each Lutemon
    protected int attack;
    protected int defense;
    protected int maxHealth;
    protected int currentHealth;
    protected int experience;
    protected int level;
    protected int id;
    protected int imageId;
    protected  int unfavorableWeather;


    protected int battles, wins, trainings;



    // These attributes to be used for GUI and saved data:
    private static final long serialVersionUID = 1L;

    // The idCount is static. The idea is to use it for the RecyclerView
    // It will need to be manually saved and it has a setter which allows ...
    // us to retrieve the number of Lutemons made so far.
    // RecyclerView could be easily implemented as a loop ...
    //  we can remove idCount later if it proves unnecessary.
    private static int idCount = 0;

    public Lutemon(String name,String type, int maxHealth, int attack, int defense) {
        this.name = name;
        this.type = type;
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.experience = 0;
        this.level = 1; // Every Lutemon starts at level 1
        // The class should start with the maxHealth as its current health
        this.currentHealth = maxHealth;
        this.battles = 0;
        this.trainings = 0;
        this.wins = 0;
        this.id = ++idCount;

    }

    // Generated getters.
    public String getName() {
        return name;
    }

    public int getImageId(){
        return imageId;
    }
    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getExperience() {
        return experience;
    }

    public int getBattles() {
        return battles;
    }

    public int getWins() {
        return wins;
    }

    public String getType() {return type;}



    public int getLevel(){return level;}

    public int getId(){return id;}

    // Created some setters

    public String weatherCondition(HashMap<Integer, String> condition){
        String effect = "";
        for(Map.Entry<Integer, String> entry: condition.entrySet()){
            if(entry.getKey() == unfavorableWeather){
                this.attack -= entry.getKey();
                effect = entry.getValue() +" " + this.name +" does not like this." + "Losing " + entry.getKey() + "attack points";
            } else {
                effect = entry.getValue() +" " + this.name +" is unbothered.";
            }
        } return effect;
    }
    public void setCurrentHealth(int healthChange){
        // healthChange can be negative or positive.
        currentHealth += healthChange;
        // Catch edge cases.
        if (currentHealth < 0){
            currentHealth = 0;
        } else if (currentHealth >= maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public void resetAttack(int number){
        this.attack = number;
    }
    // The idea here is that experience points improve the impact of the attack.
    public void setAttack(){
        // If experience = 150, attack = 1 + 150 / 100 = 1 + 1 = 2
        attack = attack + experience / 100;
    }

    public void addExperience(int xp){
        this.experience += xp;
    }
    public void setWins(){
        wins++;
    }
    public static void setIdCount(int idCount) {
        Lutemon.idCount = idCount;
    }
    public void setImageId(int drawable){
        this.imageId = drawable;
    }
    public void setBattles(){
        battles++;
    }




    public void battleExpAward(int increase){
        experience += increase;  // The idea here is to have a special reward for winning battles
        setAttack();
    }

}
