import React, { useState, useEffect } from 'react';
import '../styles/MenuEmpleado.css';
import { useNavigate } from 'react-router-dom';

const MenuEmpleado = () => {
  const navigate = useNavigate();
  const [time, setTime] = useState(new Date());
  const [employee, setEmployee] = useState({ nombre: '', apellido: '' });

  useEffect(() => {
    // Simula la obtención de datos del empleado desde el backend
    const fetchEmployee = async () => {
      try {
        const response = await fetch('http://localhost:8080/empleados/15'); // Ajusta el endpoint según tu backend
        if (!response.ok) {
          throw new Error('Error en la respuesta de la red');
        }
        const data = await response.json();
        console.log('Datos del empleado:', data); // Para verificar los datos en la consola
        setEmployee(data);
      } catch (error) {
        console.error('Error al obtener el empleado:', error);
      }
    };

    fetchEmployee();

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
          <div className="user-name">{`${employee.apellido},`}</div>
          <div className="user-name">{employee.nombre}</div>
        </div>

        <nav className="nav-menu">
          <button className="nav-button" onClick={() => navigate('/menu/asistencia')}>Asistencia</button>
          <button className="nav-button" onClick={() => navigate('/menu/evaluacion')}>Evaluación</button>
          <button className="nav-button" onClick={() => navigate('/menu/solicitudes')}>Mis solicitudes</button>
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
          {/* Tarjetas */}
          <div className="card" onClick={() => navigate('/menu/asistencia')}>
            <div className="card-icon">📋</div>
            <h3 className="card-title">Asistencia</h3>
            <p className="card-description">Registrar</p>
          </div>
          <div className="card" onClick={() => navigate('/menu/evaluacion')}>
            <div className="card-icon">📊</div>
            <h3 className="card-title">Evaluación</h3>
            <p className="card-description">Visualizar evaluación y feedback</p>
          </div>
          <div className="card" onClick={() => navigate('/menu/solicitudes')}>
            <div className="card-icon">📝</div>
            <h3 className="card-title">Solicitudes</h3>
            <p className="card-description">
              Visualiza el estado en que se encuentran tus solicitudes de permiso
            </p>
          </div>
        </div>
      </main>
    </div>
  );
};

export default MenuEmpleado;

