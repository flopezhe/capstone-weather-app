package farid.weather.data;

import farid.weather.models.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.Assert.*;


@DataMongoTest
public class WeatherRepositoryTest {

    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void testFindByUser() {

        Weather weather1 = new Weather("1", 40.7128, -74.0060, "user1");
        Weather weather2 = new Weather("2", 34.0522, -118.2437, "user1");


        weatherRepository.save(weather1);
        weatherRepository.save(weather2);



        List<Weather> user1Weather = weatherRepository.findByUserId("user1");



        assertNotNull(user1Weather);

    }

    @Test
    public void testSave (){
        Weather weather1 = new Weather("1", 40.7128, -74.0060, "user10");

        weatherRepository.save(weather1);

        Weather actual = weatherRepository.findById("1").orElse(null);

        assertEquals(weather1, actual);
    }

    @Test
    public void testDelete () {
        Weather weather1 = new Weather("1", 40.7128, -74.0060, "user11");

        weatherRepository.save(weather1);

        weatherRepository.deleteById("1");

        Weather actual = weatherRepository.findById("1").orElse(null);

        assertNull(actual);

    }
}