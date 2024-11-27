import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/GestionEmpleados.css';

const GestionEmpleados = () => {
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
          <div className="user-name">PATERNO MATERNO,</div>
          <div className="user-name">NOMBRES</div>
        </div>

        <nav className="nav-menu">
          <button className="nav-button" onClick={() => navigate('/menu/gestion-empleados')}>Gestión de Empleados</button>
          <button className="nav-button" onClick={() => navigate('/menu/evaluacion')}>Evaluación</button>
          <button className="nav-button" onClick={() => navigate('/menu/reportes')}>Reportes</button>
        </nav>

        <button className="logout-button" onClick={() => navigate('/')}>
          Cerrar sesión
        </button>
      </aside>

      {/* Contenido principal */}
      <main className="main-content">
        <header className="header">
          <h1 className="page-title">Gestión de Empleados</h1>
          <div className="time">{new Date().toLocaleTimeString()}</div>
        </header>

        <div className="subtitle-container">
          <h2 className="subtitle">¿Qué haremos hoy?</h2>
        </div>

        <div className="cards-grid">
          {/* Opción Lista de Empleados */}
          <div className="card" onClick={() => navigate('/menu/gestion-empleados/lista')}>
            <div className="card-icon">
              <img
                src="https://cdn-icons-png.flaticon.com/512/847/847969.png"
                alt="Lista de empleados"
                className="icon"
              />
            </div>
            <h3 className="card-title">Lista de Empleados</h3>
            <p className="card-description">
              Accede a información del empleado, administra y actualiza
            </p>
          </div>

          {/* Opción Solicitudes */}
          <div className="card" onClick={() => navigate('/menu/gestion-empleados/solicitudes')}>
            <div className="card-icon">
              <img
                src="https://cdn-icons-png.flaticon.com/512/1250/1250924.png"
                alt="Solicitudes"
                className="icon"
              />
            </div>
            <h3 className="card-title">Solicitudes</h3>
            <p className="card-description">Visualiza y acepta solicitudes</p>
          </div>
        </div>
      </main>
    </div>
  );
};

export default GestionEmpleados;
