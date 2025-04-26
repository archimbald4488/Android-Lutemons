package oliot.lutemons.models;

import oliot.lutemons.R;

public class BlackLutemon extends Lutemon {
    public BlackLutemon(String name) {
        super(name, "Black", 16, 9, 0); // MaxHealth, Attack, Defense
        this.imageId = R.drawable.black_lutemon;
        this.unfavorableWeather = 1;
    }
}

