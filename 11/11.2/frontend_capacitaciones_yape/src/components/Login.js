import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Login.css"; // Asegúrate de incluir este archivo de estilo
import logoYape from "../assets/logo-yape.png";
import logoBCP from "../assets/logo-bcp.png";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/api/login", {
        username: username,
        password: password,
      });

      if (response.data.message === "Login successful") {
        localStorage.setItem("userId", response.data.userId);
        navigate("/menu");
      }
    } catch (error) {
      setErrorMessage("Credenciales incorrectas. Por favor, intenta de nuevo.");
    }
  };

  return (
    <div className="login-container">
      <div className="login-logo">
        {/* Asegúrate de incluir las imágenes en la carpeta src/assets */}
        <img src={logoYape} alt="Yape Logo" />
        <img src={logoBCP} alt="BCP Logo" className="bcp-logo" />
      </div>
      <div className="login-form">
        <h2>Login</h2>
        <p>¡Bienvenido! Por favor, inicia sesión para continuar</p>
        <form onSubmit={handleLogin}>
          <div className="input-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <div className="checkbox-group">
            <input type="checkbox" id="remember-me" />
            <label htmlFor="remember-me">Recuérdame</label>
          </div>
          <button type="submit" className="btn-login">ENTRAR</button>
        </form>
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        <p className="forgot-password">¿Olvidaste tu contraseña?</p>
      </div>
    </div>
  );
};

export default Login;