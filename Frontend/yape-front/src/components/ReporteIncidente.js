// src/components/Reporte.js


import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";

import '../styles/Reporte.css';  // Aquí puedes agregar un archivo CSS si lo necesitas para estilizar la tabla.

const Reporte = () => {
  const [reportes, setReportes] = useState([]);
  const [message, setMessage] = useState('');
  const navigate = useNavigate();
  const handleLogout = () => {
    // Tu lógica de logout
  };


  // Función para obtener el reporte detallado
  const fetchReporte = async () => {
    try {
      const response = await fetch('http://localhost:8080/reportes/categorias');
      if (response.ok) {
        const data = await response.json();
        setReportes(data);
      } else {
        setMessage('Error al obtener el reporte');
      }
    } catch (error) {
      setMessage('Error al obtener el reporte');
    }
  };

  // Llamar a la función fetchReporte cuando el componente se monta
  useEffect(() => {
    fetchReporte();
  }, []);

  return (
    <div className="reporte-container">
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
      <h1>Reporte de Incidentes por Categoría</h1>

      {/* Mostrar mensaje de éxito o error */}
      {message && <div className={`alert ${message.includes('Error') ? 'error' : 'success'}`}>{message}</div>}

      {/* Tabla del reporte */}
      {reportes.length > 0 ? (
        <table className="reporte-table">
          <thead>
            <tr>
              <th>Categoría</th>
              <th>Total</th>
              <th>Porcentaje</th>
              <th>Grado de Incidencia</th>
            </tr>
          </thead>
          <tbody>
            {reportes.map((reporte, index) => (
              <tr key={index}>
                <td>{reporte.Categoria}</td>
                <td>{reporte.Total}</td>
                <td>{reporte.Porcentaje}%</td>
                <td>{reporte['Grado de Incidencia']}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No hay datos disponibles para el reporte.</p>
      )}
    </div>
  );
};

export default Reporte;
