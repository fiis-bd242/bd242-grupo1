import React, { useState, useEffect } from 'react';
import { Users, Clock } from 'lucide-react';
import '../styles/MenuPrincipal.css';
import { useNavigate } from 'react-router-dom';

const MenuPrincipal = () => {
  const navigate = useNavigate();
  const [time, setTime] = useState(new Date());

  useEffect(() => {
    const timer = setInterval(() => {
      setTime(new Date());
    }, 1000);

    return () => clearInterval(timer); // Limpia el intervalo al desmontar el componente
  }, []);

  const handleLogout = () => {
    // Aquí puedes agregar cualquier lógica adicional de cierre de sesión si es necesario
    navigate('/');
  };

  return (
    <div className="layout-container">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="sidebar-content">
          <div className="logo-container">
            <img src="https://vectorseek.com/wp-content/uploads/2023/09/Yape-App-Logo-Vector.svg-.png" alt="Logo" className="logo" />
          </div>
          
          <div className="user-profile">
            <div className="avatar"></div>
            <div className="welcome-text">¡Bienvenido/a!</div>
            <div className="user-name">PATERNO MATERNO,</div>
            <div className="user-name">NOMBRES</div>
          </div>

          <nav className="nav-menu">
            <button className="nav-button">Puestos</button>
            <button className="nav-button">Vacantes</button>
            <button className="nav-button">Actividad</button>
          </nav>
        </div>

        <button className="logout-button" onClick={handleLogout}>
          Cerrar sesión
        </button>
      </aside>

      {/* Main Content */}
      <main className="main-content">
        <header className="header">
          <h1 className="page-title">Menú principal</h1>
          <div className="time">{time.toLocaleTimeString()}</div>
        </header>

        <div className="subtitle-container">
          <h2 className="subtitle">¿Qué haremos hoy?</h2>
        </div>

        <div className="cards-grid">
          {/* Puestos Card */}
          <div className="card">
            <div className="card-icon">
              <Users className="icon" />
            </div>
            <h3 className="card-title">Puestos</h3>
            <p className="card-description">
              Visualiza, crea y edita los puestos
            </p>
          </div>

          {/* Vacantes Card */}
          <div className="card">
            <div className="card-icon">
              <div className="question-mark">?</div>
            </div>
            <h3 className="card-title">Vacantes</h3>
            <p className="card-description">
              Visualiza, crea y edita las vacantes
            </p>
          </div>

          {/* Actividad Card */}
          <div className="card">
            <div className="card-icon">
              <Clock className="icon" />
            </div>
            <h3 className="card-title">Actividad</h3>
            <p className="card-description">
              Visualiza la última actividad
            </p>
          </div>
        </div>
      </main>
    </div>
  );
};

export default MenuPrincipal;