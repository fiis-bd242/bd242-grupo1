import React from "react";
import { useNavigate } from "react-router-dom";
import "./CampaignDashboard.css";

function CampaignDashboard() {
  const navigate = useNavigate();
  const handleCreatePublicidadClick = () => {
    // Redirigir al login
    navigate("/login1");
  };
  return (
    <div className="campaign-dashboard">
      <h1>Campaña publicitaria</h1>
      <div className="campaign-options">
        <div className="option-card">
          <img src="vercampaña.png" alt="Ver Campaña" />
          <p>Ver las campañas publicitarias</p>
          <button onClick={() => navigate("/ver-campana")}>Ver Campañas</button>

        </div>
        <div className="option-card">
          <img src="definiciondetalle.png" alt="Crear publicidad" />
          <p>Definición de detalles de la publicidad</p>
          <button onClick={handleCreatePublicidadClick}>Crear publicidad</button>
        </div>
        <div className="option-card">
          <img src="administrarpublicidad.png" alt="Administrar publicidad" />
          <p>Administración (Solo para ejecutivos)</p>
          <button onClick={() => navigate("/login2")}>
            Administrar publicidad
          </button>
        </div>
      </div>
    </div>
  );
}

export default CampaignDashboard;
