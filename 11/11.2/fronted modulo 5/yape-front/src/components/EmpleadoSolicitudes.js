import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/EmpleadoSolicitudes.css';

const EmpleadoSolicitudes = () => {
  const navigate = useNavigate();

  return (
    <div className="layout-container">
      {/* Barra lateral */}
      <aside className="sidebar">
        <div className="logo-container">
          <img
            src="https://vectorseek.com/wp-content/uploads/2023/09/Yape-App-Logo-Vector.svg-.png"
            alt="Logo Yape"
            className="logo"
          />
        </div>

        <div className="user-profile">
          <div className="avatar"></div>
          <div className="welcome-text">¡Bienvenido/a!</div>
          <div className="user-name">White, Thomas</div>
        </div>

        <nav className="nav-menu">
          <button className="nav-button" onClick={() => navigate('/menu/empleado')}>Inicio</button>
          <button className="nav-button" onClick={() => navigate('/menu/empleado/solicitudes')}>Solicitudes</button>
          <button className="nav-button" onClick={() => navigate('/menu/empleado/asistencia')}>Asistencia</button>
        </nav>

        <button className="logout-button" onClick={() => navigate('/')}>
          Cerrar sesión
        </button>
      </aside>

      {/* Contenido principal */}
      <main className="main-content">
        <header className="header">
          <h1 className="page-title">Solicitudes</h1>
          <div className="time">{new Date().toLocaleTimeString()}</div>
        </header>

        <div className="subtitle-container">
          <h2 className="subtitle">¿Qué haremos hoy?</h2>
        </div>

        <div className="cards-grid">
          {/* Solicitar Permiso */}
          <div className="card" onClick={() => navigate('/menu/empleado/solicitudes/nueva')}>
            <div className="card-icon">
              <img
                src="https://cdn-icons-png.flaticon.com/512/892/892639.png"
                alt="Solicitar Permiso"
                className="icon"
              />
            </div>
            <h3 className="card-title">Solicitar Permiso</h3>
            <p className="card-description">Solicita permisos o licencias fácilmente</p>
          </div>

          {/* Estado de Solicitudes */}
          <div className="card" onClick={() => navigate('/menu/empleado/solicitudes/estado')}>
            <div className="card-icon">
              <img
                src="https://cdn-icons-png.flaticon.com/512/1250/1250924.png"
                alt="Estado de Solicitudes"
                className="icon"
              />
            </div>
            <h3 className="card-title">Estado de Solicitudes</h3>
            <p className="card-description">Revisa el estado de tus solicitudes</p>
          </div>
        </div>
      </main>
    </div>
  );
};

export default EmpleadoSolicitudes;
