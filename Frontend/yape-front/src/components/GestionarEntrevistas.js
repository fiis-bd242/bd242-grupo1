import React, { useState, useEffect, useCallback } from 'react'; // Add useCallback
import { useParams, useNavigate } from 'react-router-dom';
import '../styles/Vacante.css';

const GestionarEntrevistas = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [entrevistas, setEntrevistas] = useState([]);
    const [tiposEntrevista, setTiposEntrevista] = useState([]);
    const [empleados, setEmpleados] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const [isEditing, setIsEditing] = useState(false);
    const [currentEntrevista, setCurrentEntrevista] = useState(null);
    const [formData, setFormData] = useState({
        estado: 'Pendiente',
        fecha: '',
        id_postulante: id,
        id_empleado: '',
        id_tipo_entrevista: ''
    });
    const [showIndicadoresModal, setShowIndicadoresModal] = useState(false);
    const [currentIndicadores, setCurrentIndicadores] = useState([]);

    const fetchEntrevistas = useCallback(async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/postulantes/${id}/entrevistas`);
            if (!response.ok) throw new Error('Error al obtener entrevistas');
            const data = await response.json();
            setEntrevistas(data);
        } catch (error) {
            console.error('Error:', error);
            setError('Error al obtener las entrevistas');
        }
    }, [id]); // Add id as dependency

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                // Fetch entrevistas
                console.log('Fetching entrevistas for postulante:', id);
                const entrevistasRes = await fetch(`http://localhost:8080/api/postulantes/${id}/entrevistas`);
                if (!entrevistasRes.ok) {
                    const errorText = await entrevistasRes.text();
                    throw new Error(`Error al obtener entrevistas: ${errorText}`);
                }
                const entrevistasData = await entrevistasRes.json();
                console.log('Entrevistas recibidas:', entrevistasData);
                setEntrevistas(entrevistasData);

                // Fetch tipos de entrevista
                const tiposRes = await fetch('http://localhost:8080/api/tipos-entrevista');
                if (!tiposRes.ok) throw new Error('Error al obtener tipos de entrevista');
                const tiposData = await tiposRes.json();
                console.log('Tipos de entrevista recibidos:', tiposData);
                setTiposEntrevista(tiposData);

                // Fetch empleados
                const empleadosRes = await fetch('http://localhost:8080/api/empleados/all');
                if (!empleadosRes.ok) throw new Error('Error al obtener empleados');
                const empleadosData = await empleadosRes.json();
                console.log('Empleados recibidos:', empleadosData);
                setEmpleados(empleadosData);

                setError(null);
            } catch (err) {
                console.error('Error detallado:', err);
                setError(`Error al cargar los datos: ${err.message}`);
                setEntrevistas([]);
                setTiposEntrevista([]);
                setEmpleados([]);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [id]); // Solo depende de id, no de fetchEntrevistas

    const handleCreateOrEdit = async (e) => {
        e.preventDefault();
        try {
            const url = isEditing 
                ? `http://localhost:8080/api/entrevistas/${currentEntrevista.id_entrevista}`
                : 'http://localhost:8080/api/entrevistas';
            
            const response = await fetch(url, {
                method: isEditing ? 'PUT' : 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (!response.ok) {
                const errorData = await response.text();
                throw new Error(`Error del servidor: ${errorData}`);
            }
            
            await fetchEntrevistas();
            setShowModal(false);
            resetForm();
        } catch (error) {
            console.error('Error detallado:', error);
            alert(`Error al guardar la entrevista: ${error.message}`);
        }
    };

    const handleDelete = async (id_entrevista) => {
        if (!window.confirm('¿Está seguro que desea eliminar esta entrevista?')) return;
        
        try {
            const response = await fetch(`http://localhost:8080/api/entrevistas/${id_entrevista}`, {
                method: 'DELETE'
            });
            if (!response.ok) throw new Error('Error al eliminar la entrevista');
            await fetchEntrevistas();
        } catch (error) {
            console.error('Error:', error);
            alert('Error al eliminar la entrevista');
        }
    };

    const fetchIndicadores = async (entrevistaId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/entrevistas/${entrevistaId}/indicadores`);
            if (!response.ok) throw new Error('Error al obtener indicadores');
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error:', error);
            return [];
        }
    };

    const handleUpdateIndicadores = async (entrevistaId) => {
        try {
            const response = await fetch(`http://localhost:8080/api/entrevistas/${entrevistaId}/indicadores`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(currentIndicadores),
            });

            if (!response.ok) throw new Error('Error al actualizar indicadores');
            
            await fetchEntrevistas();
            setShowIndicadoresModal(false);
        } catch (error) {
            console.error('Error:', error);
            alert('Error al actualizar indicadores');
        }
    };

    const openEditModal = async (entrevista) => {
        setIsEditing(true);
        setCurrentEntrevista(entrevista);
        const indicadoresData = await fetchIndicadores(entrevista.id_entrevista);
        setCurrentIndicadores(indicadoresData);
        setFormData({
            estado: entrevista.estado,
            fecha: entrevista.fecha.split('T')[0],
            id_postulante: id,
            id_empleado: entrevista.id_empleado,
            id_tipo_entrevista: entrevista.id_tipo_entrevista
        });
        setShowModal(true);
    };

    const openCreateModal = () => {
        setIsEditing(false);
        setCurrentEntrevista(null);
        resetForm();
        setShowModal(true);
    };

    const resetForm = () => {
        setFormData({
            estado: 'Pendiente',
            fecha: '',
            id_postulante: id,
            id_empleado: '',
            id_tipo_entrevista: ''
        });
    };

    if (loading) return <div className="main-content">Cargando...</div>;
    if (error) return <div className="main-content">Error: {error}</div>;

    return (
        <div className="layout-container">
            <div className="main-content">
                <header className="header">
                    <h1 className="page-title">Gestionar Entrevistas</h1>
                    <button onClick={() => navigate(-1)} className="secondary-button">
                        Volver
                    </button>
                </header>

                <div className="content-section">
                    <button onClick={openCreateModal} className="create-button">
                        Nueva Entrevista
                    </button>

                    {entrevistas.length > 0 ? (
                        <table className="vacante-table">
                            <thead>
                                <tr>
                                    <th>Fecha</th>
                                    <th>Estado</th>
                                    <th>Tipo</th>
                                    <th>Empleado</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                {entrevistas.map(entrevista => (
                                    <tr key={entrevista.id_entrevista}>
                                        <td>{new Date(entrevista.fecha).toLocaleDateString()}</td>
                                        <td>{entrevista.estado}</td>
                                        <td>
                                            {tiposEntrevista.find(t => t.id_tipo_entrevista === entrevista.id_tipo_entrevista)?.nombre || 'N/A'}
                                        </td>
                                        <td>
                                            {empleados.find(e => e.id_empleado === entrevista.id_empleado)?.nombre || 'N/A'}
                                        </td>
                                        <td>
                                            <button onClick={() => openEditModal(entrevista)} className="manage-button">
                                                Editar
                                            </button>
                                            <button onClick={() => handleDelete(entrevista.id_entrevista)} className="delete-button">
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

                {showModal && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>{isEditing ? 'Editar' : 'Nueva'} Entrevista</h2>
                            <form onSubmit={handleCreateOrEdit}>
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
                                        <option value="Hecha">Hecha</option>
                                        <option value="Rechazada">Rechazada</option>
                                    </select>
                                </label>

                                <label>
                                    Tipo de Entrevista:
                                    <select
                                        value={formData.id_tipo_entrevista}
                                        onChange={(e) => setFormData({...formData, id_tipo_entrevista: e.target.value})}
                                        required
                                    >
                                        <option value="">Seleccione un tipo</option>
                                        {tiposEntrevista.map(tipo => (
                                            <option key={tipo.id_tipo_entrevista} value={tipo.id_tipo_entrevista}>
                                                {tipo.nombre}
                                            </option>
                                        ))}
                                    </select>
                                </label>

                                <label>
                                    Empleado:
                                    <select
                                        value={formData.id_empleado}
                                        onChange={(e) => setFormData({...formData, id_empleado: e.target.value})}
                                        required
                                    >
                                        <option value="">Seleccione un empleado</option>
                                        {empleados.map(empleado => (
                                            <option key={empleado.id_empleado} value={empleado.id_empleado}>
                                                {`${empleado.nombre} ${empleado.apellido}`}
                                            </option>
                                        ))}
                                    </select>
                                </label>

                                <div className="modal-buttons">
                                    <button type="submit">
                                        {isEditing ? 'Guardar' : 'Crear'}
                                    </button>
                                    <button type="button" onClick={() => setShowModal(false)}>
                                        Cancelar
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                )}

                {showIndicadoresModal && currentIndicadores.length > 0 && (
                    <div className="modal-overlay">
                        <div className="modal">
                            <h2>Editar Indicadores</h2>
                            <form onSubmit={(e) => {
                                e.preventDefault();
                                handleUpdateIndicadores(currentEntrevista.id_entrevista);
                            }}>
                                {currentIndicadores.map((indicador, index) => (
                                    <div key={indicador.id_indicador} className="indicador-item">
                                        <label>
                                            {indicador.nombre}:
                                            <input
                                                type="number"
                                                min="0"
                                                max="20"
                                                value={indicador.puntaje}
                                                onChange={(e) => {
                                                    const newIndicadores = [...currentIndicadores];
                                                    newIndicadores[index] = {
                                                        ...indicador,
                                                        puntaje: parseInt(e.target.value)
                                                    };
                                                    setCurrentIndicadores(newIndicadores);
                                                }}
                                            />
                                        </label>
                                    </div>
                                ))}
                                <div className="modal-buttons">
                                    <button type="submit">Guardar</button>
                                    <button type="button" onClick={() => setShowIndicadoresModal(false)}>
                                        Cancelar
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
};

export default GestionarEntrevistas;