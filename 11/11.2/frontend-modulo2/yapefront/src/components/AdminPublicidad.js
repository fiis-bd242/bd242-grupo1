import React from "react";
import { useNavigate } from "react-router-dom";
import "./AdminPublicidad.css";

function AdminPublicidad() {
  const navigate = useNavigate();

  return (
    <div className="admin-publicidad">
      <main33>
        <header>
          <h1>Administrar publicidad</h1>
          <p>10:50 a.m.</p>
        </header>
        <section className="options">
            <div className="card" onClick={() => navigate("/solicitudes-aprobadas")}>
            <img src="Soliaprob.png" alt="Ver aprobadas" />
            <p>Ver solicitudes aprobadas</p>
            <button>Ver</button>
            </div>
          <div className="card">
            <img src="Administrar.png" alt="Administrar solicitudes" />
            <p>Administrar solicitudes</p>
            <button onClick={() => navigate("/admin-solicitudes")}>
              Administrar
            </button>
          </div>
          <div className="card">
            <img src="Administrar.png" alt="Pre-test" />
            <p>Administrar Pre-test</p>
            <button onClick={() => navigate("/admin-pretest")}>
              Administrar
            </button>
          </div>
        </section>
      </main33>
    </div>
  );
}

export default AdminPublicidad;
