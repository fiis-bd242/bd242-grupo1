import React, { useState, useEffect, useRef } from 'react';
import '../styles/GestionarEntrevistas.css';

const GestionarEntrevistas = ({ 
  entrevistasDetails, 
  onClose, 
  postulanteId, 
  fetchEntrevistasDetails
}) => {
  const [showCreateModal, setShowCreateModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [showObservationsModal, setShowObservationsModal] = useState(false);
  const [observations, setObservations] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [employeeNames, setEmployeeNames] = useState({});
  const [formData, setFormData] = useState({
    estado: 'Pendiente',
    fecha: '',
    tipo_entrevista: 'tecnica',
    id_empleado: ''
  });
  const [editFormData, setEditFormData] = useState({
    estado: '',
    fecha: '',
    tipo_entrevista: '',
    id_empleado: '',
    puntaje_general: 0
  });
  const [selectedEntrevista, setSelectedEntrevista] = useState(null);
  const [showCreateObservationModal, setShowCreateObservationModal] = useState(false);
  const [createObservationFormData, setCreateObservationFormData] = useState({
    id_feedback: '',
    nombre: '',
    descripcion: ''
  });
  const [showEditObservationModal, setShowEditObservationModal] = useState(false);
  const [editObservationFormData, setEditObservationFormData] = useState({
    id_feedback: '',
    nombre: '',
    descripcion: ''
  });
  const [selectedObservation, setSelectedObservation] = useState(null);
  const [loadingObservations, setLoadingObservations] = useState(false);
  // const observationsCache = useRef({}); // Usar useRef para caché
  // Comentado para deshabilitar el caché temporalmente
  const observationsCache = useRef({}); // Opcional: Eliminar si no se necesita el caché

  const [isUpdating, setIsUpdating] = useState(false);
  // const [ setEntrevistasDetails] = useState(entrevistasDetails);

  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const response = await fetch('http://localhost:8080/empleados');
        if (!response.ok) throw new Error(`Error: ${response.statusText}`);
        const data = await response.json();
        setEmployees(data);
      } catch (error) {
        console.error('Error fetching employees:', error);
        alert('Error al cargar los empleados');
      }
    };

    fetchEmployees();
  }, []);

  useEffect(() => {
    const fetchEmployeeNames = async () => {
      const names = {};
      for (const entrevista of entrevistasDetails) {
        try {
          const response = await fetch(`http://localhost:8080/empleados/${entrevista.id_empleado}`);
          if (!response.ok) throw new Error(`Error: ${response.statusText}`);
          const data = await response.json();
          names[entrevista.id_empleado] = `${data.nombre} ${data.apellido}`;
        } catch (error) {
          console.error('Error fetching employee name:', error);
          names[entrevista.id_empleado] = 'Empleado desconocido';
        }
      }
      setEmployeeNames(names);
    };

    fetchEmployeeNames();
  }, [entrevistasDetails]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleEditChange = (e) => {
    const { name, value } = e.target;
    setEditFormData({ ...editFormData, [name]: value });
  };

  const handleCreateObservationChange = (e) => {
    const { name, value } = e.target;
    setCreateObservationFormData({ ...createObservationFormData, [name]: value });
  };

  const handleEditObservationChange = (e) => {
    const { name, value } = e.target;
    setEditObservationFormData({ ...editObservationFormData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = {
      ...formData,
      puntaje_general: 0, // Set puntaje_general to 0
      id_postulante: postulanteId
    };

    try {
      const response = await fetch('http://localhost:8080/api/entrevistas', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(`Error del servidor: ${errorData}`);
      }

      alert('Entrevista creada exitosamente');
      setShowCreateModal(false); // Hide the form after successful creation
      fetchEntrevistasDetails(postulanteId); // Fetch updated list of interviews
    } catch (error) {
      console.error('Error al crear la entrevista:', error);
      alert(`Error al crear la entrevista: ${error.message}`);
    }
  };

  const handleEditSubmit = async (e) => {
    e.preventDefault();
    if (isUpdating) return;

    setIsUpdating(true);

    try {
      const payload = {
        id_entrevista: selectedEntrevista.id_entrevista,
        estado: editFormData.estado,
        fecha: editFormData.fecha,
        puntaje_general: parseInt(editFormData.puntaje_general),
        id_postulante: postulanteId,
        id_empleado: parseInt(editFormData.id_empleado),
        tipo_entrevista: editFormData.tipo_entrevista
      };

      // 1. Update interview in SQL database
      const response = await fetch(`http://localhost:8080/api/entrevistas/${selectedEntrevista.id_entrevista}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        throw new Error(`Error del servidor: ${await response.text()}`);
      }

      // 2. Migrate data to Neo4j
      const migrationResponse = await fetch('http://localhost:8080/migrate', {
        method: 'GET',
      });

      if (!migrationResponse.ok) {
        throw new Error('Error al migrar los datos');
      }

      // 3. Calculate new relative score
      const scoreResponse = await fetch(`http://localhost:8080/calculate/relative-score/postulante?postulanteId=${postulanteId}`, {
        method: 'GET',
      });

      if (!scoreResponse.ok) {
        throw new Error('Error al recalcular el puntaje relativo');
      }

      // 4. Fetch updated interviews
      fetchEntrevistasDetails(postulanteId);

      // Close modal and reset form
      setShowEditModal(false);
      setSelectedEntrevista(null);
      setEditFormData({
        estado: '',
        fecha: '',
        tipo_entrevista: '',
        id_empleado: '',
        puntaje_general: 0
      });

      alert('Entrevista actualizada exitosamente');

    } catch (error) {
      console.error('Error:', error);
      alert(`Error: ${error.message}`);
    } finally {
      setIsUpdating(false);
    }
  };

  const handleCreateObservationSubmit = async (e) => {
    e.preventDefault();
    if (!selectedEntrevista) {
      alert('No hay entrevista seleccionada.');
      return;
    }
    const payload = {
      ...createObservationFormData,
      id_entrevista: selectedEntrevista.id_entrevista // Changed from id_feedback
    };
  
    try {
      const response = await fetch('http://localhost:8080/api/observaciones', { // Updated endpoint
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });
  
      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(`Error del servidor: ${errorData}`);
      }

      // Obtener la nueva observación creada
      const newObservation = await response.json();
      
      // Actualizar el estado local y el caché
      const updatedObservations = [...(observations || []), newObservation];
      setObservations(updatedObservations);
      
      // Limpiar el formulario
      setCreateObservationFormData({
        id_entrevista: '', // Updated field
        nombre: '',
        descripcion: ''
      });
      
      setShowCreateObservationModal(false);
      setShowObservationsModal(true); // Mantener visible el modal de observaciones
      alert('Observación creada exitosamente');
    } catch (error) {
      console.error('Error al crear la observación:', error);
      alert(`Error al crear la observación: ${error.message}`);
    }
  };
  
  const handleEditObservationSubmit = async (e) => {
    e.preventDefault();
    if (!selectedObservation) {
      alert('No hay observación seleccionada.');
      return;
    }
    const payload = {
      ...editObservationFormData,
      id_entrevista: selectedObservation.id_entrevista // Changed from id_feedback
    };
  
    try {
      const response = await fetch(`http://localhost:8080/api/observaciones/${selectedObservation.id_observacion}`, { // Updated endpoint
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });
  
      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(`Error del servidor: ${errorData}`);
      }
  
      // Update the observations list with the edited observation
      const updatedObservations = observations.map(obs =>
        obs.id_observacion === selectedObservation.id_observacion 
          ? { ...obs, ...payload }
          : obs
      );
      
      // Update state
      setObservations(updatedObservations);
      setShowEditObservationModal(false);
      alert('Observación actualizada exitosamente');
    } catch (error) {
      console.error('Error al actualizar la observación:', error);
      alert(`Error al actualizar la observación: ${error.message}`);
    }
  };
  

  const handleViewObservations = async (id_entrevista) => {
    const entrevista = entrevistasDetails.find(e => e.id_entrevista === id_entrevista);
    if (entrevista) {
      setSelectedEntrevista(entrevista);
    } else {
      alert('No se encontró la entrevista seleccionada.');
      return;
    }
  
    setLoadingObservations(true);
    try {
      const response = await fetch(`http://localhost:8080/api/observaciones/entrevista/${id_entrevista}`); // Updated endpoint
      
      // Initialize empty array for observations
      let observationsData = [];
      
      if (response.status === 404) {
        // No observations yet - that's okay, we'll use the empty array
        observationsCache.current[id_entrevista] = [];
      } else if (!response.ok) {
        throw new Error(`Error: ${response.statusText}`);
      } else {
        // We got some observations
        observationsData = await response.json();
        // Ensure we have an array
        observationsData = Array.isArray(observationsData) ? observationsData : [];
        observationsCache.current[id_entrevista] = observationsData;
      }
      
      setObservations(observationsCache.current[id_entrevista]);
      setShowObservationsModal(true);
    } catch (error) {
      console.error('Error fetching observations:', error);
      alert('Error al cargar las observaciones');
      // Ensure we have an empty array even on error
      setObservations([]);
    } finally {
      setLoadingObservations(false);
    }
    // Removemos la llamada a fetchPostulanteDetails ya que no necesitamos estos datos
    // fetchPostulanteDetails(postulanteId);
  
    // Ejecutar la migración de datos antes de recalcular el puntaje relativo
    try {
      const migrationResponse = await fetch('http://localhost:8080/migrate', {
        method: 'GET',
      });
  
      if (!migrationResponse.ok) {
        throw new Error('Error al migrar los datos');
      }
    } catch (error) {
      console.error('Error al migrar los datos:', error);
      alert('Error al migrar los datos');
      return; // Detener si hay error en la migración
    }
  
    // Recalcular el puntaje relativo
    try {
      const scoreResponse = await fetch(`http://localhost:8080/calculate/relative-score/postulante?postulanteId=${postulanteId}`, {
        method: 'GET',
      });
      
      if (!scoreResponse.ok) {
        throw new Error('Error al recalcular el puntaje relativo');
      }
      
      // ...existing code to fetch observations...
    } catch (error) {
      console.error('Error al recalcular el puntaje relativo:', error);
      alert('Error al recalcular el puntaje relativo');
      // ...existing error handling...
    }
  };
  

  const handleEditClick = (entrevista) => {
    setSelectedEntrevista(entrevista);
    setEditFormData({
      estado: entrevista.estado,
      fecha: entrevista.fecha.split('T')[0],
      tipo_entrevista: entrevista.tipo_entrevista,
      id_empleado: entrevista.id_empleado,
      puntaje_general: entrevista.puntaje_general
    });
    setShowEditModal(true);
  };

  const handleEditObservationClick = (observation) => {
    setSelectedObservation(observation);
    setEditObservationFormData({
      id_feedback: observation.id_feedback,
      nombre: observation.nombre,
      descripcion: observation.descripcion
    });
    setShowEditObservationModal(true);
  };

  const handleDeleteObservation = async (observationId) => {
    const confirmDelete = window.confirm('¿Estás seguro de que deseas eliminar esta observación?');
    if (!confirmDelete) return;
  
    try {
      const response = await fetch(`http://localhost:8080/api/observaciones/${observationId}`, { // Updated endpoint
        method: 'DELETE',
      });
  
      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(`Error del servidor: ${errorData}`);
      }
  
      alert('Observación eliminada exitosamente');
  
      // Actualizar el estado y el caché
      const updatedObservations = observations.filter(obs => obs.id_observacion !== observationId);
      const entrevistaId = selectedEntrevista ? selectedEntrevista.id_entrevista : null;
      if (entrevistaId) {
        observationsCache.current[entrevistaId] = updatedObservations;
      }
      setObservations(updatedObservations);
    } catch (error) {
      console.error('Error al eliminar la observación:', error);
      alert(`Error al eliminar la observación: ${error.message}`);
    }
  };
  
  const handleDeleteEntrevista = async (entrevistaId) => {
    const confirmDelete = window.confirm('¿Estás seguro de que deseas eliminar esta entrevista?');
    if (!confirmDelete) return;
  
    try {
      const response = await fetch(`http://localhost:8080/api/entrevistas/${entrevistaId}`, {
        method: 'DELETE',
      });
  
      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(`Error del servidor: ${errorData}`);
      }
  
      alert('Entrevista eliminada exitosamente');
  
      // Actualizar la lista de entrevistas después de eliminar
      fetchEntrevistasDetails(postulanteId);
    } catch (error) {
      console.error('Error al eliminar la entrevista:', error);
      alert(`Error al eliminar la entrevista: ${error.message}`);
    }
  };
  

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal" onClick={(e) => e.stopPropagation()}>
        <button className="close-popup" onClick={onClose}>&times;</button>
        <h2>Detalles de Entrevistas</h2>
        <div className="entrevistas-list">
          {entrevistasDetails.map((entrevista) => (
            <div key={entrevista.id_entrevista} className="entrevista-item">
              <p><strong>Tipo:</strong> {entrevista.tipo_entrevista}</p>
              <p><strong>Estado:</strong> {entrevista.estado}</p>
              <p><strong>Fecha:</strong> {new Date(entrevista.fecha).toLocaleDateString()}</p>
              <p><strong>Puntaje General:</strong> {entrevista.puntaje_general}</p>
              <p><strong>Empleado:</strong> {employeeNames[entrevista.id_empleado]}</p>
              <button onClick={() => handleViewObservations(entrevista.id_entrevista)}>Ver Observaciones</button>
              <button onClick={() => handleEditClick(entrevista)}>Editar</button>
              <button onClick={() => handleDeleteEntrevista(entrevista.id_entrevista)}>Eliminar</button>
            </div>
          ))}
        </div>
        <div className="modal-buttons">
          <button type="button" onClick={onClose}>Volver</button>
          <button type="button" onClick={() => setShowCreateModal(true)}>Crear Entrevista</button>
        </div>

        {showCreateModal && (
          <div className="modal-overlay" onClick={() => setShowCreateModal(false)}>
            <div className="modal" onClick={(e) => e.stopPropagation()}>
              <button className="close-popup" onClick={() => setShowCreateModal(false)}>&times;</button>
              <h2>Crear Nueva Entrevista</h2>
              <form onSubmit={handleSubmit} className="entrevista-form">
                <label>
                  Estado:
                  <input
                    type="text"
                    name="estado"
                    value={formData.estado}
                    onChange={handleChange}
                    disabled
                  />
                </label>
                <label>
                  Fecha:
                  <input
                    type="date"
                    name="fecha"
                    value={formData.fecha}
                    onChange={handleChange}
                    required
                  />
                </label>
                <label>
                  Tipo de Entrevista:
                  <select
                    name="tipo_entrevista"
                    value={formData.tipo_entrevista}
                    onChange={handleChange}
                    required
                  >
                    <option value="Tecnica">Técnica</option>
                    <option value="HR">HR</option>
                    <option value="Manager">Manager</option>
                  </select>
                </label>
                <label>
                  Empleado:
                  <select
                    name="id_empleado"
                    value={formData.id_empleado}
                    onChange={handleChange}
                    required
                  >
                    <option value="">Seleccione un empleado</option>
                    {employees.map((employee) => (
                      <option key={employee.id_empleado} value={employee.id_empleado}>
                        {employee.nombre} {employee.apellido}
                      </option>
                    ))}
                  </select>
                </label>
                <div className="modal-buttons">
                  <button type="submit">Crear Entrevista</button>
                </div>
              </form>
            </div>
          </div>
        )}

        {showEditModal && (
          <div className="modal-overlay" onClick={() => setShowEditModal(false)}>
            <div className="modal" onClick={(e) => e.stopPropagation()}>
              <button className="close-popup" onClick={() => setShowEditModal(false)}>&times;</button>
              <h2>Editar Entrevista</h2>
              <form onSubmit={handleEditSubmit} className="entrevista-form">
                <label>
                  Estado:
                  <input
                    type="text"
                    name="estado"
                    value={editFormData.estado}
                    onChange={handleEditChange}
                    required
                  />
                </label>
                <label>
                  Fecha:
                  <input
                    type="date"
                    name="fecha"
                    value={editFormData.fecha}
                    onChange={handleEditChange}
                    required
                  />
                </label>
                <label>
                  Tipo de Entrevista:
                  <select
                    name="tipo_entrevista"
                    value={editFormData.tipo_entrevista}
                    onChange={handleEditChange}
                    required
                  >
                    <option value="tecnica">Técnica</option>
                    <option value="hr">HR</option>
                    <option value="manager">Manager</option>
                  </select>
                </label>
                <label>
                  Empleado:
                  <select
                    name="id_empleado"
                    value={editFormData.id_empleado}
                    onChange={handleEditChange}
                    required
                  >
                    <option value="">Seleccione un empleado</option>
                    {employees.map((employee) => (
                      <option key={employee.id_empleado} value={employee.id_empleado}>
                        {employee.nombre} {employee.apellido}
                      </option>
                    ))}
                  </select>
                </label>
                <label>
                  Puntaje General:
                  <input
                    type="number"
                    name="puntaje_general"
                    value={editFormData.puntaje_general}
                    onChange={handleEditChange}
                    required
                  />
                </label>
                <div className="modal-buttons">
                  <button type="submit">Guardar Cambios</button>
                </div>
              </form>
            </div>
          </div>
        )}

        {showObservationsModal && (
          <div className="modal-overlay" onClick={() => setShowObservationsModal(false)}>
            <div className="modal" onClick={(e) => e.stopPropagation()}>
              <button className="close-popup" onClick={() => setShowObservationsModal(false)}>&times;</button>
              <h2>Observaciones</h2>
              {loadingObservations ? (
                <div className="spinner"></div> // Mostrar spinner durante la carga
              ) : (
                <div className="observaciones-list">
                  {Array.isArray(observations) && observations.length > 0 ? (
                    observations.map((observation) => (
                      <div key={observation.id_observacion} className="observacion-item">
                        <p><strong>Nombre:</strong> {observation.nombre}</p>
                        <p><strong>Descripción:</strong> {observation.descripcion}</p>
                        <div className="modal-buttons">
                          <button onClick={() => handleEditObservationClick(observation)}>Editar</button>
                          <button onClick={() => handleDeleteObservation(observation.id_observacion)}>Eliminar</button>
                        </div>
                      </div>
                    ))
                  ) : (
                    <p>No hay observaciones disponibles.</p> // Mensaje cuando no hay observaciones
                  )}
                </div>
              )}
              <div className="modal-buttons">
                <button type="button" onClick={() => setShowCreateObservationModal(true)}>Crear Observación</button>
                <button type="button" onClick={() => setShowObservationsModal(false)}>Cerrar</button>
              </div>
            </div>
          </div>
        )}

        {showCreateObservationModal && (
          <div className="modal-overlay" onClick={() => setShowCreateObservationModal(false)}>
            <div className="modal" onClick={(e) => e.stopPropagation()}>
              <button className="close-popup" onClick={() => setShowCreateObservationModal(false)}>&times;</button>
              <h2>Crear Observación</h2>
              <form onSubmit={handleCreateObservationSubmit} className="observacion-form">
                <label>
                  Nombre:
                  <input
                    type="text"
                    name="nombre"
                    value={createObservationFormData.nombre}
                    onChange={handleCreateObservationChange}
                    required
                  />
                </label>
                <label>
                  Descripción:
                  <textarea
                    name="descripcion"
                    value={createObservationFormData.descripcion}
                    onChange={handleCreateObservationChange}
                    required
                  />
                </label>
                <div className="modal-buttons">
                  <button type="submit">Crear Observación</button>
                </div>              </form>            </div>          </div>        )}        {showEditObservationModal && (          <div className="modal-overlay" onClick={() => setShowEditObservationModal(false)}>            <div className="modal" onClick={(e) => e.stopPropagation()}>              <button className="close-popup" onClick={() => setShowEditObservationModal(false)}>&times;</button>              <h2>Editar Observación</h2>              <form onSubmit={handleEditObservationSubmit} className="observacion-form">                <label>                  Nombre:                  <input                    type="text"                    name="nombre"                    value={editObservationFormData.nombre}                    onChange={handleEditObservationChange}                    required                  />                </label>                <label>                  Descripción:                  <textarea                    name="descripcion"                    value={editObservationFormData.descripcion}                    onChange={handleEditObservationChange}                    required                  />                </label>                <div className="modal-buttons">                  <button type="submit">Guardar Cambios</button>                </div>              </form>            </div>          </div>        )}      </div>    </div>  );};export default GestionarEntrevistas;