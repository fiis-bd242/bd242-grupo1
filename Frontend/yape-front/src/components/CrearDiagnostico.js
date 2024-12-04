import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "../styles/CrearDiagnostico.css";

const CrearDiagnostico = () => {
  const { idIncidente } = useParams(); // Obtenemos el ID del incidente de la URL
  const navigate = useNavigate();

  // Estados para el formulario
  const [descripcion, setDescripcion] = useState("");
  const [estado, setEstado] = useState("");
  const [prioridad, setPrioridad] = useState("");
  const [comentario, setComentario] = useState("");
  const [fechaRealizacion, setFechaRealizacion] = useState("");
  const [idEmpleado, setIdEmpleado] = useState("");
  const [codProveedor, setCodProveedor] = useState("");
  const [codCausa, setCodCausa] = useState("");
  const [message, setMessage] = useState(""); // Mensaje de feedback

  const handleLogout = () => {
    // Tu lógica de logout
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validar que el ID del incidente sea válido
    if (!idIncidente) {
      setMessage("No se encontró un ID de incidente válido.");
      return;
    }

    const nuevoDiagnostico = {
      descripcion,
      estado,
      prioridad,
      comentario,
      fechaRealizacion,
      codIncidente: parseInt(idIncidente, 10), // Convertir el ID del incidente a número
      idEmpleado: parseInt(idEmpleado, 10),
      codProveedor: parseInt(codProveedor, 10),
      codCausa: parseInt(codCausa, 10),
    };

    try {
      const response = await fetch("http://localhost:8080/diagnosticos/crear", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(nuevoDiagnostico),
      });

      if (response.ok) {
        setMessage("Diagnóstico registrado con éxito.");
        setTimeout(() => navigate("/menuIncidente"), 2000); // Redirigir después de 2 segundos
      } else {
        const errorData = await response.json();
        setMessage(`Error: ${errorData.message || "No se pudo registrar el diagnóstico."}`);
      }
    } catch (error) {
      setMessage("Error de conexión con el servidor.");
    }
  };

  return (
    <div className="crear-diagnostico-container">
      {/* Barra lateral */}
      <aside className="sidebar">
        <div className="sidebar-content">
          <div className="logo-container">
            <img
              src="https://vectorseek.com/wp-content/uploads/2023/09/Yape-App-Logo-Vector.svg-.png"
              alt="Logo"
              className="logo"
              onClick={() => navigate("/menuIncidente")}
            />
          </div>
          <nav className="nav-menu">
            <button className="nav-button" onClick={() => navigate("/menuIncidente/registro")}>
              Registro
            </button>
            <button className="nav-button" onClick={() => navigate("/menuIncidente/Incidentes")}>
              Diagnostico
            </button>
            <button className="nav-button" onClick={() => navigate("/menuIncidente/PostMortem")}>
              Analisis-Postmortem
            </button>
          </nav>
        </div>

        <button className="logout-button" onClick={handleLogout}>
          Cerrar sesión
        </button>
      </aside>

      <h1>Crear Diagnóstico</h1>
      {message && <div className="alert">{message}</div>}
      <form onSubmit={handleSubmit} className="diagnostico-form">
        {/* Campos del formulario */}
        <div className="form-group">
          <label>Descripción</label>
          <textarea
            value={descripcion}
            onChange={(e) => setDescripcion(e.target.value)}
            required
          ></textarea>
        </div>

        <div className="form-group">
          <label>Estado</label>
          <select value={estado} onChange={(e) => setEstado(e.target.value)} required>
            <option value="">Seleccionar</option>
            <option value="Abierto">Abierto</option>
            <option value="En Progreso">En Progreso</option>
            <option value="Cerrado">Cerrado</option>
          </select>
        </div>

        <div className="form-group">
          <label>Prioridad</label>
          <select value={prioridad} onChange={(e) => setPrioridad(e.target.value)} required>
            <option value="">Seleccionar</option>
            <option value="Baja">Baja</option>
            <option value="Media">Media</option>
            <option value="Alta">Alta</option>
          </select>
        </div>

        <div className="form-group">
          <label>Comentario</label>
          <textarea
            value={comentario}
            onChange={(e) => setComentario(e.target.value)}
            required
          ></textarea>
        </div>

        <div className="form-group">
          <label>Fecha de Realización</label>
          <input
            type="date"
            value={fechaRealizacion}
            onChange={(e) => setFechaRealizacion(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label>ID del Empleado</label>
          <input
            type="number"
            value={idEmpleado}
            onChange={(e) => setIdEmpleado(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label>Código del Proveedor</label>
          <input
            type="number"
            value={codProveedor}
            onChange={(e) => setCodProveedor(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label>Código de la Causa</label>
          <input
            type="number"
            value={codCausa}
            onChange={(e) => setCodCausa(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="submit-btn">
          Registrar Diagnóstico
        </button>
      </form>
    </div>
  );
};

export default CrearDiagnostico;
