package oliot.lutemons.storage;

import java.util.ArrayList;
import java.util.Random;

import oliot.lutemons.models.BlackLutemon;
import oliot.lutemons.models.GreenLutemon;
import oliot.lutemons.models.Lutemon;
import oliot.lutemons.models.OrangeLutemon;
import oliot.lutemons.models.PinkLutemon;
import oliot.lutemons.models.WhiteLutemon;

public class EnemyStorage {

    private static EnemyStorage instance;
    private final ArrayList<Lutemon> enemyLutemons;

    private EnemyStorage() {
        enemyLutemons = new ArrayList<>();

        enemyLutemons.add(new BlackLutemon("Goblin"));
        enemyLutemons.add(new GreenLutemon("Troll"));
        enemyLutemons.add(new OrangeLutemon("Skeleton"));
        enemyLutemons.add(new PinkLutemon("Brian Kottarainen"));
        enemyLutemons.add(new WhiteLutemon("Monster Energy Drink"));
    }

    public static EnemyStorage getInstance(){
        if(instance == null){
            instance = new EnemyStorage();
        } return  instance;
    }
    public Lutemon getRandomEnemy() {
        Random rand = new Random();
        return enemyLutemons.get(rand.nextInt(enemyLutemons.size()));
    }

}
