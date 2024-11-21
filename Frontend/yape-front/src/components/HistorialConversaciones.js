import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { obtenerHistorial } from '../services/conversacionService';
import { asignarTicket } from '../services/ticketService';

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
            codEtiqueta: "3",           // Esto lo puedes personalizar según tu aplicación
            problemaIdent: "problemaX", // Igualmente, puedes cambiar este valor según la conversación
            idConv: idConv,
            idEstado: 1,                // Estado de ejemplo
            fechaAsig: new Date().toISOString(),  // Fecha actual
            comentario: "Comentario de ejemplo para el ticket", // Comentario de ejemplo
        };
        try {
            await asignarTicket(idEmpleado, ticket);
            alert('Ticket asignado con éxito');
            // Puedes actualizar la UI aquí si necesitas reflejar cambios en tiempo real
        } catch (error) {
            alert('Error al asignar el ticket');
        }
    };

    return (
        <div>
            <h1>Historial de Conversaciones</h1>
            {loading && <p>Cargando historial...</p>}
            {error && <p className="error">{error}</p>}
            {!loading && !error && conversaciones.length === 0 && (
                <p>No se encontraron conversaciones para este usuario.</p>
            )}
            {!loading && !error && conversaciones.length > 0 && (
                <ul>
                    {conversaciones.map((conv) => (
                        <li key={conv.idConv}>
                            <strong>Cliente:</strong>{' '}
                            <Link to={`/menu/asesor/conversaciones/${idEmpleado}/${conv.idConv}?idCliente=${conv.idCliente}`}>
                                {conv.nombreCompleto}
                            </Link>

                            <br />
                            <strong>Estado:</strong> {conv.estadoConv} <br />
                            <strong>Ticket:</strong> {conv.codTicket}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default HistorialConversaciones;
