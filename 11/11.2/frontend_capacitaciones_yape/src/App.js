import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login';
import MenuPrincipal from './components/MenuPrincipal';
import MisCapacitaciones from './components/MisCapacitaciones';
import CapacitacionDetalle from './components/CapacitacionDetalle';
import TestDetalle from './components/TestDetalle';
import Notas from './components/Notas'; // Nuevo componente
import ProbarTest from './components/ProbarTest'; // Nuevo componente

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/menu" element={<MenuPrincipal />} />
          <Route path="/mis-capacitaciones" element={<MisCapacitaciones />} />
          <Route path="/mis-capacitaciones/:slug" element={<CapacitacionDetalle />} />
          <Route path="/mis-capacitaciones/:slug/test/:id_test" element={<TestDetalle />} />
          <Route path="/mis-capacitaciones/:slug/test/:id_test/notas" element={<Notas />} />
          <Route path="/mis-capacitaciones/:slug/test/:id_test/prueba" element={<ProbarTest />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;