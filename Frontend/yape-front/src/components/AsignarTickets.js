import React, { useState } from 'react';
import { asignarTicket } from '../services/ticketService';

const AsignarTickets = () => {
    const [idConversacion, setIdConversacion] = useState('');
    const [tipoTicket, setTipoTicket] = useState('');
    const [comentario, setComentario] = useState('');
    const idEmpleado = localStorage.getItem('idEmpleado'); // Simulando autenticación

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Se envían los datos al backend
            await asignarTicket(idEmpleado, {
                idConversacion,
                tipoTicket,
                comentario,
            });
            alert('Ticket asignado con éxito');
            // Reinicia el formulario tras asignar
            setIdConversacion('');
            setTipoTicket('');
            setComentario('');
        } catch (error) {
            console.error('Error al asignar el ticket:', error);
            alert('Ocurrió un error al asignar el ticket. Por favor, inténtelo nuevamente.');
        }
    };

    return (
        <div>
            <h1>Asignar Ticket</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="idConversacion">ID Conversación:</label>
                <input
                    id="idConversacion"
                    type="text"
                    value={idConversacion}
                    onChange={(e) => setIdConversacion(e.target.value)}
                    required
                />
                <br />
                <label htmlFor="tipoTicket">Tipo de Ticket:</label>
                <input
                    id="tipoTicket"
                    type="text"
                    value={tipoTicket}
                    onChange={(e) => setTipoTicket(e.target.value)}
                    required
                />
                <br />
                <label htmlFor="comentario">Comentario:</label>
                <textarea
                    id="comentario"
                    value={comentario}
                    onChange={(e) => setComentario(e.target.value)}
                    required
                />
                <br />
                <button type="submit">Asignar Ticket</button>
            </form>
        </div>
    );
};

export default AsignarTickets;
