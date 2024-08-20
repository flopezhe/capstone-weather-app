package farid.weather.data;

import farid.weather.models.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {
    List<Weather> findByUserId(String userId);
}
