import React, { useEffect, useState, useCallback } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./PrototiposEmpleado.css";

const PrototiposEmpleado = () => {
  const { idEmpleado } = useParams();
  const [prototipos, setPrototipos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const fetchPrototipos = useCallback(async () => {
    try {
      const response = await fetch(
        `http://host.docker.internal:8080/prototipos/empleado/${idEmpleado}`
      );
      if (!response.ok) {
        throw new Error("Error al obtener los prototipos del empleado");
      }
      const data = await response.json();
      setPrototipos(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }, [idEmpleado]);

  useEffect(() => {
    fetchPrototipos();
  }, [fetchPrototipos]);

  // Función para manejar el clic en "Ver más"
  const handleVerMas = (codPrototipo) => {
    navigate("/campana-detalles", { state: { codPrototipo } });
  };

  return (
    <div className="app1">
      <header className="header1">
        <h1>Prototipos del Empleado</h1>
        <button className="back-button" onClick={() => navigate(-1)}>
          Volver
        </button>
      </header>
      <main357 className="main-content1">
        {loading && <p>Cargando...</p>}
        {error && <p>Error: {error}</p>}
        {!loading && !error && (
          <div className="proposals-list1">
            {prototipos.map((prototipo, index) => (
              <div key={index} className="proposal-card1">
                {prototipo.url_recurso ? (
                  <img
                    src={`http://host.docker.internal:8080${prototipo.url_recurso}`}
                    alt={`Imagen de ${prototipo.descripcion}`}
                    className="proposal-image"
                  />
                ) : (
                  <div className="no-image">Sin imagen</div>
                )}
                <div className="proposal-info">
                  <p>
                    <strong>Descripción:</strong> {prototipo.descripcion || "Sin descripción"}
                  </p>
                  <p>
                    <strong>Fecha de creación:</strong>{" "}
                    {new Date(prototipo.Fecha_creacion).toLocaleDateString()}
                  </p>
                  <p>
                    <strong>Estado:</strong> {prototipo.estado_prot || "N/A"}
                  </p>
                  {/* Botón "Ver más" con redirección */}
                  <button
                    className="ver-mas-button"
                    onClick={() => handleVerMas(prototipo.cod_prototipo)}
                  >
                    Ver más
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </main357>
    </div>
  );
};

export default PrototiposEmpleado;
