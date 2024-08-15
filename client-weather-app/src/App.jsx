import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Weather from "./components/Weather";
import { WeatherProvider } from "./components/WeatherContext";
import BackgroundChanger from "./components/Background";
import NavBar from "./components/NavBar";
import ClothingOutfit from "./components/ClothingOutfit";
import ClothingItemForm from "./components/ClothingItemForm";

function App() {
  return (
    <Router>
      <WeatherProvider>
        <NavBar />
        <BackgroundChanger />
        <Routes>
          <Route path="/weather" element={<Weather />} />
          <Route path="/clothing-fit" element={<ClothingOutfit />} />
          <Route path="/add-clothing-item" element={<ClothingItemForm />} />
        </Routes>
      </WeatherProvider>
    </Router>
  );
}

export default App;
