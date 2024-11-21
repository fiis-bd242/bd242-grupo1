import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import MenuPrincipal from './components/MenuPrincipal';
import Puestos from './components/Puestos';
import Vacantes from './components/Vacantes';
import GestionarEntrevistas from './components/GestionarEntrevistas';
import Sugerencias from './components/Sugerencias';
import AsignarTickets from './components/AsignarTickets';
import BuscarEtiquetas from './components/BuscarEtiquetas';
import HistorialConversaciones from './components/HistorialConversaciones';
import MenuAnalista from './components/MenuAnalista';
import MenuBusiness from './components/MenuBusiness';
import MenuAsesor from './components/MenuAsesor';
import MensajesConversacion from './components/MensajeConversacion';


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

        {/* Modulo 4 */}
        <Route path="/menu" element={<MenuPrincipal />} />
        <Route path="/menu/analista" element={<MenuAnalista />} />
        <Route path="/menu/business" element={<MenuBusiness />} />
        <Route path="/menu/asesor" element={<MenuAsesor />} />
        <Route path="/menu/analista/sugerencias" element={<Sugerencias />} />
        <Route path="/menu/asesor/conversaciones-historial" element={<HistorialConversaciones />} />
        <Route path="/menu/asesor/conversaciones/:idEmpleado/:idConv" element={<MensajesConversacion />} />
        <Route path="/menu/asesor/etiquetas/buscar" element={<BuscarEtiquetas />} />
        <Route path="/menu/asesor/tickets/asignar" element={<AsignarTickets />} />
      </Routes>
    </div>
  );
}

export default App;