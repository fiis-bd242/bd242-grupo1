import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/ObjetivosEmpleados.css';

const ObjetivosEmpleados = () => {
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
            <button className="nav-button" onClick={() => navigate('/menu/empleado/mis-objetivos')}>Mis objetivos</button>
            <button className="nav-button" onClick={() => navigate('/menu/empleado/mis-resultados')}>Resultados</button>
          </nav>
  
          <button className="logout-button" onClick={() => navigate('/')}>
            Cerrar sesión
          </button>
        </aside>
  
        {/* Contenido principal */}
        <main className="main-content">
          <header className="header">
            <h1 className="page-title">Evaluaciones</h1>
            <div className="time">{new Date().toLocaleTimeString()}</div>
          </header>
  
          <div className="cards-grid">
            {/* Opción Objetivos */}
            <div className="card" onClick={() => navigate('/menu/empleado/mis-objetivos')}>
              <div className="card-icon">
                <img 
                  src="https://cdn-icons-png.flaticon.com/512/847/2476337.png"
                  alt="Mis objetivos"
                  className="icon"
                />
              </div>
              <h3 className="card-title">Mis objetivos</h3>
              <p className="card-description">
                Gestiona tus objetivos a realizar
              </p>
            </div>
  
            {/* Opción Evaluacion */}
            <div className="card" onClick={() => navigate('/menu/empleado/mis-resultados')}>
              <div className="card-icon">
                <img
                  src="https://cdn-icons-png.flaticon.com/512/1250/1250924.png"
                  alt="Evaluaciones"
                  className="icon"
                />
              </div>
              <h3 className="card-title">Evaluaciones</h3>
              <p className="card-description">Visualiza tus resultados</p>
            </div>
          </div>
        </main>
      </div>
    );
  };
  
  export default GestionEmpleados;
  