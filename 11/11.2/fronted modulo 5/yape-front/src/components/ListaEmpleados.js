import React, { useEffect, useState } from "react";
import "../styles/ListaEmpleados.css";

const ListaEmpleados = () => {
  const [empleados, setEmpleados] = useState([]);
  const [filtros, setFiltros] = useState({
    idEmpleado: "",
    nombre: "",
    apellido: "",
    puesto: "",
    departamento: "",
    estado: "",
  });

  // Función para cargar los empleados desde el backend
  const cargarEmpleados = async () => {
    try {
      const queryParams = new URLSearchParams(filtros).toString(); // Convierte filtros en query params
      const response = await fetch(`http://localhost:8080/empleados?${queryParams}`);
      if (!response.ok) {
        throw new Error("Error al obtener empleados");
      }
      const data = await response.json();
      setEmpleados(data);
    } catch (error) {
      console.error("Error al cargar empleados:", error);
    }
  };

  useEffect(() => {
    cargarEmpleados(); // Cargar la lista al inicio
  }, []);

  // Función para manejar cambios en los filtros
  const manejarCambio = (e) => {
    setFiltros({
      ...filtros,
      [e.target.name]: e.target.value,
    });
  };

  // Función para aplicar filtros
  const aplicarFiltros = (e) => {
    e.preventDefault();
    cargarEmpleados(); // Recargar empleados con los filtros aplicados
  };

  return (
    <div className="lista-empleados-container">
      <h1>Lista de Empleados</h1>
      {/* Filtros */}
      <form className="filtros-form" onSubmit={aplicarFiltros}>
        <input
          type="text"
          name="idEmpleado"
          placeholder="ID Empleado"
          value={filtros.idEmpleado}
          onChange={manejarCambio}
        />
        <input
          type="text"
          name="nombre"
          placeholder="Nombre"
          value={filtros.nombre}
          onChange={manejarCambio}
        />
        <input
          type="text"
          name="apellido"
          placeholder="Apellido"
          value={filtros.apellido}
          onChange={manejarCambio}
        />
        <input
          type="text"
          name="puesto"
          placeholder="Puesto"
          value={filtros.puesto}
          onChange={manejarCambio}
        />
        <input
          type="text"
          name="departamento"
          placeholder="Departamento"
          value={filtros.departamento}
          onChange={manejarCambio}
        />
        <select name="estado" value={filtros.estado} onChange={manejarCambio}>
          <option value="">Todos los estados</option>
          <option value="activo">Activo</option>
          <option value="inactivo">Inactivo</option>
        </select>
        <button type="submit">Buscar</button>
      </form>

      {/* Contenedor para la tabla */}
      <div className="tabla-container">
        <table className="tabla-empleados">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Puesto</th>
              <th>Departamento</th>
              <th>Estado</th>
            </tr>
          </thead>
          <tbody>
            {empleados.length > 0 ? (
              empleados.map((empleado) => (
                <tr key={empleado.id_empleado}>
                  <td>{empleado.id_empleado}</td>
                  <td>{empleado.nombre}</td>
                  <td>{empleado.apellido}</td>
                  <td>{empleado.puesto}</td>
                  <td>{empleado.departamento}</td>
                  <td>{empleado.estado}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="6">No se encontraron empleados</td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ListaEmpleados;
