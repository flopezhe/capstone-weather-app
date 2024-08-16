package farid.weather.data;

import farid.weather.models.ClothingItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothingItemRepository extends MongoRepository<ClothingItem, String> {
    List<ClothingItem> findByClothingType(String clothingType);

    List<ClothingItem> findByClothingTypeAndUserId(String clothingType, String userId);
}
