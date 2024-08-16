package farid.weather.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "weather")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Id
    private String weatherId;

    private double latitude;

    private double longitude;

    private List<String> tempAndDayAndRain;

    private int temperature;

    private String tempUnit;

    private String windSpeedUnit;

    private String precipitationUnit;

    private String timezone;

    private String userId;

}
