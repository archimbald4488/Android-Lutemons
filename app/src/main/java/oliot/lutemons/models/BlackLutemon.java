package oliot.lutemons.models;

// BlackLutemon has been changed to a concrete subclass.
// This makes more sense since we only need subclasses of the Lutemon.
// The same change will be made to all other subclasses of Lutemon
public class BlackLutemon extends Lutemon {

    public BlackLutemon(String name) {
        // int maxHealth, int attack, and int defense should be hard coded.
        // Every Lutemon starts with basic stats.
        // We can look at concrete stats later.
        // Current implementation is just a placeholder
        super(name, "Black", 100, 10, 10);
        // this.imageId = <-- This should be updated in due time
    }



}
