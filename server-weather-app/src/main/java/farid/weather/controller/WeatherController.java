package farid.weather.controller;

import farid.weather.domain.Result;
import farid.weather.domain.WeatherService;
import farid.weather.models.Weather;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/user/{userId}")
    public List<Weather> getWeather(@PathVariable String userId) {
        return weatherService.getWeather(userId);
    }


    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Weather weather) {
        
        Result<Weather> result = weatherService.saveWeather(weather);

        if(result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{weatherId}")
    public ResponseEntity<Object> update(@PathVariable String weatherId, @RequestBody Weather weather) {
        if(!weatherId.equalsIgnoreCase(weather.getWeatherId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Weather> result = weatherService.updateWeather(weather);

        if(result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{weatherId}")
    public ResponseEntity<Void> delete(@PathVariable String weatherId) {
        if(weatherService.deleteWeather(weatherId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
