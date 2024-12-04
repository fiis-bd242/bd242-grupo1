import React, { useEffect, useState } from "react";
import "./Sidebar.css";

// Importación de imágenes
import logoYape from "../assets/logo-yape.png";
import defaultUser from "../assets/default-user.png";
import axios from "axios";

const Sidebar = () => {
  const [nombreCompleto, setNombreCompleto] = useState("");

  useEffect(() => {
    const userId = localStorage.getItem("userId");
    if (userId) {
      axios
        .get(`http://localhost:8080/api/empleado/${userId}`)
        .then((response) => {
          const { nombre, apellido } = response.data;
          setNombreCompleto(`${apellido}, ${nombre}`);
        })
        .catch((error) => {
          console.error("Error al obtener los datos del empleado:", error);
        });
    }
  }, []);

  return (
    <div className="sidebar">
      <div className="sidebar-logo">
        <img src={logoYape} alt="Yape Logo" />
      </div>
      <div className="sidebar-user">
        <img src={defaultUser} alt="User" />
        <p>
          ¡Bienvenido/a! <br />
          {nombreCompleto || "PATERNO MATERNO, NOMBRES"}
        </p>
      </div>
      <nav className="sidebar-menu">
        <button className="sidebar-button">Solicitudes</button>
        <button className="sidebar-button">Crear Capac.</button>
        <button className="sidebar-button">Mis capac.</button>
      </nav>
      <button className="sidebar-logout">Cerrar sesión</button>
    </div>
  );
};

export default Sidebar;