import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { obtenerHistorial } from '../services/conversacionService';
import { asignarTicket } from '../services/ticketService';
import '../styles/HistorialConversaciones.css';

const HistorialConversaciones = () => {
    const [conversaciones, setConversaciones] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const idEmpleado = localStorage.getItem('idEmpleado');

    useEffect(() => {
        const fetchHistorial = async () => {
            try {
                setLoading(true);
                setError(null);
                const data = await obtenerHistorial(idEmpleado);
                setConversaciones(data || []);
            } catch (error) {
                setError('Error al cargar el historial. Inténtalo de nuevo más tarde.');
            } finally {
                setLoading(false);
            }
        };

        if (idEmpleado) {
            fetchHistorial();
        } else {
            setError('No se encontró un usuario autenticado.');
            setLoading(false);
        }
    }, [idEmpleado]);

    const handleAsignar = async (idConv) => {
        const ticket = {
            codEtiqueta: "3", // Personalizar según tu aplicación
            problemaIdent: "problemaX",
            idConv: idConv,
            idEstado: 1,
            fechaAsig: new Date().toISOString(),
            comentario: "Comentario de ejemplo para el ticket",
        };
        try {
            await asignarTicket(idEmpleado, ticket);
            alert('Ticket asignado con éxito');
        } catch (error) {
            alert('Error al asignar el ticket');
        }
    };

    return (
        <div className="historial-container">
            <h1 className="historial-title">Historial de Conversaciones</h1>
            {loading && <p className="loading">Cargando historial...</p>}
            {error && <p className="error">{error}</p>}
            {!loading && !error && conversaciones.length === 0 && (
                <p className="no-data">No se encontraron conversaciones para este usuario.</p>
            )}
            {!loading && !error && conversaciones.length > 0 && (
                <ul className="conversation-list">
                    {conversaciones.map((conv) => (
                        <li className="conversation-item" key={conv.idConv}>
                            <div className="conversation-header">
                                <strong>Cliente:</strong>{' '}
                                <Link
                                    to={`/menu/asesor/conversaciones/${idEmpleado}/${conv.idConv}?idCliente=${conv.idCliente}`}
                                    className="client-link"
                                >
                                    {conv.nombreCompleto}
                                </Link>
                            </div>
                            <div className="conversation-details">
                                <strong>Estado:</strong> {conv.estadoConv} <br />
                                <strong>Ticket:</strong> {conv.codTicket}
                            </div>
                            <button
                                className="assign-button"
                                onClick={() => handleAsignar(conv.idConv)}
                            >
                                Asignar Ticket
                            </button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default HistorialConversaciones;
