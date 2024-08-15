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
            <NavLink to="/clothing-fit">Clothing Outfit</NavLink>
          </li>
          <li>
            <NavLink to="/add-clothing-item">Add Clothing Item</NavLink>{" "}
          </li>
        </ul>
      </div>
    </nav>
  );
}
