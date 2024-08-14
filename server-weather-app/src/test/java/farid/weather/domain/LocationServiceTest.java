package farid.weather.domain;

import farid.weather.data.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
class LocationServiceTest {

    @Autowired
    private LocationRepository repository;



    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllLocations() {
    }

    @Test
    void getLocationById() {
    }

    @Test
    void addLocation() {
    }

    @Test
    void deleteLocation() {
    }
}