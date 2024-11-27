import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom"; // Importamos Link
import "../components/Login3.css";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const users = [
    { username: "Jane", password: "12345", idEmpleado: 2},
    { username: "Michael", password: "12345", idEmpleado: 3},
    { username: "Emily", password: "12345", idEmpleado: 4},
    { username: "David", password: "12345", idEmpleado: 5},
    { username: "Sarah", password: "12345", idEmpleado: 6},
    
  ];

  const handleSubmit = (e) => {
    e.preventDefault();
    const user = users.find(
      (u) => u.username === username && u.password === password
    );
  
    if (user) {
      // Guardar idEmpleado en localStorage
      localStorage.setItem("idEmpleado", user.idEmpleado);
  
      // Redirigir a PrototiposEmpleado con idEmpleado
      navigate(`/prototipos/empleado/${user.idEmpleado}`);
    } else {
      setError("Credenciales incorrectas");
    }
  };
  
  
  return (
    <div className="split-screen">
      <div className="left-side">
        <div className="imagenes">
          <img
            alt="Logo yape"
            height="250"
            src="https://socialpubli.com/blog/wp-content/uploads/2021/06/logo.png"
          />
        </div>
      </div>
      <div className="right-side">
        <div className="login-container">
          <h1>Login</h1>
          <form onSubmit={handleSubmit}>
            <label>
              Usuario:
              <br />
              <input
                type="text"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </label>
            <label>
              Contraseña:
              <br />
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </label>
            <br />
            <button type="submit">Iniciar sesión</button>
            {error && <p className="error">{error}</p>}
            <br />
            <Link to="/register">¿Olvidaste tu contraseña?</Link>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
