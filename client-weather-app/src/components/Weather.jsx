import React, { useEffect, useState } from "react";
import { fetchWeatherApi } from "openmeteo";

export default function Weather() {
  const [weatherData, setWeatherData] = useState(null);

  useEffect(() => {
    const params = {
      latitude: 52.52,
      longitude: 13.41,
      current: ["temperature_2m", "is_day", "rain"],
      temperature_unit: "fahrenheit",
      wind_speed_unit: "mph",
      precipitation_unit: "inch",
      timezone: "America/Los_Angeles",
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

  if (!weatherData) {
    return <div>Loading weather data...</div>;
  }

  return (
    <div>
      <p>Time: {weatherData.current.time.toISOString()}</p>
      <p>Temperature: {Math.ceil(weatherData.current.temperature2m)}°F</p>
      <p>Daytime: {weatherData.current.isDay ? "Yes" : "No"}</p>
      <p>Rain: {weatherData.current.rain ? "Yes" : "No"}</p>
    </div>
  );
}
