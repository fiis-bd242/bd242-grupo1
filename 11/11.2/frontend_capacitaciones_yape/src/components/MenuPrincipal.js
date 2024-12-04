import React from "react";
import { useNavigate } from "react-router-dom";
import Sidebar from "./Sidebar"; // Importar el menú lateral
import "./MenuPrincipal.css"; // Asegúrate de incluir este archivo CSS

// Importación de imágenes
import iconSolicitudes from "../assets/icon-solicitudes.png";
import iconCrear from "../assets/icon-crear.png";
import iconMisCapacitaciones from "../assets/icon-mis-capacitaciones.png";

const MenuPrincipal = () => {
  const navigate = useNavigate();

  const handleButtonClick = (path) => {
    navigate(path);
  };

  return (
    <div className="menu-container">
      <Sidebar />  {/* Ahora Sidebar maneja la lógica del nombre del usuario */}
      <div className="menu-content">
        <h2>Menú principal</h2>
        <p>¿Qué haremos hoy?</p>
        <div className="menu-options">
          <div
            className="menu-card"
            onClick={() => handleButtonClick("/solicitudes")}
          >
            <img src={iconSolicitudes} alt="Solicitudes" />
            <h3>Solicitudes</h3>
            <p>Administra las capacitaciones solicitadas</p>
          </div>
          <div
            className="menu-card"
            onClick={() => handleButtonClick("/crear-capacitacion")}
          >
            <img src={iconCrear} alt="Crear Capacitación" />
            <h3>Crear Capacitación</h3>
            <p>Crea capacitaciones acorde a lo solicitado</p>
          </div>
          <div
            className="menu-card"
            onClick={() => handleButtonClick("/mis-capacitaciones")}
          >
            <img src={iconMisCapacitaciones} alt="Mis Capacitaciones" />
            <h3>Mis capacitaciones</h3>
            <p>Mira las capacitaciones que lideras</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MenuPrincipal;