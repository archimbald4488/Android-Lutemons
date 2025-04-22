package oliot.lutemons.storage;

import oliot.lutemons.models.Lutemon;
import java.util.HashMap;
public class Storage {
    private static Storage instance;

    private HashMap<Integer, Lutemon> lutemonStorage = new HashMap<>();
    private HashMap<Integer, Lutemon> home = new HashMap<>();
    private HashMap<Integer, Lutemon> training = new HashMap<>();
    private HashMap<Integer, Lutemon> battlefield = new HashMap<>();

    private Storage() {}

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void addLutemon(Lutemon lutemon) {}
    public void moveToTraining(int id) {}
    public void moveToBattlefield(int id) {}
    public void moveToHome(int id) {}
    public Lutemon getLutemon(int id) { return null; }
    public void removeLutemon(int id) {}
}
