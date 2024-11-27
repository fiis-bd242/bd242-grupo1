import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/EstadoSolicitudes.css";

const EstadoSolicitudes = () => {
  const navigate = useNavigate();
  const [solicitudes, setSolicitudes] = useState([]);
  const [mensaje, setMensaje] = useState("");

  const cargarSolicitudes = async () => {
    try {
      const idEmpleado = 1; // Cambia esto por el ID del empleado autenticado
      const response = await fetch(`http://localhost:8080/permisos/empleado/${idEmpleado}`);
      if (!response.ok) {
        throw new Error("Error al obtener las solicitudes");
      }
      const data = await response.json();
      setSolicitudes(data);
    } catch (error) {
      console.error("Error al cargar solicitudes:", error);
      setMensaje("No se pudieron cargar las solicitudes. Intenta nuevamente más tarde.");
    }
  };

  const formatearFechaHora = (fechaHora) => {
    if (!fechaHora) return "Sin cambios";
    const fecha = new Date(fechaHora);
    return fecha.toLocaleString("es-PE", {
      year: "numeric",
      month: "2-digit",
      day: "2-digit",
      hour: "2-digit",
      minute: "2-digit",
    });
  };

  useEffect(() => {
    cargarSolicitudes();
  }, []);

  return (
    <div className="estado-solicitudes-container">
      <h1>Estado de Solicitudes</h1>
      {mensaje && <p className="error">{mensaje}</p>}
      {solicitudes.length > 0 ? (
        <table className="solicitudes-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Tipo</th>
              <th>Fecha Inicio</th>
              <th>Fecha Final</th>
              <th>Estado</th>
              <th>Comentario</th>
              <th>Comentario del Admin</th>
              <th>Última Actualización</th>
            </tr>
          </thead>
          <tbody>
            {solicitudes.map((solicitud, index) => (
              <tr key={index}>
                <td>{solicitud.id_permiso}</td>
                <td>{solicitud.tipo_permiso}</td>
                <td>{solicitud.fecha_inicio}</td>
                <td>{solicitud.fecha_final}</td>
                <td className={`estado-${solicitud.estado.toLowerCase()}`}>{solicitud.estado}</td>
                <td>{solicitud.comentario || "Sin comentario"}</td>
                <td>{solicitud.comentario_admin || "Sin comentario"}</td>
                <td>{formatearFechaHora(solicitud.fecha_cambio_estado)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>No tienes solicitudes registradas.</p>
      )}
      <button onClick={() => navigate("/menu/empleado")}>Volver al menú</button>
    </div>
  );
};

export default EstadoSolicitudes;
