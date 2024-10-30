import React, { useState, useEffect } from 'react';
import '../styles/MenuPrincipal.css';
import { Users } from 'lucide-react';
import { useNavigate } from 'react-router-dom';


const MenuPrincipal = () => {
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
        </div>
      </main>
    </div>
  );
};

export default MenuPrincipal;