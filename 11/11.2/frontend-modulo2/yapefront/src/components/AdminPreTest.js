import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./AdminPreTest.css";

function AdminPreTest() {
  const navigate = useNavigate();
  const [solicitudes, setSolicitudes] = useState([]);
  const [error, setError] = useState(null);

  // Obtener las solicitudes desde el backend
  useEffect(() => {
    const fetchSolicitudes = async () => {
      try {
        const response = await fetch("http://host.docker.internal:8080/pretest/all");
        if (!response.ok) {
          throw new Error("Error al obtener los datos del backend");
        }
        const data = await response.json();
        setSolicitudes(data);
      } catch (err) {
        setError(err.message);
      }
    };

    fetchSolicitudes();
  }, []);

  // Manejar cambios en los inputs
  const handleInputChange = (e, id_pretest, campo) => {
    const { value } = e.target;
    setSolicitudes((prevSolicitudes) =>
      prevSolicitudes.map((solicitud) =>
        solicitud.id_pretest === id_pretest
          ? { ...solicitud, [campo]: value }
          : solicitud
      )
    );
  };

  // Guardar AudienciaB
  const guardarAudienciaB = async (id_pretest) => {
    const solicitud = solicitudes.find((s) => s.id_pretest === id_pretest);
    if (!solicitud) {
      alert("Error: no se encontró la solicitud correspondiente.");
      return;
    }

    try {
      const response = await fetch(
        `http://host.docker.internal:8080/pretest/actualizarAudienciaB/${id_pretest}?audienciaB=${encodeURIComponent(
          solicitud.audienciaB
        )}`,
        {
          method: "PUT",
        }
      );
      if (!response.ok) {
        throw new Error("Error al guardar la AudienciaB");
      }
      alert(`AudienciaB del Pre-test con ID ${id_pretest} actualizada exitosamente.`);
    } catch (err) {
      alert(`Error al guardar AudienciaB: ${err.message}`);
    }
  };

  // Guardar Resultado y Fecha Fin
  const guardarResultadoYFechaFin = async (id_pretest) => {
    const solicitud = solicitudes.find((s) => s.id_pretest === id_pretest);
    if (!solicitud) {
      alert("Error: no se encontró la solicitud correspondiente.");
      return;
    }

    const fechaFin = new Date().toISOString().slice(0, 19).replace("T", " ");

    try {
      const response = await fetch(
        `http://host.docker.internal:8080/pretest/actualizarResultadoYFechaFin/${id_pretest}?resultado=${encodeURIComponent(
          solicitud.resultado
        )}&fechaFin=${encodeURIComponent(fechaFin)}`,
        {
          method: "PUT",
        }
      );
      if (!response.ok) {
        throw new Error("Error al guardar el resultado");
      }
      alert(`Resultado y Fecha Fin del Pre-test con ID ${id_pretest} actualizados exitosamente.`);
    } catch (err) {
      alert(`Error al guardar el resultado: ${err.message}`);
    }
  };

  return (
    <div className="admin-pretest">
      <main77>
        <header>
          <h1>Administrar Pre-test</h1>
        </header>
        <section className="solicitudes">
          {error ? (
            <p className="error">Error: {error}</p>
          ) : (
            <table>
              <thead>
                <tr>
                  <th>ID Pre-test</th>
                  <th>Prototipo</th>
                  <th>Fecha Inicio</th>
                  <th>Audiencia A</th>
                  <th>Audiencia B</th>
                  <th>Guardar AudienciaB</th>
                  <th>Resultado</th>
                  <th>Guardar Resultado</th>
                  <th>Fecha Fin</th>
                </tr>
              </thead>
              <tbody>
                {solicitudes.length > 0 ? (
                  solicitudes.map((solicitud) => (
                    <tr key={solicitud.id_pretest}>
                      <td>{`PT-${solicitud.id_pretest}`}</td>
                      <td>
                        <button
                          onClick={() =>
                            navigate("/campana-detalles", {
                              state: { codPrototipo: solicitud.cod_prototipo },
                            })
                          }
                        >
                          Ver Prototipo
                        </button>
                      </td>
                      <td>{solicitud.fecha_inicio?.split("T")[0]}</td>
                      <td>{solicitud.audienciaA}</td>
                      <td>
                        <input
                          type="text"
                          value={solicitud.audienciaB || ""}
                          onChange={(e) =>
                            handleInputChange(e, solicitud.id_pretest, "audienciaB")
                          }
                        />
                      </td>
                      <td>
                        <button onClick={() => guardarAudienciaB(solicitud.id_pretest)}>
                          Guardar AudienciaB
                        </button>
                      </td>
                      <td>
                        <input
                          type="text"
                          value={solicitud.resultado || ""}
                          onChange={(e) =>
                            handleInputChange(e, solicitud.id_pretest, "resultado")
                          }
                        />
                      </td>
                      <td>
                        <button onClick={() => guardarResultadoYFechaFin(solicitud.id_pretest)}>
                          Guardar Resultado
                        </button>
                      </td>
                      <td>
                        {solicitud.fecha_fin
                          ? solicitud.fecha_fin.split("T")[0]
                          : "N/A"}
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="9">No hay solicitudes disponibles</td>
                  </tr>
                )}
              </tbody>
            </table>
          )}
        </section>
        <footer1>
          <button onClick={() => navigate("/admin-publicidad")}>Volver</button>
          <button onClick={() => navigate("/nueva-audiencia")}>Nueva Audiencia</button>
        </footer1>
      </main77>
    </div>
  );
}

export default AdminPreTest;
