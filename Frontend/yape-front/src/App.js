import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import MenuPrincipal from './components/MenuPrincipal';
import Puestos from './components/Puestos';
import Vacantes from './components/Vacantes';
import GestionarEntrevistas from './components/GestionarEntrevistas';

function App() {
  return (
    <div className="app">
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/menu" element={<MenuPrincipal />} />
        <Route path="/menu/puestos" element={<Puestos />} />
        <Route path="/menu/vacantes" element={<Vacantes />} />
        <Route path="/gestionar-entrevistas/:id" element={<GestionarEntrevistas />} />
        <Route path="/gestionar-entrevistas" element={<GestionarEntrevistas />} />
      </Routes>
    </div>
  );
}

export default App;