package farid.weather.data;

import farid.weather.models.ClothingOutfit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingOutfitRepository extends MongoRepository<ClothingOutfit, String> {
}
