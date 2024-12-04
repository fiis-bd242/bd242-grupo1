import React, { useState, useEffect } from 'react';
import '../styles/MenuPrincipal.css';
import { Users } from 'lucide-react';
import { useNavigate } from 'react-router-dom';
import '../service/IncidenteService.js';

const MenuIncidente = () => {
  const navigate = useNavigate();
  const [time, setTime] = useState(new Date());
  const [employee, setEmployee] = useState({ nombre: '', apellido: '' });

  useEffect(() => {
    const fetchEmployee = async () => {
      try {
        const response = await fetch('http://localhost:8080/empleados/1');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log('Employee data fetched:', data); // Añadido para verificar datos
        setEmployee(data);
      } catch (error) {
        console.error('Error fetching employee:', error);
      }
    };

    fetchEmployee();

    const timer = setInterval(() => {
      setTime(new Date());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  const handleLogout = () => {
    // Tu lógica de logout
  };

  return (
    <div className="layout-container">
      {/* Sidebar */}
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
            <div className="user-name">{`${employee.apellido},`}</div>
            <div className="user-name">{employee.nombre}</div>
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
          {/* Registro Card */}
          <div className="card" onClick={() => navigate('/menuIncidente/registro')}>
            <div className="card-icon">
              <Users className="icon" />
            </div>
            <h3 className="card-title">Registro</h3>
            <p className="card-description">
              Registra los incidentes
            </p>
          </div>

          {/* Diagnostico Card */}
          <div className="card" onClick={() => navigate('/menuIncidente/Incidentes')}>
            <div className="card-icon">
              <div className="question-mark">?</div>
            </div>
            <h3 className="card-title">Diagnostico</h3>
            <p className="card-description">
              Realiza el diagnostico
            </p>
          </div>

          {/* Reporte Card */}
          <div className="card" onClick={() => navigate('/menuIncidente/PostMortem')}>
            <div className="card-icon">
              <div className="question-mark">?</div>
            </div>
            <h3 className="card-title">Analisis-Postmortem</h3>
            <p className="card-description">
              Realiza el analisis Postmortem
            </p>
          </div>
        </div>

        {/* Botón para Generar Reportes */}
        <div className="generar-reportes-container">
          <button
            className="generar-reportes-button"
            onClick={() => navigate('/menuIncidente/GenerarReportes')}
          >
            Generar Reportes
          </button>
        </div>
      </main>
    </div>
  );
};

export default MenuIncidente;

