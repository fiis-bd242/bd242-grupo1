import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/MenuPrincipal.css';

const MenuBusiness = () => {
    const navigate = useNavigate();
    const [time, setTime] = useState(new Date());

    useEffect(() => {
        const timer = setInterval(() => {
            setTime(new Date());
        }, 1000);
        return () => clearInterval(timer);
    }, []);

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
                        <div className="welcome-text">¡Bienvenido/a, Business!</div>
                    </div>
                    <nav className="nav-menu">
                        <button className="nav-button" onClick={() => navigate('/menu/lista-tipificaciones')}>Lista de Tipificaciones</button>
                        <button className="nav-button" onClick={() => navigate('/menu/historial-tipificaciones')}>Historial de Tipificaciones</button>
                        <button className="nav-button" onClick={() => navigate('/menu/business/reporte-business')}>Reporte Business</button>
                    </nav>
                </div>
                <button className="logout-button" onClick={() => navigate('/login')}>
                    Cerrar sesión
                </button>
            </aside>

            {/* Main Content */}
            <main className="main-content">
                <header className="header">
                    <h1 className="page-title">Menú Business</h1>
                    <div className="time">{time.toLocaleTimeString()}</div>
                </header>
            </main>
        </div>
    );
};

export default MenuBusiness;
