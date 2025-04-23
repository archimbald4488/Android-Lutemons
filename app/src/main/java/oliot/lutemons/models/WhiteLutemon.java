package oliot.lutemons.models;

import oliot.lutemons.R;

public class WhiteLutemon extends Lutemon {
    public WhiteLutemon(String name) {
        super(name, "White", 20, 5, 4); // MaxHealth, Attack, Defense
        this.imageId = R.drawable.ic_launcher_foreground; // temporary
    }
}
