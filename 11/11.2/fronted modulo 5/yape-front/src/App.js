import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import MenuPrincipal from './components/MenuPrincipal';
import MenuEmpleado from './components/MenuEmpleado';
import MenuAdmi from './components/MenuAdmi';
import GestionEmpleados from './components/GestionEmpleados';
import ListaEmpleados from './components/ListaEmpleados';
import ListaSolicitudes from './components/ListaSolicitudes';
import DetallePermiso from "./components/DetallePermiso";
import EmpleadoSolicitudes from "./components/EmpleadoSolicitudes";
import SolicitarPermiso from "./components/SolicitarPermiso";
import EstadoSolicitudes from "./components/EstadoSolicitudes";


function App() {
  return (
    <div className="app">
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/menu" element={<MenuPrincipal />} />
        <Route path="/menu/empleado" element={<MenuEmpleado />} />
        <Route path="/menu/admin" element={<MenuAdmi />} />
        <Route path="/menu/gestion-empleados" element={<GestionEmpleados />} />
        <Route path="/menu/gestion-empleados/lista" element={<ListaEmpleados />} />
        <Route path="/menu/gestion-empleados/solicitudes" element={<ListaSolicitudes />} />
        <Route path="/menu/gestion-empleados/solicitudes/detalle-permiso/:idPermiso" element={<DetallePermiso />} />
        <Route path="/menu/solicitudes" element={<EmpleadoSolicitudes />} /> 
        <Route path="/menu/empleado/solicitudes/nueva" element={<SolicitarPermiso />} />
        <Route path="/menu/empleado/solicitudes/estado" element={<EstadoSolicitudes />} />

      </Routes>
    </div>
  );
}

export default App;
