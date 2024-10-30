import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Puestos.css';
import '../styles/MenuPrincipal.css';

const Vacantes = () => {
  const navigate = useNavigate();
  const [time, setTime] = useState(new Date());

  useEffect(() => {
    const timer = setInterval(() => {
      setTime(new Date());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  const handleLogout = () => {
    navigate('/');
  };

  return (
    <div className="layout-container">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="sidebar-content">
          <div className="logo-container">
            <img src="https://vectorseek.com/wp-content/uploads/2023/09/Yape-App-Logo-Vector.svg-.png" alt="Logo" className="logo" onClick={() => navigate('/menu')} />
          </div>
          
          <div className="user-profile">
            <div className="avatar"></div>
            <div className="welcome-text">¡Bienvenido/a!</div>
            <div className="user-name">PATERNO MATERNO,</div>
            <div className="user-name">NOMBRES</div>
          </div>

          <nav className="nav-menu">
            <button className="nav-button" onClick={() => navigate('/menu/puestos')}>Puestos</button>
            <button className="nav-button" onClick={() => navigate('/menu/vacantes')}>Vacantes</button>
</nav>
        </div>

        <button className="logout-button" onClick={handleLogout}>
          Cerrar sesión
        </button>
      </aside>

      {/* Main Content */}
      <main className="main-content">
        <header className="header">
          <h1 className="page-title">Vacantes</h1>
          <div className="time">{time.toLocaleTimeString()}</div>
        </header>

        {/* Contenido principal de Puestos */}
      </main>
    </div>
  );
};

export default Vacantes;