import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Weather from "./components/Weather";
import { WeatherProvider } from "./components/WeatherContext";
import BackgroundChanger from "./components/Background";
import NavBar from "./components/NavBar";
import ClothingOutfit from "./components/ClothingOutfit";
import ClothingItemForm from "./components/ClothingItemForm";
import Header from "./components/Header";
import Auth from "./components/Auth";
import { UserProvider } from "./components/UserContext";

function App() {
  return (
    <UserProvider>
      <Router>
        <WeatherProvider>
          <Header />
          <NavBar />
          <BackgroundChanger />
          <Routes>
            <Route path="/weather" element={<Weather />} />
            <Route path="/clothing-fit" element={<ClothingOutfit />} />
            <Route path="/add-clothing-item" element={<ClothingItemForm />} />
            <Route path="/user" element={<Auth />} />
          </Routes>
        </WeatherProvider>
      </Router>
    </UserProvider>
  );
}

export default App;
