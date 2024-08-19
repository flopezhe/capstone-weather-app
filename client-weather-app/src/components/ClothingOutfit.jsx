import React, { useState, useEffect, useContext } from "react";
import { UserContext } from "./UserContext";

function Outfit() {
  const [bottoms, setBottoms] = useState(null);
  const [top, setTop] = useState(null);
  const [shoes, setShoes] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const { user } = useContext(UserContext);

  useEffect(() => {
    const fetchItems = async () => {
      if (!user) return;

      setLoading(true);
      setError(null);

      try {
        const bottomsResponse = await fetch(
          `http://localhost:8080/clothing_item/by_type?type=bottoms&userId=${user.id}`
        );
        const bottomsData = await bottomsResponse.json();
        if (bottomsData.length > 0) {
          setBottoms(bottomsData[0]);
        }

        const firstLayerTopResponse = await fetch(
          `http://localhost:8080/clothing_item/by_type?type=first_layer_top&userId=${user.id}`
        );
        const firstLayerTopData = await firstLayerTopResponse.json();
        if (firstLayerTopData.length > 0) {
          setTop(firstLayerTopData[0]);
        } else {
          const secondLayerTopResponse = await fetch(
            `http://localhost:8080/clothing_item/by_type?type=second_layer_top&userId=${user.id}`
          );
          const secondLayerTopData = await secondLayerTopResponse.json();
          if (secondLayerTopData.length > 0) {
            setTop(secondLayerTopData[0]);
          }
        }

        const shoesResponse = await fetch(
          `http://localhost:8080/clothing_item/by_type?type=shoes&userId=${user.id}`
        );
        const shoesData = await shoesResponse.json();
        if (shoesData.length > 0) {
          setShoes(shoesData[0]);
        }
      } catch (error) {
        setError("Error fetching outfit items");
        console.error("Error fetching outfit items:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchItems();
  }, [user]);

  if (!user) {
    return <p>Please log in to see your outfit.</p>;
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <div className="outfit-container">
      <div className="top-item">
        <h3>Top</h3>
        {top ? (
          <>
            <img src={top.clothingImage} alt={top.clothingName} />
            <p>{top.clothingName}</p>
            <button> Delete</button>
          </>
        ) : (
          <p>No top available</p>
        )}
      </div>
      <div className="bottom-item">
        <h3>Bottoms</h3>
        {bottoms ? (
          <>
            <img src={bottoms.clothingImage} alt={bottoms.clothingName} />
            <p>{bottoms.clothingName}</p>
            <button> Delete</button>
          </>
        ) : (
          <p>No bottoms available</p>
        )}
      </div>
      <div className="shoes-item">
        <h3>Shoes</h3>
        {shoes ? (
          <>
            <img src={shoes.clothingImage} alt={shoes.clothingName} />
            <p>{shoes.clothingName}</p>
            <button> Delete</button>
          </>
        ) : (
          <p>No shoes available</p>
        )}
      </div>
    </div>
  );
}

export default Outfit;
