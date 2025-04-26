package oliot.lutemons.models;

import oliot.lutemons.R;

public class OrangeLutemon extends Lutemon {
    public OrangeLutemon(String name) {
        super(name, "Orange", 17, 8, 1); // MaxHealth, Attack, Defense
        this.imageId = R.drawable.orange_lutemon;
        this.unfavorableWeather = 3;
    }
}
