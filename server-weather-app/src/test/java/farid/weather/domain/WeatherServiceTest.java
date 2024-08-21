package farid.weather.domain;

import farid.weather.data.WeatherRepository;
import farid.weather.models.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherServiceTest {

    @MockBean
    WeatherRepository weatherRepository;

    @Autowired
    WeatherService weatherService;



    @Test
    void saveWeather() {
        Weather weather = new Weather("1", 1, 1, "user1");

        when(weatherRepository.save(any())).thenReturn(weather);

        Result <Weather> actual = weatherService.saveWeather(weather);

        assertTrue(actual.isSuccess());

    }

    @Test
    void shouldNotAddInvalidLatitude(){
        Weather weather = new Weather("1", 91, 1, "user1");

        Result <Weather> actual = weatherService.saveWeather(weather);

        assertFalse(actual.isSuccess());
    }

    @Test
    void shouldNotAddInvalidLongitude(){
        Weather weather = new Weather("1", 1, 181, "user1");

        Result <Weather> actual = weatherService.saveWeather(weather);

        assertFalse(actual.isSuccess());

    }

    @Test
    void deleteWeather() {
        Weather weather = new Weather("1", 1, 1, "user1");

        when(weatherRepository.existsById("1")).thenReturn(false);

        boolean actual = weatherService.deleteWeather("1");

        assertFalse(actual);
    }

    @Test
    void updateWeather() {
        Weather weather = new Weather("1", 1, 1, "user1");

        when(weatherRepository.save(any())).thenReturn(weather);

        Result <Weather> actual = weatherService.updateWeather(weather);

        assertTrue(actual.isSuccess());
    }

   
}