import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import "../styles/PostmortemDetalles.css";

const PostmortemDetalles = () => {
  const [detalles, setDetalles] = useState([]);
  const [nuevoDetalle, setNuevoDetalle] = useState({
    resumenPost: "",
    impacto: "",
    deteccion: "",
    recuperacion: "",
    severidad: "",
    codTicketPost: "",
  });
  const [mensaje, setMensaje] = useState("");

  useEffect(() => {
    obtenerDetalles();
  }, []);

  const navigate = useNavigate();
  const handleLogout = () => {
    // Tu lógica de logout
  };
  const obtenerDetalles = async () => {
    try {
      const response = await fetch("http://localhost:8080/postmortemDetalles/obtener-postmortem");
      const data = await response.json();
      setDetalles(data);
    } catch (error) {
      console.error("Error al obtener los detalles:", error);
    }
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNuevoDetalle({ ...nuevoDetalle, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:8080/postmortemDetalles/crear-postmortem", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(nuevoDetalle),
      });

      const mensajeRespuesta = await response.text();
      setMensaje(mensajeRespuesta);

      if (response.ok) {
        setNuevoDetalle({
          resumenPost: "",
          impacto: "",
          deteccion: "",
          recuperacion: "",
          severidad: "",
          codTicketPost: "",
        });
        obtenerDetalles();
      }
    } catch (error) {
      console.error("Error al registrar el detalle:", error);
      setMensaje("Error al registrar el detalle.");
    }
  };

  return (
    <div className="postmortem-container">
      {/* Barra lateral */}
      <aside className="sidebar">
        <div className="sidebar-content">
          <div className="logo-container">
            <img
              src="https://vectorseek.com/wp-content/uploads/2023/09/Yape-App-Logo-Vector.svg-.png"
              alt="Logo"
              className="logo"
              onClick={() => navigate('/menuIncidente')}
            />
          </div>
          
          
          <nav className="nav-menu">
            <button className="nav-button" onClick={() => navigate('/menuIncidente/registro')}>Registro</button>
            <button className="nav-button" onClick={() => navigate('/menuIncidente/Incidentes')}>Diagnostico</button>
            <button className="nav-button" onClick={() => navigate('/menuIncidente/PostMortem')}>Analisis-Postmortem</button>
          </nav>
        </div>

        <button className="logout-button" onClick={handleLogout}>
          Cerrar sesión
        </button>
      </aside>
      <h1>Detalles de Postmortem</h1>

      {/* Formulario para crear nuevos detalles */}
      <form className="postmortem-form" onSubmit={handleSubmit}>
        <h2>Agregar Nuevo Analisis Postmortem</h2>
        {mensaje && <div className="mensaje">{mensaje}</div>}
        <div className="form-group">
          <label>Resumen:</label>
          <textarea
            name="resumenPost"
            value={nuevoDetalle.resumenPost}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Impacto:</label>
          <input
            type="text"
            name="impacto"
            value={nuevoDetalle.impacto}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Detección:</label>
          <input
            type="text"
            name="deteccion"
            value={nuevoDetalle.deteccion}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Recuperación:</label>
          <input
            type="text"
            name="recuperacion"
            value={nuevoDetalle.recuperacion}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label>Severidad:</label>
          <select
            name="severidad"
            value={nuevoDetalle.severidad}
            onChange={handleInputChange}
            required
          >
            <option value="">Seleccionar</option>
            <option value="Baja">Baja</option>
            <option value="Media">Media</option>
            <option value="Alta">Alta</option>
          </select>
        </div>
        <div className="form-group">
          <label>Código de Ticket:</label>
          <input
            type="number"
            name="codTicketPost"
            value={nuevoDetalle.codTicketPost}
            onChange={handleInputChange}
            required
          />
        </div>
        <button type="submit" className="submit-btn">Agregar Detalle</button>
      </form>

      {/* Tabla para mostrar detalles */}
      <h2>Lista de Analisis Postmortems</h2>
      <table className="postmortem-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Resumen</th>
            <th>Impacto</th>
            <th>Detección</th>
            <th>Recuperación</th>
            <th>Severidad</th>
            <th>Código Ticket</th>
          </tr>
        </thead>
        <tbody>
          {detalles.map((detalle) => (
            <tr key={detalle.cod_postmortem}>
              <td>{detalle.cod_postmortem}</td>
              <td>{detalle.resumen_post}</td>
              <td>{detalle.impacto}</td>
              <td>{detalle.deteccion}</td>
              <td>{detalle.recuperacion}</td>
              <td>{detalle.severidad}</td>
              <td>{detalle.cod_ticket_post}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PostmortemDetalles;
