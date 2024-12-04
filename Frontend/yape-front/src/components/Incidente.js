import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Incidente.css';



const Incidentes = () => {

  const [mensajeIncidente, setMensajeIncidente] = useState('');
  const [categoria, setCategoria] = useState('');
  const [codTicketInc, setCodTicketInc] = useState('');
  const [incidentes, setIncidentes] = useState([]);
  const [message, setMessage] = useState('');

  const navigate = useNavigate();
  const handleLogout = () => {
    // Tu lógica de logout
  };
  // Obtener todos los incidentes
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

  // Crear un nuevo incidente
  const handleSubmit = async (e) => {
    e.preventDefault();
    const nuevoIncidente = {
      mensajeIncidente,
      categoria,
      codTicketInc: parseInt(codTicketInc),
    };

    try {
      const response = await fetch('http://localhost:8080/incidentes/crear-incidente', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(nuevoIncidente),
      });

      if (response.ok) {
        setMessage('Incidente registrado con éxito');
        fetchIncidentes();
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

  // Eliminar un incidente
  const handleDelete = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/incidentes/eliminar-incidente/${id}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        setMessage('Incidente eliminado con éxito');
        fetchIncidentes();
      } else {
        setMessage('Error al eliminar el incidente');
      }
    } catch (error) {
      setMessage('Error al eliminar el incidente');
    }
  };

  // Obtener incidentes al montar el componente
  useEffect(() => {
    fetchIncidentes();
  }, []);

  return (
    <div className="incidentes-container">
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
            <button className="nav-button" onClick={() => navigate('/menuIncidente/diagnostico')}>Diagnostico</button>
            <button className="nav-button" onClick={() => navigate('/menuIncidente/PostMortem')}>Analisis-Postmortem</button>
          </nav>
        </div>

        <button className="logout-button" onClick={handleLogout}>
          Cerrar sesión
        </button>
      </aside>
      {/* Contenido principal */}
      <div className="main-content">
        <h1>Gestión de Incidentes</h1>

        {/* Mensaje de alerta */}
        {message && <div className={`alert ${message.includes('éxito') ? 'success' : 'error'}`}>{message}</div>}

        {/* Formulario para crear un incidente */}
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
              {incidentes.map((incidente) => (
                <li key={incidente.cod_incidente} className="list-item">
                  <p><strong>ID:</strong> {incidente.cod_incidente}</p>
                  <p><strong>Mensaje:</strong> {incidente.mensaje_incidente}</p>
                  <p><strong>Categoría:</strong> {incidente.categoria}</p>
                  <button
                    className="delete-btn"
                    onClick={() => handleDelete(incidente.cod_incidente)}
                  >
                    Eliminar
                  </button>
                </li>
              ))}
            </ul>
          ) : (
            <p>No hay incidentes registrados.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default Incidentes;
