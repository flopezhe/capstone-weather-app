package farid.weather.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "weather")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Id
    private String weatherId;

    private double latitude;

    private double longitude;

    private String userId;



}
