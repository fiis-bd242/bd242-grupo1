import React from 'react';
import { Link, useLocation } from "react-router-dom";
import "./Sidebar.css"

function Sidebar() {
    const imagen="https://www.chullitohost.com/assets/img/pagos/pago-min-01.png";
    const location = useLocation();
    const isActive = (path) => {
        return location.pathname === path ? 'active' : ''; // Compara la URL actual con la ruta
      };
      
  return (
    <aside className="sidebar">
        <div>
            <img src={imagen} width="250" height="100"/>
        </div>
      <h3>Gestión de Promociones</h3>
      <ul>
        <li>
          <Link to="/"className={isActive("/")}>Promociones existentes</Link>
        </li>
        <li>
          <Link to="/crear-promocion" className={isActive("/crear-promocion")}>Crear Promoción</Link>
        </li>
        <li>
          <Link to="/promociones-activas" className={isActive("/promociones-activas")}>Promociones Activas</Link>
        </li>
        <li>
          <Link to="/productos-por-promocion" className={isActive("/productos-por-promocion")}>Asignar Productos</Link>
        </li>
      </ul>
    </aside>
  );
}

export default Sidebar;
