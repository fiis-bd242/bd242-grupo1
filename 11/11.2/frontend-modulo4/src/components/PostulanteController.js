import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const PostulanteController = () => {
  const { id } = useParams(); // Obtain the postulante ID from URL parameters
  const [entrevistas, setEntrevistas] = useState([]);
  const [message, setMessage] = useState('');

  // State variables for Experiencia Laboral
  const [fechaInicio, setFechaInicio] = useState('');
  const [fechaFin, setFechaFin] = useState('');

  // Handler functions for Experiencia Laboral
  const handleFechaInicioChange = (e) => {
    setFechaInicio(e.target.value);
  };

  const handleFechaFinChange = (e) => {
    setFechaFin(e.target.value);
  };

  useEffect(() => {
    axios.get(`/api/postulantes/${id}/entrevistas-hechas`)
      .then(response => {
        if (response.data.length === 0) {
          setMessage('Aún no ha hecho una entrevista.');
        } else {
          setEntrevistas(response.data);
          setMessage('');
        }
      })
      .catch(error => {
        console.error('Error fetching entrevistas:', error);
        setMessage('Error al obtener los datos del postulante.');
      });
  }, [id]);

  return (
    <div>
      {message && <p>{message}</p>}
      {entrevistas.map(entrevista => (
        <div key={entrevista.id_entrevista}>
          <p>Fecha: {entrevista.fecha}</p>
          <p>Estado: {entrevista.estado}</p>
          {/* Other fields */}
        </div>
      ))}
      
      <form>
        {/* ...other form fields... */}
        
        <div className="experiencia-laboral">
          <h3>Experiencia Laboral</h3>
          
          {/* Fecha Inicio */}
          <div className="form-group">
            <label htmlFor="fechaInicio">Fecha Inicio:</label>
            <input
              type="date"
              id="fechaInicio"
              name="fechaInicio"
              value={fechaInicio}
              onChange={handleFechaInicioChange}
              required
            />
          </div>
          
          {/* Fecha Fin */}
          <div className="form-group">
            <label htmlFor="fechaFin">Fecha Fin:</label>
            <input
              type="date"
              id="fechaFin"
              name="fechaFin"
              value={fechaFin}
              onChange={handleFechaFinChange}
              required
            />
          </div>
          
          {/* ...other experiencia laboral fields... */}
        </div>
        
        {/* ...submit button and other form elements... */}
      </form>
      
    </div>
  );
};

export default PostulanteController;
