package oliot.lutemons.storage;

import android.content.Context;

import oliot.lutemons.R;
import oliot.lutemons.models.BlackLutemon;
import oliot.lutemons.models.GreenLutemon;
import oliot.lutemons.models.Lutemon;
import oliot.lutemons.models.OrangeLutemon;
import oliot.lutemons.models.PinkLutemon;
import oliot.lutemons.models.WhiteLutemon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


public class Storage {
    private static Storage instance;

    private  HashMap<Integer, Lutemon> lutemonStorage = new HashMap<>();
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

    public void removeLutemon(int id) {
        lutemonStorage.remove(id);
        home.remove(id);
        training.remove(id);
        battlefield.remove(id);



    }

    // getters for convenience
    public HashMap<Integer, Lutemon> getHomeLutemons() { return home; }
    public HashMap<Integer, Lutemon> getTrainingLutemons() { return training; }


    // This can be implemented when/if we decide to add the ability to save Lutemons

    public void saveCurrentLutemons(Context context){
        try {
            /* Important. READ BEFORE ATTEMPTING TO REFACTOR!
             * NOTE ABOUT FILE STORAGE:
             *
             * The "save.data" file is saved using Context.openFileOutput(), which writes to the app's private internal storage.
             * File location: /data/data/your.package.name/files/save.data <-- Looks something like this, I guess.
             *
             * - This storage is private to the app. Other apps cannot access this file.
             * - The file persists between app launches, but will be deleted if the app is uninstalled.
             *
             * If in the future there's a need to make the save file visible outside the app (for example in Downloads or external storage),
             * this code would need to be refactored.
             */
            ObjectOutputStream lutemonWriter = new ObjectOutputStream(context.openFileOutput("save.data", Context.MODE_PRIVATE));
            lutemonWriter.writeObject(lutemonStorage);
            lutemonWriter.close();
        } catch (IOException e) {
            System.out.println("Tallentaminen ei onnistunut.");
            e.printStackTrace();
        }
    }

    public void loadSavedLutemons(Context context){
        try {
            ObjectInputStream loadLutemons = new ObjectInputStream(context.openFileInput("save.data"));
            lutemonStorage = (HashMap<Integer, Lutemon>) loadLutemons.readObject();
            loadLutemons.close();

            // Current view needs to be cleared to load the objects in saved file
            home.clear();
            battlefield.clear();
            training.clear();

            for(Lutemon lutemon : lutemonStorage.values()){
                home.put(lutemon.getId(), lutemon);

                // The images arent saving properly.
                // Ill just handle this in way that works, but is undoubtedly ugly.
                // We only have four lutemons so it is okay.
                if (lutemon instanceof BlackLutemon) {
                    lutemon.setImageId(R.drawable.black_lutemon);
                } else if (lutemon instanceof OrangeLutemon) {
                    lutemon.setImageId(R.drawable.orange_lutemon);
                } else if (lutemon instanceof PinkLutemon){
                    lutemon.setImageId(R.drawable.pink_lutemon);
                } else if(lutemon instanceof WhiteLutemon){
                    lutemon.setImageId(R.drawable.white_lutemon);
                } else if (lutemon instanceof GreenLutemon) {
                    lutemon.setImageId(R.drawable.green_lutemon);
                }


            }
            int highestId = 0;
            for (Lutemon lutemon : lutemonStorage.values()) {
                if (lutemon.getId() > highestId) {
                    highestId = lutemon.getId();
                }
            }
            Lutemon.setIdCount(highestId);

        } catch (FileNotFoundException e) {
            System.out.println("Tiedoston lukeminen ei onnistunut (file not found)");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Tiedoston lukeminen ei onnistunut (io error)");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Tiedoston lukeminen ei onnistunut (class not found)");
            e.printStackTrace();
        }
    }



}
