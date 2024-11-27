import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/MenuAdmi.css';

const MenuAdmi = () => {
  const navigate = useNavigate();
  const [time, setTime] = useState(new Date());
  const [admin, setAdmin] = useState({ nombre: 'NOMBRES', apellido: 'PATERNO MATERNO' });

  useEffect(() => {
    const fetchAdmin = async () => {
      try {
        const response = await fetch('http://localhost:8080/empleados/13'); // Cambia el endpoint según tu backend
        if (!response.ok) {
          throw new Error('Error en la respuesta de la red');
        }
        const data = await response.json();
        setAdmin(data);
      } catch (error) {
        console.error('Error al obtener el administrador:', error);
      }
    };

    fetchAdmin();

    const timer = setInterval(() => {
      setTime(new Date());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  return (
    <div className="layout-container">
      {/* Sidebar */}
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
          <div className="user-name">{`${admin.apellido},`}</div>
          <div className="user-name">{admin.nombre}</div>
        </div>

        <nav className="nav-menu">
          <button className="nav-button" onClick={() => navigate('/menu/gestion-empleados')}>
            Gestión de Empleados
          </button>
          <button className="nav-button" onClick={() => navigate('/menu/evaluacion')}>
            Evaluación
          </button>
        </nav>

        <button className="logout-button" onClick={() => navigate('/')}>
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
          {/* Tarjeta Gestión de Empleados */}
          <div className="card" onClick={() => navigate('/menu/gestion-empleados')}>
            <div className="card-icon">👥</div>
            <h3 className="card-title">Gestión de Empleados</h3>
            <p className="card-description">
              Administra, actualiza y visualiza información del personal
            </p>
          </div>

          {/* Tarjeta Evaluación */}
          <div className="card" onClick={() => navigate('/menu/evaluacion')}>
            <div className="card-icon">📊</div>
            <h3 className="card-title">Evaluación</h3>
            <p className="card-description">Realiza y gestiona evaluaciones de desempeño</p>
          </div>

        </div>
      </main>
    </div>
  );
};

export default MenuAdmi;
