import React, { useState, useEffect, useCallback } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import '../styles/Vacante.css';

const GestionarEntrevistas = () => {
    const { id } = useParams(); // id del postulante
    const navigate = useNavigate();
    const [entrevistas, setEntrevistas] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    
    // Modal states
    const [showCreateModal, setShowCreateModal] = useState(false);
    const [showEditModal, setShowEditModal] = useState(false);
    const [showDetailsModal, setShowDetailsModal] = useState(false);
    const [showScoresModal, setShowScoresModal] = useState(false);
    const [showObservationModal, setShowObservationModal] = useState(false);

    // Data states
    const [selectedEntrevista, setSelectedEntrevista] = useState(null);
    const [entrevistaCompleta, setEntrevistaCompleta] = useState(null);
    
    // Nuevo estado para empleados y tipos de entrevista
    const [empleados, setEmpleados] = useState([]);
    const [tiposEntrevista, setTiposEntrevista] = useState([]);
    
    const [formData, setFormData] = useState({
        fecha: '',
        estado: 'Pendiente',
        id_empleado: '',
        id_tipo_entrevista: '',
        id_postulante: parseInt(id) // Asegurar que se incluya el id del postulante
    });
    const [observacionForm, setObservacionForm] = useState({
        contenido: '',
        tipo: 'Regular'
    });
    const [scores, setScores] = useState([]);

    // Fetch all interviews
    const fetchEntrevistas = useCallback(async () => { // Envolver con useCallback
        try {
            console.log('Fetching entrevistas...');
            const response = await fetch(`http://localhost:8080/api/postulantes/${id}/entrevistas`);
            if (!response.ok) throw new Error('Error al obtener entrevistas');
            const data = await response.json();
            console.log('Entrevistas obtenidas:', data);
            setEntrevistas(data);
            setError(null);
        } catch (err) {
            setError('Error al cargar las entrevistas');
            console.error(err);
        } finally {
            setLoading(false);
        }
    }, [id]); // Añadir dependencias

    // Función para obtener empleados
    const fetchEmpleados = useCallback(async () => { // Envolver con useCallback
        try {
            console.log('Fetching empleados...');
            // Actualiza la URL según el endpoint proporcionado
            const response = await fetch('http://localhost:8080/empleados');
            if (!response.ok) throw new Error('Error al obtener empleados');
            const data = await response.json();
            console.log('Empleados obtenidos:', data);
            setEmpleados(data);
        } catch (err) {
            setError('Error al cargar los empleados');
            console.error(err);
        }
    }, []); // Sin dependencias adicionales

    // Función para obtener tipos de entrevista
    const fetchTiposEntrevista = useCallback(async () => { // Envolver con useCallback
        try {
            console.log('Fetching tipos de entrevista...');
            // Actualiza la URL al nuevo endpoint proporcionado
            const response = await fetch('http://localhost:8080/api/tipos-entrevista/id-nombres');
            if (!response.ok) throw new Error('Error al obtener tipos de entrevista');
            const data = await response.json();
            console.log('Tipos de entrevista obtenidos:', data); // Verificar estructura de datos

            // Verificar si los datos están dentro de una propiedad específica
            // Por ejemplo, si la respuesta es { tipos: [...] }, ajusta a data.tipos
            const tipos = Array.isArray(data) ? data : data.tipos || [];
            console.log('Tipos de entrevista procesados:', tipos);

            setTiposEntrevista(data);

        } catch (err) {
            setError('Error al cargar los tipos de entrevista');
            console.error(err);
        }
    }, []); // Sin dependencias adicionales

    // Fetch complete interview details
    const fetchEntrevistaCompleta = useCallback(async (id_entrevista) => {
        try {
            const response = await fetch(`http://localhost:8080/api/entrevistas/${id_entrevista}/completa`);
            if (!response.ok) throw new Error('Error al obtener detalles');
            const data = await response.json();
            setEntrevistaCompleta(data);
            setShowDetailsModal(true);
            setError(null);
        } catch (err) {
            setError('Error al cargar los detalles');
            console.error(err);
        }
    }, []);

    // Fetch all data with useCallback
    const fetchAllData = useCallback(async () => {
        await Promise.all([fetchEntrevistas(), fetchEmpleados(), fetchTiposEntrevista()]);
        setLoading(false);
    }, [fetchEntrevistas, fetchEmpleados, fetchTiposEntrevista]); // Añadir dependencias

    useEffect(() => {
        fetchAllData();
    }, [fetchAllData]); // Incluir fetchAllData en las dependencias

    // Create new interview
    const handleCreate = async (e) => {
        e.preventDefault();
        try {
            const nuevaEntrevista = {
                fecha: formData.fecha,
                estado: formData.estado,
                idEmpleado: formData.id_empleado ? parseInt(formData.id_empleado) : null,
                idTipoEntrevista: formData.id_tipo_entrevista ? parseInt(formData.id_tipo_entrevista) : null,
                idPostulante: parseInt(id) || null
            };

            console.log('Datos a enviar:', JSON.stringify(nuevaEntrevista, null, 2)); // Mostrar datos enviados

            // Validar que los campos requeridos no estén vacíos ni sean nulos
            if (
                !nuevaEntrevista.fecha ||
                !nuevaEntrevista.idEmpleado ||
                !nuevaEntrevista.idTipoEntrevista ||
                !nuevaEntrevista.idPostulante
            ) {
                setError('Por favor, completa todos los campos requeridos.');
                return;
            }

            const response = await fetch('http://localhost:8080/api/entrevistas', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(nuevaEntrevista)
            });

            if (!response.ok) {
                let errorMessage = 'Error al crear entrevista';
                const contentType = response.headers.get('Content-Type');
                if (contentType && contentType.includes('application/json')) {
                    const errorData = await response.json();
                    errorMessage = errorData.message || errorMessage;
                }
                throw new Error(errorMessage);
            }

            // Si la respuesta tiene contenido JSON, puedes procesarlo aquí si es necesario
            // const result = await response.json();
            // console.log('Entrevista creada exitosamente:', result);

            await fetchEntrevistas();
            setShowCreateModal(false);
            setFormData({ 
                fecha: '', 
                estado: 'Pendiente',
                id_empleado: '',
                id_tipo_entrevista: '',
                id_postulante: parseInt(id)
            });
        } catch (err) {
            setError(`Error al crear la entrevista: ${err.message}`); // Mostrar mensaje de error detallado
            console.error(err);
        }
    };

    // Update interview
    const handleUpdate = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/api/entrevistas/${selectedEntrevista.id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData)
            });
            
            if (!response.ok) throw new Error('Error al actualizar');
            await fetchEntrevistas();
            setShowEditModal(false);
        } catch (err) {
            setError('Error al actualizar la entrevista');
            console.error(err);
        }
    };

    // Update scores
    const handleUpdateScores = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/api/entrevistas/${selectedEntrevista.id}/puntajes`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(scores)
            });
            
            if (!response.ok) throw new Error('Error al actualizar puntajes');
            await fetchEntrevistas();
            setShowScoresModal(false);
        } catch (err) {
            setError('Error al actualizar los puntajes');
            console.error(err);
        }
    };

    // Add observation
    const handleAddObservation = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/api/entrevistas/${selectedEntrevista.id}/observaciones`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(observacionForm)
            });
            
            if (!response.ok) throw new Error('Error al agregar observación');
            await fetchEntrevistaCompleta(selectedEntrevista.id);
            setShowObservationModal(false);
            setObservacionForm({ contenido: '', tipo: 'Regular' });
        } catch (err) {
            setError('Error al agregar la observación');
            console.error(err);
        }
    };

    // Delete interview
    const handleDelete = async (id_entrevista) => {
        if (!window.confirm('¿Está seguro de eliminar esta entrevista?')) return;
        
        try {
            const response = await fetch(`http://localhost:8080/api/entrevistas/${id_entrevista}`, {
                method: 'DELETE'
            });
            
            if (!response.ok) throw new Error('Error al eliminar');
            await fetchEntrevistas();
        } catch (err) {
            setError('Error al eliminar la entrevista');
            console.error(err);
        }
    };

    return (
        <div className="gestionar-entrevistas-container">
            <div className="gestionar-entrevistas-content">
                <header className="header">
                    <h1 className="page-title">Gestionar Entrevistas</h1>
                    <button onClick={() => navigate(-1)} className="secondary-button">
                        Volver
                    </button>
                </header>

                <div className="content-section">
                    <button onClick={() => setShowCreateModal(true)} className="create-button">
                        Nueva Entrevista
                    </button>

                    {loading ? (
                        <div className="loading-indicator">Cargando entrevistas...</div>
                    ) : error ? (
                        <div className="error-message">
                            {error}
                            <button onClick={fetchEntrevistas}>Reintentar</button>
                        </div>
                    ) : entrevistas.length > 0 ? (
                        <table className="vacante-table">
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                    <th>Tipo</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {entrevistas.map(entrevista => (
                                    <tr key={entrevista.id_entrevista}>
                                        <td>{new Date(entrevista.fecha).toLocaleDateString()}</td>
                                        <td>{entrevista.estado}</td>
                                        <td>{entrevista.tipo_entrevista?.nombre}</td>
                                        <td>
                                            <button 
                                                onClick={() => fetchEntrevistaCompleta(entrevista.id_entrevista)}
                                                className="manage-button"
                                            >
                                                Ver Detalles
                                            </button>
                                            <button 
                                                onClick={() => {
                                                    setSelectedEntrevista(entrevista);
                                                    setShowEditModal(true);
                                                    // Prellenar formData con los datos de la entrevista seleccionada
                                                    setFormData({
                                                        fecha: entrevista.fecha,
                                                        estado: entrevista.estado,
                                                        id_empleado: entrevista.id_empleado,
                                                        id_tipo_entrevista: entrevista.id_tipo_entrevista,
                                                        id_postulante: entrevista.id_postulante
                                                    });
                                                }}
                                                className="manage-button"
                                            >
                                                Editar
                                            </button>
                                            <button 
                                                onClick={() => handleDelete(entrevista.id_entrevista)}
                                                className="delete-button"
                                            >
                                                Eliminar
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    ) : (
                        <p>No hay entrevistas registradas</p>
                    )}
                </div>

                {/* Modal para crear/editar entrevista */}
                {(showCreateModal || showEditModal) && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>{showEditModal ? 'Editar' : 'Nueva'} Entrevista</h2>
                            <form onSubmit={showEditModal ? handleUpdate : handleCreate}>
                                <label>
                                    Fecha:
                                    <input
                                        type="date"
                                        value={formData.fecha}
                                        onChange={(e) => setFormData({...formData, fecha: e.target.value})}
                                        required
                                    />
                                </label>

                                <label>
                                    Estado:
                                    <select
                                        value={formData.estado}
                                        onChange={(e) => setFormData({...formData, estado: e.target.value})}
                                        required
                                    >
                                        <option value="Pendiente">Pendiente</option>
                                        <option value="Realizada">Realizada</option>
                                        <option value="Cancelada">Cancelada</option>
                                    </select>
                                </label>

                                <label>
                                    Empleado:
                                    <select
                                        value={formData.id_empleado}
                                        onChange={(e) => setFormData({
                                            ...formData,
                                            id_empleado: e.target.value ? parseInt(e.target.value) : null
                                        })}
                                        required
                                    >
                                        <option value="">Selecciona un empleado</option>
                                        {empleados.map(empleado => (
                                            <option key={empleado.id_empleado} value={empleado.id_empleado}>
                                                {empleado.nombre} {empleado.apellido}
                                            </option>
                                        ))}
                                    </select>
                                </label>

                                <label>
                                    Tipo de Entrevista:
                                    <select
                                        value={formData.id_tipo_entrevista}
                                        onChange={(e) => setFormData({
                                            ...formData,
                                            id_tipo_entrevista: e.target.value ? parseInt(e.target.value) : null
                                        })}
                                        required
                                    >
                                        <option value="">Selecciona un tipo</option>
                                        {tiposEntrevista.map((tipo) => (
                                            <option key={tipo.id} value={tipo.id}>
                                                {tipo.nombre}
                                            </option>
                                        ))}
                                    </select>
                                </label>

                                {/* Agregar más campos según sea necesario */}

                                <div className="modal-buttons">
                                    <button type="submit">
                                        {showEditModal ? 'Guardar' : 'Crear'}
                                    </button>
                                    <button 
                                        type="button" 
                                        onClick={() => {
                                            setShowCreateModal(false);
                                            setShowEditModal(false);
                                        }}
                                    >
                                        Cancelar
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                )}

                {/* Modal de detalles */}
                {showDetailsModal && entrevistaCompleta && (
                    <div className="modal-overlay">
                        <div className="modal modal-large">
                            <h2>Detalles de la Entrevista</h2>
                            <div className="entrevista-details">
                                <div className="info-section">
                                    <h3>Información General</h3>
                                    <p><strong>Fecha:</strong> {new Date(entrevistaCompleta.fecha).toLocaleDateString()}</p>
                                    <p><strong>Estado:</strong> {entrevistaCompleta.estado}</p>
                                    <p><strong>Tipo:</strong> {entrevistaCompleta.tipo_entrevista?.nombre}</p>
                                </div>

                                <div className="info-section">
                                    <h3>Indicadores</h3>
                                    <button onClick={() => {
                                        setScores(entrevistaCompleta.indicadores || []);
                                        setShowScoresModal(true);
                                    }}>
                                        Editar Puntajes
                                    </button>
                                    {entrevistaCompleta.indicadores?.map(indicador => (
                                        <div key={indicador.id_indicador} className="indicador-item">
                                            <span>{indicador.nombre}</span>
                                            <span>{indicador.puntaje}/20</span>
                                        </div>
                                    ))}
                                </div>

                                <div className="info-section">
                                    <h3>Observaciones</h3>
                                    <button onClick={() => setShowObservationModal(true)}>
                                        Agregar Observación
                                    </button>
                                    {entrevistaCompleta.observaciones?.map(obs => (
                                        <div key={obs.id_observacion} className={`observacion-item ${obs.tipo}`}>
                                            <p><strong>{obs.tipo}:</strong> {obs.contenido}</p>
                                            <small>{new Date(obs.fecha).toLocaleString()}</small>
                                        </div>
                                    ))}
                                </div>
                            </div>

                            <div className="modal-buttons">
                                <button type="button" onClick={() => setShowDetailsModal(false)}>
                                    Cerrar
                                </button>
                            </div>
                        </div>
                    </div>
                )}

                {/* Modal para actualizar puntajes */}
                {showScoresModal && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>Editar Puntajes</h2>
                            <form onSubmit={handleUpdateScores}>
                                {scores.map((score, index) => (
                                    <div key={score.id_indicador} className="indicador-item">
                                        <span>{score.nombre}</span>
                                        <input
                                            type="number"
                                            min="0"
                                            max="20"
                                            value={score.puntaje}
                                            onChange={(e) => {
                                                const newScores = [...scores];
                                                newScores[index].puntaje = parseInt(e.target.value) || 0;
                                                setScores(newScores);
                                            }}
                                            required
                                        />
                                    </div>
                                ))}
                                <div className="modal-buttons">
                                    <button type="submit">Guardar</button>
                                    <button type="button" onClick={() => setShowScoresModal(false)}>Cancelar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                )}

                {/* Modal para agregar observación */}
                {showObservationModal && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>Agregar Observación</h2>
                            <form onSubmit={handleAddObservation}>
                                <label>
                                    Tipo:
                                    <select
                                        value={observacionForm.tipo}
                                        onChange={(e) => setObservacionForm({ ...observacionForm, tipo: e.target.value })}
                                        required
                                    >
                                        <option value="Regular">Regular</option>
                                        <option value="Importante">Importante</option>
                                        <option value="Crítica">Crítica</option>
                                    </select>
                                </label>
                                <label>
                                    Contenido:
                                    <textarea
                                        value={observacionForm.contenido}
                                        onChange={(e) => setObservacionForm({ ...observacionForm, contenido: e.target.value })}
                                        required
                                    />
                                </label>
                                <div className="modal-buttons">
                                    <button type="submit">Agregar</button>
                                    <button type="button" onClick={() => setShowObservationModal(false)}>Cancelar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                )}

                {/* Modales adicionales según sea necesario */}
            </div>
        </div>
    );
};

export default GestionarEntrevistas;