package oliot.lutemons.models;

import oliot.lutemons.R;

public class PinkLutemon extends Lutemon {
    public PinkLutemon(String name) {
        super(name, "Pink", 18, 7, 2); // MaxHealth, Attack, Defense
        this.imageId = R.drawable.pink_lutemon;
        this.unfavorableWeather = 4;
    }
}
