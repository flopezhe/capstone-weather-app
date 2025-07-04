import React from "react";
import { useWeather } from "./WeatherContext";
import giphyGif from "./images/rain.gif";
import sunnyGif from "./images/sunny.gif";
import nightGif from "./images/night.gif";

function BackgroundChanger() {
  const { weatherData } = useWeather();

  if (!weatherData) {
    return null;
  }

  const { rain, isDay } = weatherData.current;

  const backgroundStyle = {
    backgroundImage:
      rain > 0
        ? `url(${giphyGif})`
        : isDay
        ? `url(${sunnyGif})`
        : `url(${nightGif})`,
    backgroundSize: "cover",
    backgroundRepeat: "no-repeat",
    backgroundPosition: "center",
    position: "fixed",
    top: 0,
    left: 0,
    width: "100%",
    height: "100%",
    zIndex: -1,
    opacity: 0.5,
  };

  return <div style={backgroundStyle}></div>;
}

export default BackgroundChanger;
