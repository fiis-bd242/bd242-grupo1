import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import Puestos from './components/Puestos';
import Vacantes from './components/Vacantes';
import GestionarEntrevistas from './components/GestionarEntrevistas';
import Incidente from './components/Incidente';
import MenuIncidente from './components/MenuIncidente';
import Diagnostico from './components/Diagnostico';
import ReporteIncidente from './components/ReporteIncidente';

function App() {
  return (
    <div className="app">
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/menuIncidente" element={<MenuIncidente />} />
        <Route path="/menu/puestos" element={<Puestos />} />
        <Route path="/menu/vacantes" element={<Vacantes />} />
        <Route path="/gestionar-entrevistas/:id" element={<GestionarEntrevistas />} />
        <Route path="/gestionar-entrevistas" element={<GestionarEntrevistas />} />
        {/* Modulo 6 */}
        <Route path="/menuIncidente/Registro" element={<Incidente />} />
        <Route path="/menuIncidente/Diagnostico" element={<Diagnostico />} />
        <Route path="/menuIncidente/Reporte" element={<ReporteIncidente />} />


        

      </Routes>



    </div>
  );
}

export default App;