import React, { useState, useContext } from "react";
import { UserContext } from "./UserContext";

function Auth() {
  const [registerUsername, setRegisterUsername] = useState("");
  const [registerPassword, setRegisterPassword] = useState("");
  const [loginUsername, setLoginUsername] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const { user, setUser } = useContext(UserContext);
  const [errorsMsg, setErrorsMsg] = useState("");
  const [successMsg, setSuccessMessage] = useState("");

  async function handleRegister() {
    const response = await fetch("http://localhost:8080/app_user/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userName: registerUsername,
        password: registerPassword,
      }),
    });
    if (response.ok) {
      setSuccessMessage("Registered SuccessFully!");
    } else {
      setErrorsMsg("Failed to Register!");
    }
  }

  async function handleLogin() {
    const response = await fetch("http://localhost:8080/app_user/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        userName: loginUsername,
        password: loginPassword,
      }),
    });
    const data = await response.json();
    if (response.ok) {
      setUser(data);
      setSuccessMessage("Login success!");
    } else {
      setErrorsMsg("Login failed!");
    }
  }

  function handleLogout() {
    setUser(null);
  }

  return (
    <>
      <div>
        {successMsg ? (
          <div>{successMsg}</div>
        ) : (
          <div>{errorsMsg ? <div>{errorsMsg}</div> : <div></div>}</div>
        )}
      </div>

      <div>
        {user ? (
          <>
            <h2 className="weder-title">Welcome {user.userName}</h2>
            <button className="weder-title" onClick={handleLogout}>
              Logout
            </button>
          </>
        ) : (
          <>
            <div className="weder-title">
              <h2>Register</h2>
              <input
                type="text"
                placeholder="Username"
                value={registerUsername}
                onChange={(e) => setRegisterUsername(e.target.value)}
              />
              <input
                type="password"
                placeholder="Password"
                value={registerPassword}
                onChange={(e) => setRegisterPassword(e.target.value)}
              />
              <button onClick={handleRegister}>Register</button>

              <h2>Login</h2>
              <input
                type="text"
                placeholder="Username"
                value={loginUsername}
                onChange={(e) => setLoginUsername(e.target.value)}
              />
              <input
                type="password"
                placeholder="Password"
                value={loginPassword}
                onChange={(e) => setLoginPassword(e.target.value)}
              />
              <button onClick={handleLogin}>Login</button>
            </div>
          </>
        )}
      </div>
    </>
  );
}

export default Auth;
