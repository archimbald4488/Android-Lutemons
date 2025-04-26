package oliot.lutemons.models;

import oliot.lutemons.R;

public class WhiteLutemon extends Lutemon {
    public WhiteLutemon(String name) {
        super(name, "White", 20, 5, 4); // MaxHealth, Attack, Defense
        this.imageId = R.drawable.white_lutemon;
        this.unfavorableWeather = 1;
    }
}
