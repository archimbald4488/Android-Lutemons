package oliot.lutemons.managers;

import java.util.Scanner;

import oliot.lutemons.models.Lutemon;

public class BattleManager {
    // Currently the battle manager works in the console.
    // The logic is simple, but works.
    private static final double RANDOM_DAMAGE_FACTOR = 3.0;
    Scanner gameScanner = new Scanner(System.in);

    // If the player wins the battle, he can earn experience points.
    int expAward = 0;

    String eventOutcome, winner;

    public void startBattle(Lutemon playerLutemon, Lutemon enemyLutemon){
        updateStats(playerLutemon, enemyLutemon);

        while(!isBattleOver(playerLutemon, enemyLutemon)){
            String userInput;
            System.out.println("(a) for attack, (r) for running away");
            userInput = gameScanner.nextLine();

            switch (userInput){
                case "a":
                    expAward += playerAttack(playerLutemon, enemyLutemon);
                    updateStats(playerLutemon,enemyLutemon);
                    break;
                case "r":
                    // playerEscape() <-- to be implemented
                    break;
            } enemyAttack(enemyLutemon, playerLutemon);
                if (isBattleOver(playerLutemon, enemyLutemon)) {
                break;
            }
        }

        checkBattleOutcome(playerLutemon, enemyLutemon, expAward);


    }


    public void updateStats(Lutemon a, Lutemon b) {
        System.out.printf("%s(%s) - Attack: %d, Defense: %d, Health: %d/%d, Exp: %d%n",
                a.getName(), a.getType(), a.getAttack(), a.getDefense(), a.getCurrentHealth(), a.getMaxHealth(), a.getExperience());
        System.out.printf("%s(%s) - Attack: %d, Defense: %d, Health: %d/%d, Exp: %d%n",
                b.getName(), b.getType(), b.getAttack(), b.getDefense(), b.getCurrentHealth(), b.getMaxHealth(), b.getExperience());
    }



    // Main attack logic
    public int attack(Lutemon attacker, Lutemon defender){
        double randomFactor = Math.random() * RANDOM_DAMAGE_FACTOR;
        int damage = (int) (attacker.getAttack() + randomFactor - defender.getDefense());
        String outcome;
        defender.setCurrentHealth(-damage); // Defender takes damage

       String event = (attacker.getType() + "(" + attacker.getName() + ") attacks " +
                defender.getType() + "(" + defender.getName() + ") for " + damage + " damage!");

        if (defender.getCurrentHealth() > 0) {
            outcome =  "(" + defender.getName() + ") manages to escape death.";
        } else {
            outcome =  "(" + defender.getName() + ") gets killed.";
        }

        setAttackOutcome(event + outcome +"\n");

        return damage; // This will be used for the case where the player attacks.
    }

    public void checkBattleOutcome(Lutemon player, Lutemon enemy, int expAward){
        if (isBattleOver(player, enemy)) {
            if (player.getCurrentHealth() <= 0) {
                winner = "The battle is over. "+enemy.getName() + " won the battle!";

                player.setBattles();
            } else {
                winner = "The battle is over."+player.getName() + " won the battle!";
                player.battleExpAward(expAward);
                player.setWins();
                player.setBattles();
                player.setCurrentHealth(player.getMaxHealth());
            }
            enemy.setCurrentHealth(enemy.getMaxHealth()); // The enemies are hard coded in the EnemyStorage. Need to improve their health at end of game
        }
    }

    public int playerAttack(Lutemon attacker, Lutemon defender){
        return attack(attacker, defender);
    }


    public void enemyAttack(Lutemon attacker, Lutemon defender){
        attack(attacker, defender);
    }

    public String getWinner(){return winner;}
    public void setAttackOutcome(String event) {
        eventOutcome = event;

    }

    public String getAttackOutcome(){
        return eventOutcome;
    }

    public boolean isBattleOver(Lutemon a, Lutemon b) {
        return a.getCurrentHealth() == 0 || b.getCurrentHealth() == 0;

    }
}
