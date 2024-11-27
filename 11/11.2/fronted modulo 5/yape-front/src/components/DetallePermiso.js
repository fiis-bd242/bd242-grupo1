import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "../styles/DetallePermiso.css";

const DetallePermiso = () => {
  const { idPermiso } = useParams();
  const [detalle, setDetalle] = useState(null);
  const [evidencias, setEvidencias] = useState([]);

  const cargarDetallePermiso = async () => {
    try {
      const response = await fetch(`http://localhost:8080/permisos/${idPermiso}/detalles`);
      if (!response.ok) {
        throw new Error("Error al obtener detalles del permiso");
      }
      const data = await response.json();
      setDetalle(data);
    } catch (error) {
      console.error("Error al cargar detalles del permiso:", error);
    }
  };

  const cargarEvidencias = async () => {
    try {
      const response = await fetch(`http://localhost:8080/permisos/${idPermiso}/evidencias`);
      if (!response.ok) {
        throw new Error("Error al obtener evidencias del permiso");
      }
      const data = await response.json();
      setEvidencias(data);
    } catch (error) {
      console.error("Error al cargar evidencias:", error);
    }
  };

  useEffect(() => {
    cargarDetallePermiso();
    cargarEvidencias();
  }, [idPermiso]);

  if (!detalle) {
    return <div>Cargando detalles...</div>;
  }

  return (
    <div className="detalle-permiso-container">
      <h1>Detalle del Permiso</h1>
      <ul>
        <li><strong>ID Permiso:</strong> {detalle.id_permiso}</li>
        <li><strong>Empleado:</strong> {detalle.empleado_nombre} {detalle.empleado_apellido}</li>
        <li><strong>Departamento:</strong> {detalle.departamento}</li>
        <li><strong>Puesto:</strong> {detalle.puesto}</li>
        <li><strong>Teléfono:</strong> {detalle.telefono}</li>
        <li><strong>Tipo de Permiso:</strong> {detalle.tipo_permiso}</li>
        <li><strong>Fecha Inicio:</strong> {detalle.fecha_inicio}</li>
        <li><strong>Fecha Final:</strong> {detalle.fecha_final}</li>
        <li><strong>Estado:</strong> {detalle.estado}</li>
        <li><strong>Comentario del Empleado:</strong> {detalle.comentario_empleado}</li>
        <li><strong>Comentario del Administrador:</strong> {detalle.comentario_administrador || "N/A"}</li>
      </ul>

      <h2>Evidencias</h2>
      {evidencias.length > 0 ? (
        <ul>
          {evidencias.map((evidencia, index) => (
            <li key={index}>
              <a 
                href={`http://localhost:8080/uploads/evidencias/${idPermiso}/${evidencia.nombre_archivo}`}
                target="_blank"
                rel="noopener noreferrer"
              >
                {evidencia.nombre_archivo}
              </a>
            </li>
          ))}
        </ul>
      ) : (
        <p>No hay evidencias disponibles para este permiso.</p>
      )}
    </div>
  );
};

export default DetallePermiso;
