import React, { createContext, useContext, useState, useEffect } from "react";
import { fetchWeatherApi } from "openmeteo";
import { UserContext } from "./UserContext";

export const WeatherContext = createContext();

export function WeatherProvider({ children }) {
  const [weatherData, setWeatherData] = useState(null);

  const { user } = useContext(UserContext);

  useEffect(() => {
    if (user && user.latitude && user.longitude) {
      const params = {
        latitude: user.latitude,
        longitude: user.longitude,
        current: ["temperature_2m", "is_day", "rain"],
        temperature_unit: "fahrenheit",
        wind_speed_unit: "mph",
        precipitation_unit: "inch",
        timezone: "America/Los_Angeles",
        timeformat: "unixtime",
      };
      const url = "https://api.open-meteo.com/v1/forecast";

      async function fetchData() {
        try {
          const responses = await fetchWeatherApi(url, params);
          const response = responses[0];

          const current = response.current();

          const weatherData = {
            current: {
              time: new Date(Number(current.time()) * 1000),
              temperature2m: current.variables(0).value(),
              isDay: current.variables(1).value(),
              rain: current.variables(2).value(),
            },
          };

          setWeatherData(weatherData);
        } catch (error) {
          console.error("Error fetching weather data:", error);
        }
      }

      fetchData();
    }
  }, [user]);

  return (
    <WeatherContext.Provider value={{ weatherData, setWeatherData }}>
      {children}
    </WeatherContext.Provider>
  );
}

export const useWeather = () => useContext(WeatherContext);
