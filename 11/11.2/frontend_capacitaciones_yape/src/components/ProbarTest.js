import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import Sidebar from "./Sidebar";
import "./ProbarTest.css";

const ProbarTest = () => {
  const { id_test } = useParams();
  const userId = localStorage.getItem("userId");
  const [preguntas, setPreguntas] = useState([]);
  const [respuestas, setRespuestas] = useState({});
  const [resultado, setResultado] = useState(null);
  const [enviado, setEnviado] = useState(false);

  useEffect(() => {
    const fetchPreguntas = async () => {
      if (id_test) {
        try {
          const response = await axios.get(`http://localhost:8080/api/test/${id_test}/prueba`);
          const groupedPreguntas = groupAlternativasByPregunta(response.data.preguntas);
          setPreguntas(groupedPreguntas);
        } catch (error) {
          console.error("Error al obtener preguntas", error);
        }
      }
    };

    fetchPreguntas();
  }, [id_test]);

  const groupAlternativasByPregunta = (preguntas) => {
    return preguntas.reduce((acc, pregunta) => {
      const { ID_pregunta, titulo_pregunta, nombre_test } = pregunta;
      if (!acc[ID_pregunta]) {
        acc[ID_pregunta] = {
          ID_pregunta,
          titulo_pregunta,
          nombre_test,
          alternativas: [],
        };
      }
      acc[ID_pregunta].alternativas.push({
        ID_alternativa: pregunta.ID_alternativa,
        contenido: pregunta.contenido,
        es_correcta: pregunta.es_correcta,
      });
      return acc;
    }, {});
  };

  const handleRespuestaChange = (idPregunta, idAlternativa) => {
    setRespuestas((prevRespuestas) => ({
      ...prevRespuestas,
      [idPregunta]: idAlternativa,
    }));
  };

  const handleSubmit = async () => {
    const respuestasArray = Object.keys(respuestas).map((idPregunta) => ({
      ID_pregunta: parseInt(idPregunta),
      ID_alternativa: parseInt(respuestas[idPregunta]),
    }));

    try {
      const response = await axios.post(
        `http://localhost:8080/api/test/calculo/submit/${id_test}/${userId}`,
        respuestasArray
      );
      setResultado(response.data);
      setEnviado(true);
    } catch (error) {
      console.error("Error al enviar las respuestas", error);
    }
  };

  return (
    <div className="probar-test-container">
      <Sidebar />
      <main className="probar-test-content">
        <h1>Realizar Test</h1>
        <form className="test-form">
          {preguntas.length === 0 ? (
            <p>Cargando preguntas...</p>
          ) : (
            Object.values(preguntas).map((pregunta, index) => (
              <div key={pregunta.ID_pregunta} className="pregunta-container">
                <h3>
                  {index + 1}. {pregunta.titulo_pregunta}
                </h3>
                {pregunta.alternativas.map((alternativa) => (
                  <label key={alternativa.ID_alternativa} className="alternativa">
                    <input
                      type="radio"
                      name={`pregunta_${pregunta.ID_pregunta}`}
                      value={alternativa.ID_alternativa}
                      onChange={() => handleRespuestaChange(pregunta.ID_pregunta, alternativa.ID_alternativa)}
                    />
                    {alternativa.contenido}
                  </label>
                ))}
              </div>
            ))
          )}
          <button type="button" onClick={handleSubmit} className="enviar-button" disabled={enviado}>
            Enviar
          </button>
        </form>

        {enviado && (
          <div className="resultado-container">
            <p className="mensaje-exito">¡Test enviado correctamente!</p>
            {resultado && (
              <p>
                Puntaje: <strong>{resultado.puntaje}</strong> / {resultado.puntajeMaximo}
              </p>
            )}
          </div>
        )}
      </main>
    </div>
  );
};

export default ProbarTest;