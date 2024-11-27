// src/components/Reporte.js


import React, { useState, useEffect } from 'react';
import '../styles/Reporte.css';  // Aquí puedes agregar un archivo CSS si lo necesitas para estilizar la tabla.

const Reporte = () => {
  const [reportes, setReportes] = useState([]);
  const [message, setMessage] = useState('');

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
