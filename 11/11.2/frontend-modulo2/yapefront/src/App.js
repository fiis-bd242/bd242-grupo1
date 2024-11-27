import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Sidebar from "./components/Sidebar";
import CampaignDashboard from "./components/CampaignDashboard";
import CrearPrototipo from "./components/CrearPrototipo";
import IntroducirRecursos from "./components/IntroducirRecursos";
import CampanaDetalles from "./components/CampanaDetalles"; 
import AdminPublicidad from "./components/AdminPublicidad";
import AdminPreTest from "./components/AdminPreTest";
import NuevaAudiencia from "./components/NuevaAudiencia";
import AdminSolicitudes from "./components/AdminSolicitudes";
import VerArchivos from "./components/VerArchivos";
import SolicitudesAprobadas from "./components/SolicitudesAprobadas";
import VerCampana from "./components/VerCampana";
import Login1 from "./components/Login1";
import Login2 from "./components/Login2";
import Login3 from "./components/Login3";
import Reportes from "./components/Reportes";
import PrototiposEmpleado from "./components/PrototiposEmpleado.js";
import "./App.css";

function App() {
  return (
    <Router>
      <div className="app-container">
        <Sidebar />
        <Routes>
          <Route path="/" element={<CampaignDashboard />} />
          <Route path="/create-publicidad" element={<CrearPrototipo />} />
          <Route path="/introducir-recursos" element={<IntroducirRecursos />} />
          <Route path="/campana-detalles" element={<CampanaDetalles />} /> {/* Nueva ruta */}
          <Route path="/admin-pretest" element={<AdminPreTest />} />
          <Route path="/admin-publicidad" element={<AdminPublicidad />} />
          <Route path="/nueva-audiencia" element={<NuevaAudiencia />} />
          <Route path="/admin-solicitudes" element={<AdminSolicitudes />} />
          <Route path="/ver-archivos" element={<VerArchivos />} />
          <Route path="/solicitudes-aprobadas" element={<SolicitudesAprobadas />} />
          <Route path="/ver-campana" element={<VerCampana />} />
          <Route path="/login1" element={<Login1 />} />
          <Route path="/login2" element={<Login2 />} />
          <Route path="/login3" element={<Login3 />} />
          <Route path="/reporte/:codCampana" element={<Reportes />} />
          <Route path="/prototipos/empleado/:idEmpleado" element={<PrototiposEmpleado />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
