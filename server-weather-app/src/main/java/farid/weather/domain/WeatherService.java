package farid.weather.domain;

import farid.weather.data.WeatherRepository;
import farid.weather.models.Weather;

import java.util.List;

public class WeatherService {
    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getWeather(String id) {
        return weatherRepository.findById(id).orElse(null);
    }

    public Result<Weather> saveWeather(Weather weather) {
        Result<Weather> result = validateSave(weather);

        if (result.isSuccess()) {
            weatherRepository.save(weather);
        } else {
            result.addMessage("Weather not saved", ResultType.INVALID);
        }

        return result;
    }

    public boolean deleteWeather(String id) {
        boolean result = weatherRepository.existsById(id);

        if (result) {
            weatherRepository.deleteById(id);
        }

        return result;
    }

    public Result<Weather> updateWeather(Weather weather) {
        Result<Weather> result = validateSave(weather);

        if (result.isSuccess()) {
            weatherRepository.save(weather);
        } else {
            result.addMessage("Weather not updated", ResultType.INVALID);
        }

        return result;
    }

    public Result<Weather> validateSave(Weather weather) {
        Result<Weather> result = new Result<>();

        if (weather.getLatitude() < -90 || weather.getLatitude() > 90) {
            result.addMessage("Latitude must be between -90 and 90", ResultType.INVALID);
        }

        if (weather.getLongitude() < -180 || weather.getLongitude() > 180) {
            result.addMessage("Longitude must be between -180 and 180", ResultType.INVALID);
        }


        return result;
    }
}
