import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/ListaSolicitudes.css";

const ListaSolicitudes = () => {
  const [solicitudes, setSolicitudes] = useState([]);
  const [filtros, setFiltros] = useState({
    estado: "",
    tipoPermiso: "",
  });

  const [modalRechazo, setModalRechazo] = useState(false);
  const [comentarioRechazo, setComentarioRechazo] = useState("");
  const [permisoRechazo, setPermisoRechazo] = useState(null);

  const navigate = useNavigate();

  // Función para cargar solicitudes desde el backend
  const cargarSolicitudes = async () => {
    try {
      const filtrosMapeados = {
        estado: filtros.estado,
        tipoPermiso: filtros.tipoPermiso,
      };

      const queryParams = new URLSearchParams(filtrosMapeados).toString();
      const response = await fetch(`http://localhost:8080/permisos?${queryParams}`);
      if (!response.ok) {
        throw new Error("Error al obtener las solicitudes");
      }
      const data = await response.json();
      setSolicitudes(data);
    } catch (error) {
      console.error("Error al cargar solicitudes:", error);
    }
  };

  // Función para aprobar un permiso
  const aprobarPermiso = async (idPermiso) => {
    try {
      const response = await fetch(`http://localhost:8080/permisos/${idPermiso}?nuevoEstado=Aprobado`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          nuevoEstado: "Aprobado",
        }),
      });

      if (!response.ok) {
        throw new Error("Error al aprobar el permiso");
      }

      alert("Permiso aprobado exitosamente.");
      cargarSolicitudes(); // Recargar la lista de solicitudes
    } catch (error) {
      console.error("Error al aprobar el permiso:", error);
      alert("Hubo un error al aprobar el permiso. Intenta nuevamente.");
    }
  };

  // Función para abrir el modal de rechazo
  const abrirModalRechazo = (idPermiso) => {
    setPermisoRechazo(idPermiso);
    setModalRechazo(true);
  };

  // Función para rechazar un permiso con comentario
  const rechazarPermiso = async () => {
    if (!comentarioRechazo.trim()) {
      alert("Por favor, escribe un motivo para el rechazo.");
      return;
    }
  
    try {
      const response = await fetch(`http://localhost:8080/permisos/${permisoRechazo}?nuevoEstado=Rechazado&comentarioAdmin=${encodeURIComponent(comentarioRechazo)}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
      });
  
      if (!response.ok) {
        throw new Error("Error al rechazar el permiso");
      }
  
      alert("Permiso rechazado exitosamente.");
      setModalRechazo(false);
      setComentarioRechazo("");
      cargarSolicitudes(); // Recargar la lista de solicitudes
    } catch (error) {
      console.error("Error al rechazar el permiso:", error);
      alert("Hubo un error al rechazar el permiso. Intenta nuevamente.");
    }
  };

  useEffect(() => {
    cargarSolicitudes();
  }, []);

  return (
    <div className="lista-solicitudes-container">
      <h1>Solicitudes de Permiso</h1>

      {/* Filtros */}
      <form
        className="filtros-form"
        onSubmit={(e) => {
          e.preventDefault();
          cargarSolicitudes();
        }}
      >
        <select
          name="estado"
          value={filtros.estado}
          onChange={(e) => setFiltros({ ...filtros, estado: e.target.value })}
        >
          <option value="">Todos los estados</option>
          <option value="Pendiente">Pendiente</option>
          <option value="Aprobado">Aprobado</option>
          <option value="Rechazado">Rechazado</option>
        </select>
        <button type="submit">Buscar</button>
      </form>

      {/* Tabla de solicitudes */}
      <div className="tabla-container">
        <table className="tabla-solicitudes">
          <thead>
            <tr>
              <th>ID Permiso</th>
              <th>Empleado</th>
              <th>Tipo Permiso</th>
              <th>Fecha Inicio</th>
              <th>Fecha Final</th>
              <th>Estado</th>
              <th>Comentario</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {solicitudes.map((solicitud) => (
              <tr key={solicitud.id_permiso}>
                <td>{solicitud.id_permiso}</td>
                <td>
                  {solicitud.nombre} {solicitud.apellido}
                </td>
                <td>{solicitud.tipo_permiso}</td>
                <td>{solicitud.fecha_inicio}</td>
                <td>{solicitud.fecha_final}</td>
                <td>{solicitud.estado}</td>
                <td>{solicitud.comentario || "Sin comentario"}</td>
                <td>
                  {solicitud.estado === "Pendiente" && (
                    <>
                      <button
                        className="btn-approve"
                        onClick={() => aprobarPermiso(solicitud.id_permiso)}
                      >
                        ✅
                      </button>
                      <button
                        className="btn-reject"
                        onClick={() => abrirModalRechazo(solicitud.id_permiso)}
                      >
                        ❌
                      </button>
                    </>
                  )}
                  <button
                    className="ver-mas-btn"
                    onClick={() => navigate(`/menu/gestion-empleados/solicitudes/detalle-permiso/${solicitud.id_permiso}`)}
                  >
                    Ver más
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal para rechazo */}
      {modalRechazo && (
        <div className="modal">
          <div className="modal-content">
            <h3>Rechazar Permiso</h3>
            <textarea
              value={comentarioRechazo}
              onChange={(e) => setComentarioRechazo(e.target.value)}
              placeholder="Escribe un motivo para el rechazo..."
            />
            <div className="modal-actions">
              <button className="btn-confirm" onClick={rechazarPermiso}>
                Confirmar
              </button>
              <button
                className="btn-cancel"
                onClick={() => {
                  setModalRechazo(false);
                  setComentarioRechazo("");
                }}
              >
                Cancelar
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ListaSolicitudes;
