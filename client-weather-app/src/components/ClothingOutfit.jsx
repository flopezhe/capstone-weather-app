import React, { useState, useEffect, useContext } from "react";
import { UserContext } from "./UserContext";
import { useNavigate } from "react-router-dom";

function Outfit() {
  const [bottoms, setBottoms] = useState(null);
  const [top, setTop] = useState(null);
  const [shoes, setShoes] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const { user } = useContext(UserContext);

  async function getNewTop() {
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

      if (!secondLayerTopResponse.ok) {
        setError("No More Tops!");
      }
      const secondLayerTopData = await secondLayerTopResponse.json();
      if (!secondLayerTopData.ok) {
        setError("No more tops!");
      }
      if (secondLayerTopData.length > 0) {
        setTop(secondLayerTopData[0]);
        setError("");
      }
    }
  }

  async function getNewBottoms() {
    const bottomsResponse = await fetch(
      `http://localhost:8080/clothing_item/by_type?type=bottoms&userId=${user.id}`
    );
    if (!bottomsResponse.ok) {
      setError("No more bottoms!");
    }
    const bottomsData = await bottomsResponse.json();
    if (!bottomsData.ok) {
      setError("No more bottoms!");
    }
    if (bottomsData.length > 0) {
      setBottoms(bottomsData[0]);
      setError("");
    }
  }

  async function getNewShoes() {
    const shoesResponse = await fetch(
      `http://localhost:8080/clothing_item/by_type?type=shoes&userId=${user.id}`
    );
    if (!shoesResponse.ok) {
      setError("No more shoes!");
    }
    const shoesData = await shoesResponse.json();

    if (!shoesData.ok) {
      setError("No more shoes!");
    }
    if (shoesData.length > 0) {
      setShoes(shoesData[0]);
      setError("");
    }
  }

  useEffect(() => {
    async function fetchItems() {
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
    }

    fetchItems();
  }, [user]);

  function handleTopDelete() {
    fetch(`http://localhost:8080/clothing_item/${top.id}`, {
      method: "DELETE",
    })
      .then(setTop())
      .then(setError("Deleted Successfully!"));
  }

  function handleBottomsDelete() {
    fetch(`http://localhost:8080/clothing_item/${bottoms.id}`, {
      method: "DELETE",
    })
      .then(setBottoms())
      .then(setError("Deleted Successfully!"));
  }

  function handleShoesDelete() {
    fetch(`http://localhost:8080/clothing_item/${shoes.id}`, {
      method: "DELETE",
    })
      .then(setShoes())
      .then(setError("Deleted Successfully!"));
  }

  if (!user) {
    return <p className="weder-title">Please log in to see your outfit.</p>;
  }

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <>
      <div className="outfit-container">
        {error ? <div>{error}</div> : <div></div>}
        <div className="top-item">
          <h3>Top</h3>
          {top ? (
            <>
              <img
                className="small-top-css"
                src={top.clothingImage}
                alt={top.clothingName}
              />
              <p>{top.clothingName}</p>
              <button onClick={handleTopDelete}> Delete</button>
            </>
          ) : (
            <>
              <p>No top</p>
              <button onClick={getNewTop}> Get New Top</button>
            </>
          )}
        </div>
        <div className="bottom-item">
          <h3>Bottoms</h3>
          {bottoms ? (
            <>
              <img src={bottoms.clothingImage} alt={bottoms.clothingName} />
              <p>{bottoms.clothingName}</p>
              <button onClick={handleBottomsDelete}> Delete</button>
            </>
          ) : (
            <>
              <p>No Bottoms</p>
              <button onClick={getNewBottoms}> Get New Bottoms</button>
            </>
          )}
        </div>
        <div className="shoes-item">
          <h3>Shoes</h3>
          {shoes ? (
            <>
              <img src={shoes.clothingImage} alt={shoes.clothingName} />
              <p>{shoes.clothingName}</p>
              <button onClick={handleShoesDelete}> Delete</button>
            </>
          ) : (
            <>
              <p>No Shoes</p>
              <button onClick={getNewShoes}> Get New Shoes</button>
            </>
          )}
        </div>
      </div>
    </>
  );
}

export default Outfit;
