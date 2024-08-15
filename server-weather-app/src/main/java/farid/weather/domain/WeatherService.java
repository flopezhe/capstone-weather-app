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

    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    public void deleteWeather(String id) {
        weatherRepository.deleteById(id);
    }

    public void updateWeather(int id, Double latitudeId, Double longitudeId, List<String> tempAndDayAndRain, int temperature, String tempUnit, String windUnit, String precipitationUnit, String timezone) {
        Weather weather = new Weather();
        weather.setWeatherId(id);
        weather.setLatitude(latitudeId);
        weather.setLongitude(longitudeId);
        weather.setTempAndDayAndRain(tempAndDayAndRain);
        weather.setTemperature(temperature);
        weather.setTempUnit(tempUnit);
        weather.setWindSpeedUnit(windUnit);
        weather.setPrecipitationUnit(precipitationUnit);
        weather.setTimezone(timezone);
        weatherRepository.save(weather);
    }

    public void validateSave(Weather weather) {
        if (weather.getLatitude() < -90 || weather.getLatitude() > 90) {
            throw new IllegalArgumentException("Location ID is required!");
        }

        if (weather.getLongitude() < -180 || weather.getLongitude() > 180) {
            throw new IllegalArgumentException("Date is required!");
        }


    }
}
