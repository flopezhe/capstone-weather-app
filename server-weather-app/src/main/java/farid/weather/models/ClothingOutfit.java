package farid.weather.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "clothing_outfit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClothingOutfit {

    @Id
    private String id;

    private String outfitName;

    private List<String> clothingItemIds;
}
