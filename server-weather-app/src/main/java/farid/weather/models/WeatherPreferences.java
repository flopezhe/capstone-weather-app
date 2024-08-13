package farid.weather.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "weather_preferences")
public class WeatherPreferences {

    @Id
    private int weatherPreferenceId;
    private int weatherId;
    private int locationId;
}
