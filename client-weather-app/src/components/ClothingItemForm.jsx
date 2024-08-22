import React, { useState, useContext } from "react";
import { UserContext } from "./UserContext";

function ClothingItemForm() {
  const [formData, setFormData] = useState({
    clothingType: "",
    clothingName: "",
    clothingImage: "",
    wearOnRainyDay: "",
    wearOnHotDay: "",
  });
  const { user } = useContext(UserContext);

  const [errorsMsg, setErrorsMsg] = useState("");

  function handleChange(e) {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  }

  function handleSubmit(e) {
    e.preventDefault();
    if (!user) {
      setErrorsMsg("You must be logged in to add a clothing item.");
      return;
    }

    fetch("http://localhost:8080/clothing_item", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ ...formData, userId: user.id }),
    })
      .then((response) => {
        if (response.ok) {
          setErrorsMsg("Added successfully!");
          setFormData({
            clothingType: "",
            clothingName: "",
            clothingImage: "",
            wearOnRainyDay: "",
            wearOnHotDay: "",
          });
        } else {
          setErrorsMsg("Error Submitting Form");
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        setErrorsMsg("An error occurred while adding the clothing item.");
      });
  }

  return (
    <>
      {errorsMsg ? (
        <div className="weder-title">{errorsMsg}</div>
      ) : (
        <div className="weder-title"> Item Form</div>
      )}
      <form onSubmit={handleSubmit} className="weder-title">
        <div>
          <label>Clothing Type:</label>
          <select
            name="clothingType"
            className="custom-select"
            value={formData.clothingType}
            onChange={handleChange}
            required
          >
            <option value="" disabled>
              Select Clothing Type
            </option>
            <option value="bottoms">Bottoms</option>
            <option value="first_layer_top">First Layer Top</option>
            <option value="second_layer_top">Second Layer Top</option>
            <option value="shoes">Shoes</option>
          </select>
        </div>
        <div>
          <label>Clothing Name:</label>
          <input
            type="text"
            name="clothingName"
            value={formData.clothingName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Clothing Image URL:</label>
          <input
            type="url"
            name="clothingImage"
            value={formData.clothingImage}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Rain Clothing?:</label>
          <select
            name="wearOnRainyDay"
            className="custom-select"
            value={formData.wearOnRainyDay}
            onChange={handleChange}
            required
          >
            <option value="" disabled>
              Select Yes or No
            </option>
            <option value="yes">Yes</option>
            <option value="no">No</option>
          </select>
        </div>
        <div>
          <label>Hot Day Clothing?:</label>
          <select
            name="wearOnHotDay"
            className="custom-select"
            value={formData.wearOnHotDay}
            onChange={handleChange}
            required
          >
            <option value="" disabled>
              Select Yes or No
            </option>
            <option value="yes">Yes</option>
            <option value="no">No</option>
          </select>
        </div>
        <div>
          <button className="add-clothing-item-button" type="submit">
            Add Clothing Item
          </button>
        </div>
      </form>
    </>
  );
}

export default ClothingItemForm;
