import React, { useState, useEffect } from 'react';
import { obtenerTicketsPorEmpleado } from '../services/ticketService'; // Esta línea debe ser correcta si 'obtenerTicketsPorEmpleado' está correctamente exportada
import { useNavigate } from 'react-router-dom';

const ListaTickets = () => {
    const [tickets, setTickets] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate(); // Hook para navegación

    // Obtener idEmpleado desde localStorage
    const idEmpleado = localStorage.getItem('idEmpleado'); // Esto es correcto

    // Función para cargar los tickets
    const cargarTickets = async () => {
        try {
            setLoading(true);
            const ticketsObtenidos = await obtenerTicketsPorEmpleado(idEmpleado);
            setTickets(ticketsObtenidos);
        } catch (err) {
            setError('Hubo un problema al cargar los tickets');
        } finally {
            setLoading(false);
        }
    };

    // Cargar los tickets cuando el componente se monta
    useEffect(() => {
        if (idEmpleado) {
            cargarTickets();
        } else {
            setError('No se encontró el idEmpleado');
            setLoading(false);
        }
    }, [idEmpleado]);

    // Manejar la redirección al actualizar un ticket
    const handleActualizarClick = (ticketId) => {
        navigate(`/menu/asesor/actualizar/ticket/${ticketId}`); // Usa el ticketId directamente en la URL
    };

    if (loading) {
        return <p>Cargando tickets...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <div>
            <h2>Lista de Tickets</h2>
            {tickets.length === 0 ? (
                <p>No hay tickets asignados para este empleado.</p>
            ) : (
                <ul>
                    {tickets.map((ticket) => (
                        <li key={ticket.idConv} style={{ marginBottom: '20px', border: '1px solid #ccc', padding: '10px', borderRadius: '5px' }}>
                            <p><strong>Código de Etiqueta:</strong> {ticket.codEtiqueta}</p>
                            <p><strong>Problema:</strong> {ticket.problemaIdent}</p>
                            <p><strong>ID Conversación:</strong> {ticket.idConv}</p>
                            <p><strong>Fecha Asignación:</strong> {new Date(ticket.fechaAsig).toLocaleDateString()}</p>
                            <p><strong>Comentario:</strong> {ticket.comentario}</p>
                            <p><strong>Estado:</strong> {ticket.nombreEstado}</p>
                            <button onClick={() => handleActualizarClick(ticket.idConv)} style={{ marginTop: '10px', padding: '5px 10px', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
                                Actualizar
                            </button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default ListaTickets;
