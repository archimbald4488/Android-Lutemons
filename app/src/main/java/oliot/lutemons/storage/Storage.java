package oliot.lutemons.storage;

import oliot.lutemons.models.Lutemon;
import java.util.HashMap;
public class Storage {
    private static Storage instance;

    private final HashMap<Integer, Lutemon> lutemonStorage = new HashMap<>();
    private final HashMap<Integer, Lutemon> home = new HashMap<>();
    private final HashMap<Integer, Lutemon> training = new HashMap<>();
    private final HashMap<Integer, Lutemon> battlefield = new HashMap<>();

    private Storage() {}

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public void addLutemon(Lutemon lutemon) { lutemonStorage.put(lutemon.getId(), lutemon);}

    public void moveToTraining(int id) {
        Lutemon lutemon = lutemonStorage.get(id);
        if (lutemon != null) {
            training.put(id, lutemon);
            home.remove(id);
            battlefield.remove(id);
        }
    }
    public void moveToBattlefield(int id) {
        Lutemon lutemon = lutemonStorage.get(id);
        if (lutemon != null) {
            battlefield.put(id, lutemon);
            home.remove(id);
            training.remove(id);
        }
    }
    public void moveToHome(int id) {
        Lutemon lutemon = lutemonStorage.get(id);
        if (lutemon != null) {
            home.put(id, lutemon);
            training.remove(id);
            battlefield.remove(id);
        }
    }
    public Lutemon getLutemon(int id) { return lutemonStorage.get(id);}
    public void removeLutemon(int id) {lutemonStorage.remove(id);}

    // getters for convenience
    public HashMap<Integer, Lutemon> getHomeLutemons() { return home; }
    public HashMap<Integer, Lutemon> getTrainingLutemons() { return training; }
    public HashMap<Integer, Lutemon> getBattlefieldLutemons() { return battlefield; }

    // This can be implemented when/if we decide to add the ability to save Lutemons

    /* public void saveCurrentLutemons(Context context){
        try{
            ObjectOutputStream lutemonWriter = new ObjectInputStream(context.openFileOut("save.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemonHashMap);
            lutemonWriter.close();
        } catch (IOException e){
            System.out.println("Tallentaminen ei onnistunut.");
        }
    }

    public void loadSavedLutemons(Context context){
        try{
            ObjectInputStream loadLutemons = new ObjectInputStream(context.openFileInput("save.data", Context.MODE_PRIVATE));
            lutemonHashMap = (HashMap<Integer, Lutemon>) loadLutemons.readObject();
        } catch (FileNotFoundException e){
            System.out.println("Tiedoston lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("Tiedoston lukeminen ei onnistunut");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.out.println("Tiedoston lukeminen ei onnistunut");
            e.printStackTrace();
        }
    } */


}
