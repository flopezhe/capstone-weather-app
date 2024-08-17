import React, { useState, useContext } from "react";
import { UserContext } from "./UserContext";
import { WeatherContext } from "./WeatherContext";

export default function Weather() {
  const [longitudeData, setLongitudeData] = useState("");
  const [latitudeData, setLatitudeData] = useState("");
  const { user } = useContext(UserContext);
  const { weatherData, setWeatherData } = useContext(WeatherContext);

  const fetchData = async () => {
    if (
      !latitudeData ||
      !longitudeData ||
      isNaN(latitudeData) ||
      isNaN(longitudeData)
    ) {
      console.error("Invalid latitude or longitude values.");
      return;
    }

    const params = {
      latitude: latitudeData,
      longitude: longitudeData,
      current_weather: true,
      temperature_unit: "fahrenheit",
      wind_speed_unit: "mph",
      precipitation_unit: "inch",
      timezone: "America/Los_Angeles",
    };

    const url = `https://api.open-meteo.com/v1/forecast?latitude=${params.latitude}&longitude=${params.longitude}&current_weather=true&temperature_unit=${params.temperature_unit}&wind_speed_unit=${params.wind_speed_unit}&precipitation_unit=${params.precipitation_unit}&timezone=${params.timezone}`;

    try {
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error(`API request failed with status ${response.status}`);
      }
      const data = await response.json();

      if (!data.current_weather) {
        throw new Error("Current weather data not found in the response.");
      }

      const currentWeather = data.current_weather;

      const weatherData = {
        current: {
          time: currentWeather.time,
          temperature2m: currentWeather.temperature,
          isDay: currentWeather.is_day,
          rain: currentWeather.precipitation > 0,
        },
      };

      setWeatherData(weatherData);

      if (user) {
        await fetch(`/weather/associate/${user.id}`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            latitude: latitudeData,
            longitude: longitudeData,
            tempAndDayAndRain: [
              `Time: ${currentWeather.time}`,
              `Temperature: ${currentWeather.temperature}`,
              `Is Day: ${currentWeather.is_day}`,
              `Rain: ${currentWeather.precipitation > 0 ? "Yes" : "No"}`,
            ],
            temperature: currentWeather.temperature,
            tempUnit: "fahrenheit",
            windSpeedUnit: "mph",
            precipitationUnit: "inch",
            timezone: "America/Los_Angeles",
          }),
        });
      }
    } catch (error) {
      console.error("Error fetching weather data:", error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    fetchData();
  };

  const formatDate = (date) => {
    const options = {
      year: "numeric",
      month: "long",
      day: "numeric",
      hour: "numeric",
      minute: "numeric",
      hour12: true,
      timeZone: "America/Los_Angeles",
    };
    return date.toLocaleString(undefined, options);
  };

  return (
    <>
      <div>
        <form onSubmit={handleSubmit}>
          <label>Longitude: </label>
          <input
            type="text"
            value={longitudeData}
            onChange={(e) => setLongitudeData(e.target.value)}
          />
          <br />
          <label>Latitude: </label>
          <input
            type="text"
            value={latitudeData}
            onChange={(e) => setLatitudeData(e.target.value)}
          />
          <br />
          <button className="add-clothing-item-button" type="submit">
            Enter Location
          </button>
        </form>
      </div>
      {weatherData && (
        <div>
          <p>
            Date:
            {formatDate(new Date(weatherData.current.time))}
          </p>
          <p>Temperature: {Math.ceil(weatherData.current.temperature2m)}°F</p>
          <p>Daytime: {weatherData.current.isDay ? "Yes" : "No"}</p>
          <p>Rain: {weatherData.current.rain ? "Yes" : "No"}</p>
        </div>
      )}
    </>
  );
}
