import React, { useState } from "react";

function ClothingItemForm() {
  const [formData, setFormData] = useState({
    clothingType: "",
    clothingName: "",
    clothingImage: "",
    wearOnRainyDay: "",
    wearOnHotDay: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetch("http://localhost:8080/clothing_item", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    })
      .then((response) => {
        if (response.ok) {
          alert("Clothing item added successfully!");
        } else {
          alert("Failed to add clothing item.");
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("An error occurred while adding the clothing item.");
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Clothing Type:</label>
        <select
          name="clothingType"
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
          type="text"
          name="clothingImage"
          value={formData.clothingImage}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Wear on Rainy Day:</label>
        <select
          name="wearOnRainyDay"
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
        <label>Wear on Hot Day:</label>
        <select
          name="wearOnHotDay"
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
      <button type="submit">Add Clothing Item</button>
    </form>
  );
}

export default ClothingItemForm;
