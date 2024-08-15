package farid.weather.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clothing_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothingItem {

    @Id
    private String id;

    private String clothingType;

    private String clothingName;

    private String clothingImage;

    private String wearOnRainyDay;

    private String wearOnHotDay;
}
