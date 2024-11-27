import React from "react";
import { useNavigate } from "react-router-dom";
import "./Sidebar.css";

function Sidebar() {
  const navigate = useNavigate();

  return (
    <div className="sidebar">
      <h2>Yape</h2>
      <div className="sidebar-buttons">
        <button className="sidebar-button" onClick={() => navigate("/promociones")}>
          Promociones
        </button>
        <button className="sidebar-button selected" onClick={() => navigate("/")}>
          Campañas
        </button>
        <button className="sidebar-button" onClick={() => navigate("/puestos")}>
          Puestos
        </button>
        <button className="sidebar-button" onClick={() => navigate("/reportes")}>
          Reportes
        </button>
        <button className="sidebar-button" onClick={() => navigate("/tipificaciones")}>
          Tipificaciones
        </button>
        <button className="sidebar-button" onClick={() => navigate("/yapeos")}>
          Yapeos
        </button>
      </div>
      <button className="logout-button">Cerrar sesión</button>
    </div>
  );
}

export default Sidebar;
