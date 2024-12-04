import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Incidentes.css";

const Incidentes = () => {
  const [incidentes, setIncidentes] = useState([]);
  const [message, setMessage] = useState("");
  const navigate = useNavigate();
  const handleLogout = () => {
    // Tu lógica de logout
  };

  // Fetch de incidentes desde el backend
  const fetchIncidentes = async () => {
    try {
      const response = await fetch("http://localhost:8080/incidentes/obtener-incidentes");
      if (response.ok) {
        const data = await response.json();
        setIncidentes(data);
      } else {
        setMessage("Error al obtener los incidentes.");
      }
    } catch (error) {
      setMessage("Error de conexión con el servidor.");
    }
  };

  // Cargar incidentes al montar el componente
  useEffect(() => {
    fetchIncidentes();
  }, []);

  // Manejar el redireccionamiento para crear diagnóstico
  const handleCrearDiagnostico = (idIncidente) => {
    navigate(`/crear-diagnostico/${idIncidente}`);
  };

  return (
    <div className="incidentes-container">
      {/* Barra lateral */}
      <aside className="sidebar">
        <div className="sidebar-content">
          <div className="logo-container">
            <img
              src="https://vectorseek.com/wp-content/uploads/2023/09/Yape-App-Logo-Vector.svg-.png"
              alt="Logo"
              className="logo"
              onClick={() => navigate('/menuIncidente')}
            />
          </div>
          
          
          <nav className="nav-menu">
            <button className="nav-button" onClick={() => navigate('/menuIncidente/registro')}>Registro</button>
            <button className="nav-button" onClick={() => navigate('/menuIncidente/Incidentes')}>Diagnostico</button>
            <button className="nav-button" onClick={() => navigate('/menuIncidente/PostMortem')}>Analisis-Postmortem</button>
          </nav>
        </div>

        <button className="logout-button" onClick={handleLogout}>
          Cerrar sesión
        </button>
      </aside>
      <h1>Lista de Incidentes</h1>
      {message && <div className="alert error">{message}</div>}

      {incidentes.length > 0 ? (
        <table className="incidentes-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Mensaje</th>
              <th>Categoría</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {incidentes.map((incidente) => (
              <tr key={incidente.id_incidente}>
                <td>{incidente.id_incidente}</td>
                <td>{incidente.mensaje_incidente}</td>
                <td>{incidente.categoria}</td>
                <td>
                  <button
                    className="btn-crear-diagnostico"
                    onClick={() => handleCrearDiagnostico(incidente.id_incidente)}
                  >
                    Crear Diagnóstico
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No hay incidentes registrados.</p>
      )}
    </div>
  );
};

export default Incidentes;
