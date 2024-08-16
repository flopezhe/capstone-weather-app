import React, { createContext, useContext, useState, useEffect } from "react";
import { fetchWeatherApi } from "openmeteo";

const WeatherContext = createContext();

export const WeatherProvider = ({ children }) => {
  const [weatherData, setWeatherData] = useState(null);

  useEffect(() => {
    const params = {
      latitude: 32.71,
      longitude: 117.16,
      current: ["temperature_2m", "is_day", "rain"],
      temperature_unit: "fahrenheit",
      wind_speed_unit: "mph",
      precipitation_unit: "inch",
      timezone: "America/Los_Angeles",
      timeformat: "unixtime",
    };
    const url = "https://api.open-meteo.com/v1/forecast";

    const fetchData = async () => {
      try {
        const responses = await fetchWeatherApi(url, params);
        const response = responses[0];

        const utcOffsetSeconds = response.utcOffsetSeconds();
        const current = response.current();

        const weatherData = {
          current: {
            time: new Date((Number(current.time()) + utcOffsetSeconds) * 1000),
            temperature2m: current.variables(0).value(),
            isDay: current.variables(1).value(),
            rain: current.variables(2).value(),
          },
        };

        setWeatherData(weatherData);
      } catch (error) {
        console.error("Error fetching weather data:", error);
      }
    };

    fetchData();
  }, []);

  return (
    <WeatherContext.Provider value={weatherData}>
      {children}
    </WeatherContext.Provider>
  );
};

export const useWeather = () => useContext(WeatherContext);
