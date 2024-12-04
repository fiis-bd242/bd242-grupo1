import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import '../styles/diagnostico.css';

const Diagnostico = () => {
  const [comentario, setComentario] = useState('');
  const [fechaRealizacion, setFechaRealizacion] = useState('');
  const [codIncidente, setCodIncidente] = useState('');
  const [idEmpleado, setIdEmpleado] = useState('');
  const [codProveedor, setCodProveedor] = useState('');
  const [codCausa, setCodCausa] = useState('');
  const [message, setMessage] = useState('');

  const navigate = useNavigate();
  const handleLogout = () => {
    // Tu lógica de logout
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const nuevoDiagnostico = {
      comentario,
      fechaRealizacion,
      codIncidente: parseInt(codIncidente, 10),
      idEmpleado: parseInt(idEmpleado, 10),
      codProveedor: parseInt(codProveedor, 10),
      codCausa: parseInt(codCausa, 10),
    };

    try {
      const response = await fetch('http://localhost:8080/diagnosticos/crear', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(nuevoDiagnostico),
      });

      if (response.ok) {
        const result = await response.text();
        setMessage(result);
        // Limpiar formulario
        setComentario('');
        setFechaRealizacion('');
        setCodIncidente('');
        setIdEmpleado('');
        setCodProveedor('');
        setCodCausa('');
      } else {
        setMessage('Error al registrar el diagnóstico');
      }
    } catch (error) {
      console.error('Error al enviar los datos:', error);
      setMessage('Ocurrió un error al conectar con el servidor');
    }
  };

  return (
    <div className="diagnostico-container">
      <aside className="sidebar">
        <div className="sidebar-content">
          <div className="logo-container">
            <img
              src="https://vectorseek.com/wp-content/uploads/2023/09/Yape-App-Logo-Vector.svg-.png"
              alt="Logo"
              className="logo"
              onClick={() => navigate('/menu')}
            />
          </div>
          
          <div className="user-profile">
            <div className="avatar"></div>
            <div className="welcome-text">¡Bienvenido/a!</div>
            
          </div>

          <nav className="nav-menu">
            <button className="nav-button" onClick={() => navigate('/menuIncidente/registro')}>Registro</button>
            <button className="nav-button" onClick={() => navigate('/menuIncidente/diagnostico')}>Diagnostico</button>
            <button className="nav-button" onClick={() => navigate('/menuIncidente/Analisis-Postmortem')}>Analisis-Postmortem</button>
          </nav>
        </div>

        <button className="logout-button" onClick={handleLogout}>
          Cerrar sesión
        </button>
      </aside>

      <h1>Registrar Diagnóstico</h1>
      {message && <div className={`alert ${message.includes('éxito') ? 'success' : 'error'}`}>{message}</div>}
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="comentario">Comentario</label>
          <input
            type="text"
            id="comentario"
            value={comentario}
            onChange={(e) => setComentario(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="fechaRealizacion">Fecha de Realización</label>
          <input
            type="date"
            id="fechaRealizacion"
            value={fechaRealizacion}
            onChange={(e) => setFechaRealizacion(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="codIncidente">Código del Incidente</label>
          <input
            type="number"
            id="codIncidente"
            value={codIncidente}
            onChange={(e) => setCodIncidente(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="idEmpleado">ID del Empleado</label>
          <input
            type="number"
            id="idEmpleado"
            value={idEmpleado}
            onChange={(e) => setIdEmpleado(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="codProveedor">Código del Proveedor</label>
          <input
            type="number"
            id="codProveedor"
            value={codProveedor}
            onChange={(e) => setCodProveedor(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="codCausa">Código de la Causa</label>
          <input
            type="number"
            id="codCausa"
            value={codCausa}
            onChange={(e) => setCodCausa(e.target.value)}
            required
          />
        </div>
        <button type="submit">Registrar Diagnóstico</button>
      </form>
    </div>
  );
};

export default Diagnostico;
