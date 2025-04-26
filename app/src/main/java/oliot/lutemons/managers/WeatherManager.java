package oliot.lutemons.managers;

import java.util.HashMap;
import java.util.Random;

public class WeatherManager {

    public HashMap<Integer, String> getRandomWeatherCondition() {
        Random random = new Random();

        // Generate a random number between 1 and 4 (inclusive)
        int weatherType = random.nextInt(4) + 1;  // Random number between 1 and 4

        // Define possible weather conditions
        HashMap<Integer, String> weatherConditions = new HashMap<>();
        weatherConditions.put(1, "The battle begins, it's drizzling💧");
        weatherConditions.put(2, "The battle begins, it's pouring rain 🌧️");
        weatherConditions.put(3, "The battle begins, the sun is shining ☀️");
        weatherConditions.put(4, "The battle begins, it's a very cloudy day🌥️");

        // Get the weather condition based on the random value
        String weatherCondition = weatherConditions.get(weatherType);

        // If the weather condition is null, log a message and return a default condition
        if (weatherCondition == null) {
            System.err.println("Error: Weather condition for type " + weatherType + " is missing.");
            weatherCondition = "The battle begins with no weather condition specified.";
        }

        // Return a HashMap with the selected weather condition
        HashMap<Integer, String> selectedWeather = new HashMap<>();
        selectedWeather.put(weatherType, weatherCondition);

        return selectedWeather;
    }
}


