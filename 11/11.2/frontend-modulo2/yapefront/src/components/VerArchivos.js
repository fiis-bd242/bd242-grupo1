import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import "./VerArchivos.css";

function VerArchivos() {
  const location = useLocation(); // Obtener datos de navegación
  const { codPrototipo } = location.state || {}; // Extraer el codPrototipo
  const navigate = useNavigate();

  // Estado para almacenar los recursos
  const [recursos, setRecursos] = useState([]);

  // Estado para el tamaño de las imágenes
  const [imageSize, setImageSize] = useState(150); // Tamaño inicial de 150px

  // URL base del servidor para las rutas de recursos
  const BASE_URL = "http://host.docker.internal:8080";

  // Llamada a la API para obtener recursos
  useEffect(() => {
    if (codPrototipo) {
      axios
        .get(`${BASE_URL}/recursos/por-prototipo/${codPrototipo}`)
        .then((response) => {
          setRecursos(response.data); // Guardar los recursos en el estado
        })
        .catch((error) => {
          console.error("Error al obtener los recursos:", error);
        });
    }
  }, [codPrototipo]);

  // Función para obtener un recurso por tipo
  const getRecursoPorTipo = (idTipo) => {
    const recurso = recursos.find((rec) => rec.id_tipo_recurso === idTipo);
    if (!recurso) return null;
    return `${BASE_URL}${recurso.url_recurso}`;
  };

  return (
    <div className="ver-archivos-container">
      <header>
        <h1>Campaña publicitaria</h1>
        <h2>Archivos para el prototipo: {codPrototipo}</h2>
      </header>
      <main12>
        {/* Control deslizante para el tamaño de la imagen */}
        <div className="image-size-control">
          <label htmlFor="imageSize">Tamaño de imagen:</label>
          <input
            type="range"
            id="imageSize"
            min="200"
            max="500"
            value={imageSize}
            onChange={(e) => setImageSize(e.target.value)}
          />
          <span>{imageSize}px</span>
        </div>

        <div className="archivos-grid">
          {/* Espacio para Foto */}
          <div className="archivo-seccion">
            <h3>Foto</h3>
            {getRecursoPorTipo(1) ? (
              <img
                src={getRecursoPorTipo(1)}
                alt="Imagen"
                style={{ width: `${imageSize}px`, height: "auto" }}
              />
            ) : (
              <p>No disponible</p>
            )}
          </div>

          {/* Espacio para Video */}
          <div className="archivo-seccion">
            <h3>Video</h3>
            {getRecursoPorTipo(2) ? (
              <video width="320" height="240" controls>
                <source src={getRecursoPorTipo(2)} type="video/mp4" />
                Tu navegador no soporta el video.
              </video>
            ) : (
              <p>No disponible</p>
            )}
          </div>

          {/* Espacio para Guion */}
          <div className="archivo-seccion">
            <h3>Guion</h3>
            {getRecursoPorTipo(3) ? (
              <button
                className="archivo-boton"
                onClick={() => window.open(getRecursoPorTipo(3), "_blank")}
              >
                Ver Guion
              </button>
            ) : (
              <p>No disponible</p>
            )}
          </div>

          {/* Espacio para Documento */}
          <div className="archivo-seccion">
            <h3>Documento</h3>
            {getRecursoPorTipo(4) ? (
              <button
                className="archivo-boton"
                onClick={() => window.open(getRecursoPorTipo(4), "_blank")}
              >
                Ver Documento
              </button>
            ) : (
              <p>No disponible</p>
            )}
          </div>
        </div>
      </main12>
      <footer>
        <button className="btn" onClick={() => navigate(-1)}>Volver</button>
      </footer>
    </div>
  );
}

export default VerArchivos;
