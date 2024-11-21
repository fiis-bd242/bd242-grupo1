import React, { useState, useEffect } from 'react';
import { useParams, useSearchParams, useNavigate } from 'react-router-dom';
import { obtenerMensajes } from '../services/conversacionService';

const MensajesConversacion = () => {
    const { idEmpleado, idConv } = useParams(); // Usamos useParams para obtener idEmpleado y idConv
    const [searchParams] = useSearchParams(); // Usamos useSearchParams para obtener la cadena de consulta
    const idCliente = searchParams.get('idCliente'); // Obtenemos idCliente de la cadena de consulta

    const [mensajes, setMensajes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate(); // Hook para redirigir

    useEffect(() => {
        const fetchMensajes = async () => {
            try {
                setLoading(true);
                setError(null);
                const data = await obtenerMensajes(idEmpleado, idConv, idCliente);
                setMensajes(data || []);
            } catch (error) {
                setError('Error al cargar los mensajes. Inténtalo de nuevo más tarde.');
            } finally {
                setLoading(false);
            }
        };

        // Asegurarnos de que idEmpleado, idConv, e idCliente estén disponibles
        if (idEmpleado && idConv && idCliente) {
            fetchMensajes();
        } else {
            setError('Parámetros incompletos para cargar los mensajes.');
            setLoading(false);
        }
    }, [idEmpleado, idConv, idCliente]);

    // Función para redirigir a la página de asignación de tickets
    const handleAsignar = () => {
        navigate(`/menu/asesor/tickets/asignar?idEmpleado=${idEmpleado}&idConv=${idConv}&idCliente=${idCliente}`);
    };

    return (
        <div>
            <h1>Mensajes de la Conversación {idConv}</h1>
            {loading && <p>Cargando mensajes...</p>}
            {error && <p className="error">{error}</p>}
            {!loading && !error && mensajes.length === 0 && (
                <p>No se encontraron mensajes para esta conversación.</p>
            )}
            {!loading && !error && mensajes.length > 0 && (
                <div>
                    <ul>
                        {mensajes.map((mensaje) => (
                            <li key={mensaje.idMensaje}>
                                <strong>Contenido:</strong> {mensaje.contenido} <br />
                                <strong>Remitente Cliente:</strong> {mensaje.remitenteCliente} <br />
                                <strong>Remitente Asesor:</strong> {mensaje.remitenteAsesor}
                            </li>
                        ))}
                    </ul>
                    {/* Botón de asignación */}
                    <button onClick={handleAsignar}>Asignar Ticket</button>
                </div>
            )}
        </div>
    );
};

export default MensajesConversacion;
