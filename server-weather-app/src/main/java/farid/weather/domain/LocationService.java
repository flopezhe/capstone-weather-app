package farid.weather.domain;

import farid.weather.data.LocationRepository;
import farid.weather.models.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> getAllLocations(){
        return repository.findAll();
    }

    public Optional<Location> getLocationById(Integer locationId){
        return repository.findById(locationId);
    }

    public Location addLocation(Location location){
        return repository.save(location);
    }

    public void deleteLocation(Integer locationId){
        repository.deleteById(locationId);
    }
}
