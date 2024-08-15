package farid.weather.data;

import farid.weather.models.ClothingItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingItemRepository extends MongoRepository<ClothingItem, String> {
}
