
// src/components/Incidentes.js
import React, { useState, useEffect } from 'react';
import '../styles/Incidente.css';

const Incidentes = () => {
  // Estado para el formulario
  const [mensajeIncidente, setMensajeIncidente] = useState('');
  const [categoria, setCategoria] = useState('');
  const [codTicketInc, setCodTicketInc] = useState('');

  // Estado para la lista de incidentes
  const [incidentes, setIncidentes] = useState([]);
  const [message, setMessage] = useState('');

  // Función para obtener todos los incidentes
  const fetchIncidentes = async () => {
    try {
      const response = await fetch('http://localhost:8080/incidentes/obtener-incidentes');
      if (response.ok) {
        const data = await response.json();
        setIncidentes(data);
      } else {
        setMessage('Error al obtener los incidentes');
      }
    } catch (error) {
      setMessage('Error al obtener los incidentes');
    }
  };

  // Función para crear un incidente
  const handleSubmit = async (e) => {
    e.preventDefault();

    const nuevoIncidente = {
      mensajeIncidente,
      categoria,
      codTicketInc: parseInt(codTicketInc),  // Aseguramos que el código de ticket es un número
    };

    try {
      const response = await fetch('http://localhost:8080/incidentes/crear-incidente', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(nuevoIncidente),
      });

      const result = await response.text();

      if (response.ok) {
        setMessage(result);
        fetchIncidentes(); // Actualiza la lista de incidentes
        setMensajeIncidente('');
        setCategoria('');
        setCodTicketInc('');
      } else {
        setMessage('Error al registrar el incidente');
      }
    } catch (error) {
      setMessage('Error al registrar el incidente');
    }
  };

  // Llamar a la función fetchIncidentes cuando el componente se monta
  useEffect(() => {
    fetchIncidentes();
  }, []);

  return (
    <div className="incidentes-container">
      <h1>Gestión de Incidentes</h1>

      {/* Mostrar mensaje de éxito o error */}
      {message && <div className={`alert ${message.includes('con éxito') ? 'success' : 'error'}`}>{message}</div>}

      {/* Formulario para crear un nuevo incidente */}
      <form onSubmit={handleSubmit} className="incidente-form">
        <h2>Crear un Nuevo Incidente</h2>

        <div className="form-group">
          <label htmlFor="mensajeIncidente">Mensaje del Incidente</label>
          <input
            type="text"
            id="mensajeIncidente"
            value={mensajeIncidente}
            onChange={(e) => setMensajeIncidente(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="categoria">Categoría</label>
          <input
            type="text"
            id="categoria"
            value={categoria}
            onChange={(e) => setCategoria(e.target.value)}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="codTicketInc">Código de Ticket</label>
          <input
            type="number"
            id="codTicketInc"
            value={codTicketInc}
            onChange={(e) => setCodTicketInc(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="submit-btn">Registrar Incidente</button>
      </form>

      {/* Lista de incidentes */}
      <div className="incidente-list">
        <h2>Incidentes Registrados</h2>
        {incidentes.length > 0 ? (
          <ul>
            {incidentes.map((incidente, index) => (
              <li key={index} className="incidente-item">
                <p><strong>ID:</strong> {incidente.cod_incidente}</p>
                <p><strong>Mensaje:</strong> {incidente.mensaje_incidente}</p>
                <p><strong>Categoría:</strong> {incidente.categoria}</p>
                <p><strong>Código de Ticket:</strong> {incidente.cod_ticket_inc}</p>
                <button className="delete-btn">Eliminar</button>
              </li>
            ))}
          </ul>
        ) : (
          <p>No hay incidentes registrados.</p>
        )}
      </div>
    </div>
  );
};

export default Incidentes;
