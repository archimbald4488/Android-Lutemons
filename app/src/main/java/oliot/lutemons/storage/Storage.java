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

    public void addLutemon(Lutemon lutemon) { lutemonStorage.put(lutemon.getId(), lutemon);}

    // move to Training can be next.
    public void moveToTraining(int id) {}

    // I dont this is needed?
    public void moveToBattlefield(int id) {}
    public void moveToHome(int id) {}
    public Lutemon getLutemon(int id) { return lutemonStorage.get(id);}
    public void removeLutemon(int id) {lutemonStorage.remove(id);}

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
