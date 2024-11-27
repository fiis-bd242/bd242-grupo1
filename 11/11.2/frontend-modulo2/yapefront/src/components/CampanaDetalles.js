import React, { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import "./CampanaDetalles.css";

function CampanaDetalles() {
  const navigate = useNavigate();
  const location = useLocation();

  const codPrototipo = location.state?.codPrototipo;
  const [detalles, setDetalles] = useState(null);
  const [error, setError] = useState(null);
  const [isPreTestRegistered, setIsPreTestRegistered] = useState(false);

  useEffect(() => {
    if (codPrototipo) {
      axios
        .get(`http://host.docker.internal:8080/campanas/detalles/${codPrototipo}`)
        .then((response) => {
          console.log("Datos del prototipo:", response.data);
          setDetalles(response.data);
        })
        .catch((error) => {
          console.error("Error al obtener los detalles del prototipo", error);
          setError("No se pudieron cargar los detalles del prototipo.");
        });
    } else {
      setError("Código de prototipo no disponible.");
    }
  }, [codPrototipo]);

  const registrarPreTest = async () => {
    if (!codPrototipo || !detalles) {
      alert("Información insuficiente para registrar el Pre-Test.");
      return;
    }

    try {
      const data = {
        audienciaA: detalles.id_audiencia,
        fecha_inicio: new Date().toISOString(),
        cod_prototipo: codPrototipo,
        id_empleado: 1,
      };

      const response = await axios.post("http://host.docker.internal:8080/pretest/registrar", data);

      if (response.status === 200) {
        alert("Pre-Test registrado exitosamente.");
        setIsPreTestRegistered(true);
      }
    } catch (error) {
      console.error("Error al registrar el Pre-Test:", error);
      alert("No se pudo registrar el Pre-Test. Inténtalo nuevamente.");
    }
  };

  const actualizarEstado = async () => {
    if (!codPrototipo || !detalles) {
      alert("Información insuficiente para actualizar el estado.");
      return;
    }

    try {
      const data = {
        cod_prototipo: codPrototipo,
        estado: detalles.estado_prot,
      };

      const response = await axios.put(
        `http://host.docker.internal:8080/prototipos/actualizarEstado/${codPrototipo}`,
        data
      );

      if (response.status === 200) {
        alert("Estado actualizado exitosamente.");
      }
    } catch (error) {
      console.error("Error al actualizar el estado:", error);
      alert("No se pudo actualizar el estado. Inténtalo nuevamente.");
    }
  };

  if (error) {
    return <div>{error}</div>;
  }

  if (!detalles) {
    return <div>Cargando...</div>;
  }

  return (
    <div className="campana-detalles">
      <h1>Mis Propuestas</h1>
      <div className="campana-container">
        <h2>Campaña</h2>
        <p className="nombre-prototipo">{detalles.nombre_prot}</p>
        <div className="detalles-grid">
          <div className="image-section">
            {detalles.foto ? (
              <div className="image-placeholder">
                <img
                  src={`http://host.docker.internal:8080${detalles.foto}`}
                  alt="Foto de la campaña"
                />
              </div>
            ) : (
              <p>No hay foto disponible</p>
            )}
            <button
              className="edit-button"
              onClick={() => navigate("/introducir-recursos", { state: { codPrototipo } })}
            >
              Editar archivos
            </button>
          </div>
          <div className="info-section">
            <label5>Canal de difusión:</label5>
            <input type="text" value={detalles.canal_difusion || ""} readOnly />
            <label5>Autor:</label5>
            <input type="text" value={detalles.autor || ""} readOnly />
            <label5>Segmentación de audiencia:</label5>
            <input type="text" value={detalles.id_audiencia} readOnly />
            <input type="text" value={detalles.edad_rango || ""} readOnly />
            <div className="audience-segment">
              <input type="text" value={detalles.genero || ""} readOnly />
              <input type="text" value={detalles.ubicacion || ""} readOnly />
            </div>
            <label5>Presupuesto:</label5>
            <input type="text" value={detalles.presupuesto || ""} readOnly />
            <label5>Estado:</label5>
            <input type="text" value={detalles.estado_prot || ""} readOnly />
            <label5>Objetivos de la campaña:</label5>
            <textarea value={detalles.objetivos || ""} readOnly></textarea>
          </div>
        </div>
        <div className="buttons-section">
          <button onClick={() => navigate("/")}>Regresar</button>
          <button
            onClick={registrarPreTest}
            disabled={isPreTestRegistered}
          >
            {isPreTestRegistered ? "Pre-Test Registrado" : "Pre-Test"}
          </button>
          <button onClick={actualizarEstado}>Mandar Prototipo</button>
        </div>
      </div>
    </div>
  );
}

export default CampanaDetalles;
