package farid.weather.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "location")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {


    @Id
    private int locationId;

    private double latitude;
    private double longitude;
    private String city;
    private String state;


}
