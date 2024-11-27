import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import "./Reportes.css";

const Reportes = () => {
  const { codCampana } = useParams(); // Capturar el parámetro de la URL
  const navigate = useNavigate(); // Hook para manejar la navegación
  const [reportData, setReportData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchReportData = async () => {
      try {
        const response = await axios.get(
          `http://host.docker.internal:8080/campanas/reporte/${codCampana}`
        );
        setReportData(response.data);
      } catch (err) {
        setError("Error al cargar los datos del reporte.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchReportData();
  }, [codCampana]);

  if (loading) {
    return <div className="loading">Cargando datos...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div className="reportes-container">
      <h1>Reporte de Campaña</h1>
      <button className="back-button" onClick={() => navigate(-1)}>
        Volver
      </button>
      <table className="report-table">
        <thead>
          <tr>
            <th>Nombre Canal</th>
            <th>Código Prototipo</th>
            <th>Código Canal</th>
            <th>Impresiones</th>
            <th>Clics</th>
            <th>Conversiones</th>
            <th>CTR (%)</th>
            <th>Tasa de Conversión (%)</th>
            <th>Costo por Conversión (CPC)</th>
          </tr>
        </thead>
        <tbody>
          {reportData.map((data, index) => (
            <tr key={index}>
              <td>{data.nombre_canal}</td>
              <td>{data.cod_prototipo}</td>
              <td>{data.cod_canal}</td>
              <td>{data.impresiones}</td>
              <td>{data.clics}</td>
              <td>{data.conversiones}</td>
              <td>{data.ctr.toFixed(2)}</td>
              <td>{data.conversionRate.toFixed(2)}</td>
              <td>{data.cpc.toFixed(2)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Reportes;
