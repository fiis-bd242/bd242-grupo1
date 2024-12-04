import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom"; // Para redirigir
import Sidebar from "../components/Sidebar"; // Importamos el Sidebar
import "./MisCapacitaciones.css"; // Importamos el CSS

const MisCapacitaciones = () => {
  const [capacitaciones, setCapacitaciones] = useState([]);
  const [nombreFiltro, setNombreFiltro] = useState("");
  const [estadoFiltro, setEstadoFiltro] = useState("en_proceso"); // Valor predeterminado
  const [menuActivo, setMenuActivo] = useState(null); // Control del menú desplegable
  const navigate = useNavigate();

  const fetchCapacitaciones = async () => {
    try {
      const userId = localStorage.getItem("userId"); // Obtener el ID del instructor desde localStorage
      if (userId) {
        const response = await axios.get("http://localhost:8080/api/capacitaciones", {
          params: {
            idInstructor: userId, // Pasar el ID del instructor
            nombre: nombreFiltro || null, // Filtro de nombre
            estado: estadoFiltro, // Filtro de estado
          },
        });
        setCapacitaciones(response.data);
      } else {
        console.error("No se encontró el ID del instructor");
      }
    } catch (error) {
      console.error("Error al cargar las capacitaciones:", error);
    }
  };

  useEffect(() => {
    fetchCapacitaciones();
  }, []);

  const handleVerDetalles = (slug) => {
    navigate(`/mis-capacitaciones/${slug}`); // Redirige a la nueva página
  };

  const handleEditar = (cod) => {
    console.log(`Editar capacitación con código: ${cod}`);
    // Aquí puedes implementar la funcionalidad de edición.
  };

  const toggleMenu = (cod) => {
    setMenuActivo((prev) => (prev === cod ? null : cod)); // Alterna el menú desplegable
  };

  return (
    <div className="main-container">
      <Sidebar /> {/* Renderizamos el Sidebar */}
      <div className="mis-capacitaciones-container">
        <h1 className="titulo">Gestión de Capacitaciones</h1>
        <div className="filtros">
          <label>
            Nombre:
            <input
              type="text"
              value={nombreFiltro}
              onChange={(e) => setNombreFiltro(e.target.value)}
            />
          </label>
          <label>
            Estado:
            <select
              value={estadoFiltro}
              onChange={(e) => setEstadoFiltro(e.target.value)}
            >
              <option value="en_proceso">En Proceso</option>
              <option value="pendiente">Pendiente</option>
              <option value="finalizado">Finalizado</option>
            </select>
          </label>
          <button className="buscar-btn" onClick={fetchCapacitaciones}>
            Buscar
          </button>
        </div>

        <div className="tabla-contenedor">
          <table className="tabla">
            <thead>
              <tr>
                <th>Cod</th>
                <th>Nombre</th>
                <th>Fecha de Inicio</th>
                <th>Fecha Fin</th>
                <th>Estado</th>
                <th>Opciones</th>
              </tr>
            </thead>
            <tbody>
              {capacitaciones.map((cap) => (
                <tr key={cap.cod}>
                  <td>{cap.cod}</td>
                  <td>
                    <button
                      onClick={() =>
                        handleVerDetalles(cap.nombre.replace(/\s+/g, "-").toLowerCase())
                      }
                      className="link"
                    >
                      {cap.nombre}
                    </button>
                  </td>
                  <td>{cap["Fecha de Inicio"]}</td>
                  <td>{cap["Fecha Fin"]}</td>
                  <td>{cap.estado}</td>
                  <td>
                    <div className="menu-container">
                      <button
                        onClick={() => toggleMenu(cap.cod)}
                        className="menu-btn"
                      >
                        ...
                      </button>
                      {menuActivo === cap.cod && (
                        <ul className="menu">
                          <li>
                            <button
                              onClick={() => handleEditar(cap.cod)}
                              className="menu-item"
                            >
                              Editar
                            </button>
                          </li>
                          <li>
                            <button
                              onClick={() =>
                                handleVerDetalles(
                                  cap.nombre.replace(/\s+/g, "-").toLowerCase()
                                )
                              }
                              className="menu-item"
                            >
                              Ver Detalles
                            </button>
                          </li>
                        </ul>
                      )}
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default MisCapacitaciones;
