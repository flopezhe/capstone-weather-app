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
        Result<Void> result = validateSave(location);

        if(result.isSuccess()){
            result.addMessage("Location added!", ResultType.SUCCESS);
        }

        return repository.save(location);
    }

    public void deleteLocation(Integer locationId){
        repository.deleteById(locationId);
    }

    public Result<Void> validateSave(Location location){
        Result<Void> result = new Result<>();

        if(location.getCity() == null){
            result.addMessage("City is required!", ResultType.INVALID);
        }

        if(location.getState() == null){
            result.addMessage("State is required!", ResultType.INVALID);
        }
        return result;
    }
}
