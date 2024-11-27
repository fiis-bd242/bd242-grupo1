import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./NuevaAudiencia.css";

function NuevaAudiencia() {
  const navigate = useNavigate();

  // Estados para manejar los datos del formulario
  const [edadRango, setEdadRango] = useState("");
  const [genero, setGenero] = useState("");
  const [ubicacion, setUbicacion] = useState("");
  const [mensaje, setMensaje] = useState(""); // Para el mensaje en el textarea

  // Función para manejar el envío de datos
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Crear un objeto con los datos de la audiencia
    const audienciaData = {
      edad_rango: edadRango,
      genero: genero,
      ubicacion: ubicacion,
    };

    try {
      // Hacer una solicitud POST para buscar o crear la audiencia
      const response = await fetch("http://host.docker.internal:8080/api/audiencia/buscar-o-crear", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(audienciaData),
      });

      const data = await response.json();

      if (response.ok) {
        // Si la audiencia existe o fue creada, actualizamos el mensaje en el textarea
        setMensaje(
          `La audiencia que usted colocó ya existe. El id_audiencia es: ${data.id_audiencia}`
        );
      } else {
        // En caso de error (si no se pudo encontrar o crear la audiencia)
        setMensaje("Hubo un problema al procesar su solicitud.");
      }
    } catch (error) {
      // En caso de error en la solicitud
      setMensaje("Error en la conexión con el servidor.");
    }
  };

  return (
    <div className="nueva-audiencia">
      <main>
        <header>
          <h1>Nueva audiencia</h1>
          <p>10:50 a.m.</p>
        </header>
        <section className="audiencia-form">
          <h2>Segmentación de audiencia:</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-group">
              <input
                type="text"
                placeholder="Rango edades (NN-NN)"
                value={edadRango}
                onChange={(e) => setEdadRango(e.target.value)}
                required
              />
              <input
                type="text"
                placeholder="Género (M,F,MF,FM)"
                value={genero}
                onChange={(e) => setGenero(e.target.value)}
                required
              />
              <input
                type="text"
                placeholder="Ubicación"
                value={ubicacion}
                onChange={(e) => setUbicacion(e.target.value)}
                required
              />
            </div>
            <div className="message-box">
              <textarea
                placeholder="Escribe un mensaje aquí..."
                value={mensaje}
                readOnly
                rows="4"
              ></textarea>
            </div>
            <div className="form-buttons">
              <button className="regresar" onClick={() => navigate(-1)}>
                Regresar
              </button>
              <button className="subir" type="submit">
                Subir
              </button>
            </div>
          </form>
        </section>
      </main>
    </div>
  );
}

export default NuevaAudiencia;
