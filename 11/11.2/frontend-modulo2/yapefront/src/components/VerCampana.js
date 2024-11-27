import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./VerCampana.css";

const App = () => {
  const [proposals, setProposals] = useState([]); // Estado para las propuestas
  const [loading, setLoading] = useState(true); // Estado para manejar la carga
  const [error, setError] = useState(null); // Estado para manejar errores
  const navigate = useNavigate();

  // Función para obtener las campañas desde el backend
  const fetchProposals = async () => {
    try {
      const response = await fetch("http://host.docker.internal:8080/campanas/listado"); // Cambia la URL si es necesario
      if (!response.ok) {
        throw new Error("Error al obtener las campañas publicitarias");
      }
      const data = await response.json();
      setProposals(data); // Actualizamos el estado con los datos obtenidos
    } catch (err) {
      setError(err.message); // Guardamos el mensaje de error
    } finally {
      setLoading(false); // Terminamos la carga
    }
  };

  // Efecto para cargar las propuestas al montar el componente
  useEffect(() => {
    fetchProposals();
  }, []);

  const handleMisPropuestas = () => {
    navigate("/login3"); // Redirige al componente de login
  };


  return (
    <div className="app1">
      <header className="header1">
        <h1>Campaña publicitaria</h1>
        <div className="tabs">
        <button className="tab" onClick={handleMisPropuestas}>
            Mis Propuestas
          </button>
        </div>
      </header>
      <main3 className="main-content1">
        {loading && <p>Cargando...</p>}
        {error && <p>Error: {error}</p>}
        {!loading && !error && (
          <div className="proposals-list1">
            {proposals.map((proposal, index) => (
              <div key={index} className="proposal-card1">
                <img
                  src={`http://host.docker.internal:8080${proposal.urlRecurso}`}
                  alt={`Imagen de ${proposal.nombreCampana}`}
                  className="proposal-image"
                />
                <div className="proposal-info">
                  <p>
                    <strong>Nombre:</strong> {proposal.nombreCampana}
                  </p>
                  <p>
                    <strong>Fecha:</strong>{" "}
                    {new Date(proposal.fechaPublicacion).toLocaleDateString()}
                  </p>
                  <p>
                    <strong>Descripción:</strong> {proposal.descripcion}
                  </p>
                  <div className="buttons-container">
                    <button
                      className="view-more"
                      onClick={() =>
                        navigate("/campana-detalles", {
                          state: { codPrototipo: proposal.codPrototipo },
                        })
                      }
                    >
                      Ver más
                    </button>
                    <button
                      className="view-report"
                      onClick={() => navigate(`/reporte/${proposal.codCampana}`)}
                    >
                      Ver reporte
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )}
      </main3>
    </div>
  );
};

export default App;
