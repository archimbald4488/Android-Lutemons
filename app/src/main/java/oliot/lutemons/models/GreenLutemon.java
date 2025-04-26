package oliot.lutemons.models;

import oliot.lutemons.R;

public class GreenLutemon extends Lutemon {
    public GreenLutemon(String name) {
        super(name, "Green", 19, 6, 3); // MaxHealth, Attack, Defense
        this.imageId = R.drawable.green_lutemon; // temporary
        this.unfavorableWeather = 2;
    }
}
