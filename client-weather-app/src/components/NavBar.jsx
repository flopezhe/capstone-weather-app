import React from "react";
import { NavLink } from "react-router-dom";

export default function NavBar() {
  return (
    <nav>
      <div>
        <ul>
          <li>
            <NavLink className="nav-to-style" to="/">
              Home
            </NavLink>
          </li>
          <li>
            <NavLink className="nav-to-style" to="/weather">
              Weather
            </NavLink>
          </li>
          <li>
            <NavLink className="nav-to-style" to="/clothing-fit">
              Clothing Outfit
            </NavLink>
          </li>
          <li>
            <NavLink className="nav-to-style" to="/add-clothing-item">
              Add Clothing Item
            </NavLink>{" "}
          </li>
          <li>
            <NavLink className="nav-to-style" to="/user">
              Account{" "}
            </NavLink>
          </li>
        </ul>
      </div>
    </nav>
  );
}
