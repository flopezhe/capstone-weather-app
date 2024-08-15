import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Weather from "./components/Weather";
import { WeatherProvider } from "./components/WeatherContext";
import BackgroundChanger from "./components/Background";
import NavBar from "./components/NavBar";
import ClothingOutfit from "./components/ClothingOutfit";

function App() {
  return (
    <Router>
      <WeatherProvider>
        <NavBar />
        <BackgroundChanger />
        <Routes>
          <Route path="/weather" element={<Weather />} />
          <Route path="/clothing-fit" element={<ClothingOutfit />} />
        </Routes>
      </WeatherProvider>
    </Router>
  );
}

export default App;
