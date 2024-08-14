import React from "react";
import { NavLink } from "react-router-dom";

export default function NavBar() {
  return (
    <nav>
      <div>
        <ul>
          <li>
            <NavLink to="/">Home</NavLink>
          </li>
          <li>
            <NavLink to="/weather">Weather</NavLink>
          </li>
          <li>
            <NavLink>Weather Preferences</NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}
