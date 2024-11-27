import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./SolicitudesAprobadas.css";

function SolicitudesAprobadas() {
  const navigate = useNavigate();
  const [solicitudes, setSolicitudes] = useState([]);
  const [error, setError] = useState(null);
  const [mensaje, setMensaje] = useState(null);

  // Función para formatear la fecha
  const formatFecha = (fechaISO) => {
    const fecha = new Date(fechaISO);
    const opciones = { day: "2-digit", month: "2-digit", year: "numeric" };
    return new Intl.DateTimeFormat("es-ES", opciones).format(fecha);
  };

  useEffect(() => {
    axios
      .get("http://host.docker.internal:8080/prototipos/aprobados")
      .then((response) => {
        console.log("Solicitudes aprobadas:", response.data);
        setSolicitudes(response.data);
      })
      .catch((error) => {
        console.error("Error al obtener solicitudes aprobadas:", error);
        setError("No se pudieron cargar las solicitudes aprobadas.");
      });
  }, []);

  // Función para marcar el prototipo como "Terminado"
  const marcarComoTerminado = async (codPrototipo) => {
    try {
      const response = await axios.put(
        `http://host.docker.internal:8080/prototipos/marcar-terminado/${codPrototipo}`
      );
      console.log(response.data);
      setMensaje(`Prototipo ${codPrototipo} marcado como 'Terminado'.`);
    } catch (error) {
      console.error("Error al marcar como terminado:", error);
      setMensaje(
        `No se pudo marcar el prototipo ${codPrototipo} como 'Terminado'.`
      );
    }
  };

  // Función para crear una nueva campaña publicitaria
  const crearNuevaCampana = async (codPrototipo) => {
    try {
      const response = await axios.put(
        `http://host.docker.internal:8080/campanas/NuevaCampana/${codPrototipo}`
      );
      console.log(response.data);
      setMensaje(`Campaña para el prototipo ${codPrototipo} creada exitosamente.`);
    } catch (error) {
      console.error("Error al crear nueva campaña:", error);
      setMensaje(
        `No se pudo crear la campaña para el prototipo ${codPrototipo}.`
      );
    }
  };

  // Función principal para manejar el botón "Lanzar"
  const manejarLanzar = async (codPrototipo) => {
    await marcarComoTerminado(codPrototipo);
    await crearNuevaCampana(codPrototipo);
  };

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div className="solicitudes-aprobadas">
      <header>
        <h1>Campaña publicitaria</h1>
        <p>10:50 a.m.</p>
      </header>
      <section>
        <button className="btn-regresar" onClick={() => navigate(-1)}>
          Regresar
        </button>
        <h2>Solicitudes aprobadas</h2>
        {mensaje && <p className="mensaje">{mensaje}</p>}
        <div className="filtro">
          <p>Ordenar por</p>
          <select>
            <option value="fecha">Fecha</option>
            <option value="nombre">Nombre</option>
            <option value="estado">Estado</option>
          </select>
        </div>
        <div className="lista-solicitudes">
          {solicitudes.map((solicitud) => (
            <div className="solicitud" key={solicitud.cod_prototipo}>
              <img
                src={`http://host.docker.internal:8080${solicitud.url_recurso}`}
                alt="Imagen campaña"
              />
              <div>
                <p>
                  <strong>Nombre:</strong> {solicitud.nombre_prot}
                </p>
                <p>
                  <strong>Descripción:</strong> {solicitud.descripcion}
                </p>
                <p>
                  <strong>Fecha:</strong> {formatFecha(solicitud.fecha_estado)}
                </p>
                <button onClick={() => manejarLanzar(solicitud.cod_prototipo)}>
                  Lanzar
                </button>
                <button
                  onClick={() =>
                    navigate("/campana-detalles", {
                      state: { codPrototipo: solicitud.cod_prototipo },
                    })
                  }
                >
                  Ver más
                </button>
              </div>
            </div>
          ))}
        </div>
      </section>
    </div>
  );
}

export default SolicitudesAprobadas;
