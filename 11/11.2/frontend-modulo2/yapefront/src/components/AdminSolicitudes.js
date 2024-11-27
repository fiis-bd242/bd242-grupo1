import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"; // Importa useNavigate
import axios from "axios";
import "./AdminSolicitudes.css";

function AdminSolicitudes() {
  const navigate = useNavigate(); // Para manejar navegación
  const [solicitudes, setSolicitudes] = useState([]);

  useEffect(() => {
    axios
      .get("http://host.docker.internal:8080/prototipos/pendientes")
      .then((response) => {
        setSolicitudes(response.data);
      })
      .catch((error) => {
        console.error("Error al obtener las solicitudes:", error);
      });
  }, []);

  const handleViewPrototipo = (codPrototipo) => {
    navigate("/campana-detalles", { state: { codPrototipo } }); // Navegar pasando el cod_prototipo
  };

  // Función para cambiar el estado del prototipo
  const cambiarEstadoPrototipo = async (codPrototipo, nuevoEstado) => {
    try {
      await axios.put(`http://host.docker.internal:8080/prototipos/cambiarEstado/${codPrototipo}`, {
        estado: nuevoEstado,
      });
      alert(`El prototipo ${codPrototipo} ha sido ${nuevoEstado.toLowerCase()}.`);
      // Actualizar solicitudes tras el cambio
      setSolicitudes((prevSolicitudes) =>
        prevSolicitudes.filter((solicitud) => solicitud.cod_prototipo !== codPrototipo)
      );
    } catch (error) {
      console.error(`Error al cambiar el estado a ${nuevoEstado}:`, error);
      alert(`No se pudo cambiar el estado del prototipo ${codPrototipo}.`);
    }
  };

  return (
    <div className="admin-solicitudes-container">
      <mainadmin>
        <header>
          <h1>Administrar solicitudes</h1>
        </header>
        <section className="solicitudes">
          <table>
            <thead>
              <tr>
                <th>Código</th>
                <th>Fecha</th>
                <th>Responsable</th>
                <th>Prototipo</th>
                <th>Acciones</th>
                <th>Estado</th>
                <th>Archivos</th>
              </tr>
            </thead>
            <tbody>
              {solicitudes.length > 0 ? (
                solicitudes.map((solicitud, index) => (
                  <tr key={index}>
                    <td>{solicitud.cod_prototipo}</td>
                    <td>{solicitud.fecha_estado}</td>
                    <td>{solicitud.autor}</td>
                    <td>
                      <button onClick={() => handleViewPrototipo(solicitud.cod_prototipo)}>
                        Ver Prototipo
                      </button>
                    </td>
                    <td>
                      {/* Botones de acción para aprobar/rechazar */}
                      <button
                        onClick={() => cambiarEstadoPrototipo(solicitud.cod_prototipo, "Aprobado")}
                      >
                        ✔
                      </button>
                      <button
                        onClick={() => cambiarEstadoPrototipo(solicitud.cod_prototipo, "Rechazado")}
                      >
                        ✖
                      </button>
                    </td>
                    <td>{solicitud.estado_prot}</td>
                    <td>
          <button onClick={() => navigate("/ver-archivos", { state: { codPrototipo: solicitud.cod_prototipo } })}>
            Ver Archivos
          </button>
        </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="6">Cargando solicitudes...</td>
                </tr>
              )}
            </tbody>
          </table>
        </section>
      </mainadmin>
    </div>
  );
}

export default AdminSolicitudes;
