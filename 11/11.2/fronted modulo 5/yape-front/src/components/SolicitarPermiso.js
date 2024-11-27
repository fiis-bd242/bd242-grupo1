import React, { useState } from "react";
import "../styles/SolicitarPermiso.css";

const SolicitarPermiso = () => {
  const [formData, setFormData] = useState({
    fechaInicio: "",
    fechaFinal: "",
    idTipoPermiso: "",
    comentario: "",
  });
  const [evidencias, setEvidencias] = useState([]);
  const [mensaje, setMensaje] = useState("");

  const tiposPermiso = [
    { id: 1, nombre: "Falta justificada" },
    { id: 2, nombre: "Vacaciones" },
    { id: 3, nombre: "Permiso" },
    { id: 4, nombre: "Tardanza justificada" },
  ];

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleFileChange = (e) => {
    setEvidencias(e.target.files);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const permisoData = {
      fechaInicio: formData.fechaInicio,
      fechaFinal: formData.fechaFinal,
      idTipoPermiso: formData.idTipoPermiso,
      comentario: formData.comentario,
      estado: "Pendiente", // Estado inicial
      idEmpleado: 15, // Cambia esto al ID del empleado autenticado
    };

    try {
      // Paso 1: Guardar el permiso
      const permisoResponse = await fetch("http://localhost:8080/permisos", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(permisoData),
      });

      if (!permisoResponse.ok) {
        throw new Error("Error al guardar el permiso.");
      }

      const permisoGuardado = await permisoResponse.json();

      // Paso 2: Subir las evidencias si hay archivos seleccionados
      if (evidencias.length > 0) {
        const evidenciaFormData = new FormData();
        for (const file of evidencias) {
          evidenciaFormData.append("evidencias", file);
        }

        const evidenciaResponse = await fetch(
          `http://localhost:8080/permisos/${permisoGuardado.idPermiso}/evidencias`,
          {
            method: "POST",
            body: evidenciaFormData,
          }
        );

        if (!evidenciaResponse.ok) {
          throw new Error("Error al subir las evidencias.");
        }
      }

      // Mostrar mensaje de éxito
      setMensaje("Permiso solicitado exitosamente.");
      setFormData({ fechaInicio: "", fechaFinal: "", idTipoPermiso: "", comentario: "" });
      setEvidencias([]);
    } catch (error) {
      console.error("Error al solicitar permiso:", error);
      setMensaje("Hubo un error al procesar la solicitud. Intenta nuevamente.");
    }
  };

  return (
    <div className="solicitar-permiso-container">
      <h1>Solicitar Permiso</h1>
      <form className="form-permiso" onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="fechaInicio">Fecha de Inicio:</label>
          <input
            type="date"
            id="fechaInicio"
            name="fechaInicio"
            value={formData.fechaInicio}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="fechaFinal">Fecha Final:</label>
          <input
            type="date"
            id="fechaFinal"
            name="fechaFinal"
            value={formData.fechaFinal}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="idTipoPermiso">Tipo de Permiso:</label>
          <select
            id="idTipoPermiso"
            name="idTipoPermiso"
            value={formData.idTipoPermiso}
            onChange={handleInputChange}
            required
          >
            <option value="">Selecciona un tipo</option>
            {tiposPermiso.map((tipo) => (
              <option key={tipo.id} value={tipo.id}>
                {tipo.nombre}
              </option>
            ))}
          </select>
        </div>
        <div className="form-group">
          <label htmlFor="comentario">Comentario:</label>
          <textarea
            id="comentario"
            name="comentario"
            value={formData.comentario}
            onChange={handleInputChange}
            placeholder="Agrega un comentario (opcional)"
          ></textarea>
        </div>
        <div className="form-group">
          <label htmlFor="evidencias">Evidencias (opcional):</label>
          <input
            type="file"
            id="evidencias"
            multiple
            onChange={handleFileChange}
          />
        </div>
        <button type="submit" className="btn-enviar">
          Enviar Solicitud
        </button>
      </form>
      {mensaje && <p className="mensaje">{mensaje}</p>}
    </div>
  );
};

export default SolicitarPermiso;
