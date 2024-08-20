import React, { useState, useContext, useEffect } from "react";
import { UserContext } from "./UserContext";
import { WeatherContext } from "./WeatherContext";

export default function Weather() {
  const [longitudeData, setLongitudeData] = useState("");
  const [latitudeData, setLatitudeData] = useState("");
  const { user } = useContext(UserContext);
  const { weatherData, setWeatherData } = useContext(WeatherContext);
  const [errorsMsg, setErrorsMsg] = useState("");
  const [dataErrorsMsg, setDataErrorsMsg] = useState("");
  const [successMsg, setSuccessMessage] = useState("");
  const [weathers, setWeathersData] = useState([]);

  useEffect(() => {
    async function fetchWeatherData() {
      if (!user) {
        setDataErrorsMsg("User not logged in");
        return;
      }

      try {
        const response = await fetch(
          `http://localhost:8080/weather/user/${user.id}`
        );
        if (!response.ok) {
          setDataErrorsMsg(`Couldnt get the data, error with request!`);
          return;
        }

        const data = await response.json();

        setWeathersData(data);
      } catch (error) {
        console.error("Error fetching weather data:", error);
        setDataErrorsMsg("Error fetching weather data.");
      }
    }
    fetchWeatherData();
  }, [user, weathers]);

  async function fetchData() {
    if (
      !latitudeData ||
      !longitudeData ||
      isNaN(latitudeData) ||
      isNaN(longitudeData)
    ) {
      console.error("Invalid latitude or longitude values.");
      setErrorsMsg("Please enter a valid number for latitude and longitude!");
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
        setErrorsMsg(`API request failed with status ${response.status}`);
      }
      const data = await response.json();

      if (!data.current_weather) {
        setErrorsMsg("Please put a valid latitude or longitude.");
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
    } catch (error) {
      console.error("Error fetching weather data:", error);
    }
  }

  function handleSubmit(event) {
    event.preventDefault();
    fetchData();
  }

  function handleSaveWeather(event) {
    event.preventDefault();
    if (!user) {
      setErrorsMsg("Need to be logged in to save weather!");
      return;
    }

    fetch(`http://localhost:8080/weather`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify({
        latitude: parseFloat(latitudeData),
        longitude: parseFloat(longitudeData),
        userId: user.id,
      }),
    }).then((response) => {
      if (response.ok) {
        setSuccessMessage("Added successfully!");
      } else {
        setErrorsMsg("Error Submitting Form");
      }
    });
  }

  function formatDate(date) {
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
  }

  function handleDelete(weatherId) {
    fetch(`http://localhost:8080/weather/${weatherId}`, {
      method: "DELETE",
    });
  }

  return (
    <>
      {successMsg ? (
        <div>{successMsg}</div>
      ) : (
        <div>{errorsMsg ? <div>{errorsMsg}</div> : <div></div>}</div>
      )}
      <div className="weder-title">Item Form</div>
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
          <button
            className="add-clothing-item-button weder-title"
            type="submit"
          >
            Enter Location
          </button>
        </form>
      </div>
      {weatherData && (
        <>
          <div className="weder-title">
            <p>
              Date:
              {formatDate(new Date(weatherData.current.time))}
            </p>
            <p>Temperature: {Math.ceil(weatherData.current.temperature2m)}°F</p>
            <p>Daytime: {weatherData.current.isDay ? "Yes" : "No"}</p>
            <p>Rain: {weatherData.current.rain ? "Yes" : "No"}</p>
          </div>
        </>
      )}
      <div className="weder-title">
        <button
          className="add-clothing-item-button"
          onClick={handleSaveWeather}
          type="button"
        >
          Save Weather
        </button>
      </div>
      <div>
        <h3>Weather Data</h3>
        {dataErrorsMsg && <div>{dataErrorsMsg}</div>}
        {weathers.length > 0 ? (
          weathers.map((weather) => (
            <div key={weather.weatherId}>
              <p>Latitude: {weather.latitude}</p>
              <p>Longitude: {weather.longitude}</p>
              <button
                className="add-clothing-item-button"
                onClick={() => handleDelete(weather.weatherId)}
                type="button"
              >
                Delete
              </button>
            </div>
          ))
        ) : (
          <p>No data</p>
        )}
      </div>
    </>
  );
}
