import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import "./Notas.css";
import Sidebar from "./Sidebar";

const Notas = () => {
  const { id_test } = useParams();
  const [resultados, setResultados] = useState([]);
  const [estadisticas, setEstadisticas] = useState(null);
  const [nombre, setNombre] = useState("");
  const [puntaje, setPuntaje] = useState("");

  useEffect(() => {
    if (id_test && !isNaN(id_test)) {
      fetchResultados();
      fetchEstadisticas();
    }
  }, [id_test, nombre, puntaje]);

  const fetchResultados = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/test/${id_test}/notas`, {
        params: { nombre, puntaje: puntaje !== "" ? puntaje : undefined },
      });
      setResultados(response.data);
    } catch (error) {
      console.error("Error al obtener resultados:", error);
    }
  };

  const fetchEstadisticas = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/test/${id_test}/estadisticas`);
      setEstadisticas(response.data);
    } catch (error) {
      console.error("Error al obtener estadísticas:", error);
    }
  };

  return (
    <div className="notas-container">
      <Sidebar />
      <main className="notas-content">
        <h2>Notas del Test:</h2>

        <div className="filter-section">
          <input
            type="text"
            placeholder="Buscar por empleado"
            value={nombre}
            onChange={(e) => setNombre(e.target.value)}
            className="filter-input"
          />
          <input
            type="number"
            placeholder="Filtrar por puntaje"
            value={puntaje}
            onChange={(e) => setPuntaje(e.target.value ? parseInt(e.target.value, 10) : "")}
            className="filter-input"
          />
          <button onClick={fetchResultados} className="refresh-button">
            🔄
          </button>
        </div>

        <table className="notas-table">
          <thead>
            <tr>
              <th>Código</th>
              <th>Empleado</th>
              <th>Puntaje</th>
              <th>Comentario</th>
              <th>Añadir Comentario</th>
            </tr>
          </thead>
          <tbody>
            {resultados.length === 0 ? (
              <tr>
                <td colSpan="5">No hay resultados para mostrar</td>
              </tr>
            ) : (
              resultados.map((resultado) => (
                <tr key={resultado.codigo}>
                  <td>{resultado.codigo}</td>
                  <td>{resultado.empleado}</td>
                  <td>{resultado.puntaje}</td>
                  <td>{resultado.comentario}</td>
                  <td>
                    <button className="add-comment-button">+</button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>

        {estadisticas && (
          <div className="batch-info">
            <h3>Estadísticas del Test</h3>
            <ul>
              <li><strong>Total de empleados:</strong> {estadisticas.total_empleados}</li>
              <li><strong>Promedio de puntaje:</strong> {estadisticas.promedio_puntaje.toFixed(2)}</li>
              <li><strong>Porcentaje de destacados:</strong> {estadisticas.porcentaje_destacados.toFixed(2)}%</li>
            </ul>
          </div>
        )}
      </main>
    </div>
  );
};

export default Notas;