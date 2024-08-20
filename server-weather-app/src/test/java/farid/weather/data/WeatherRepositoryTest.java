package farid.weather.data;

import farid.weather.models.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Testcontainers
public class WeatherRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void saveWeather() {
        Weather weather = new Weather();
        weather.setWeatherId("1");
        weather.setLatitude(50);
        weather.setLongitude(50);
        mongoTemplate.save(weather);

        Weather found = mongoTemplate.findById(weather.getWeatherId(), Weather.class);
        assertNotNull(found);
        assertEquals(weather.getWeatherId(), found.getWeatherId());
    }
}