import React, { useState, useEffect } from 'react';
import { useParams, useSearchParams } from 'react-router-dom';
import { asignarTicket } from '../services/ticketService';
import BuscarEtiquetas from './BuscarEtiquetas'; // Importamos el componente de búsqueda
import '../styles/AsignarTickets.css';

const AsignarTickets = () => {
    const { idEmpleado: idEmpleadoParam } = useParams();
    const [searchParams] = useSearchParams();
    const idConv = searchParams.get('idConv');

    const [idEmpleado, setIdEmpleado] = useState(idEmpleadoParam || localStorage.getItem('idEmpleado') || '');
    const [codEtiqueta, setCodEtiqueta] = useState('');
    const [problemaIdent, setProblemaIdent] = useState('');
    const [comentario, setComentario] = useState('');
    const [mostrarBusqueda, setMostrarBusqueda] = useState(false);

    useEffect(() => {
        if (!idEmpleado) {
            console.error('idEmpleado no está definido. Verifica la URL o el almacenamiento local.');
            alert('Error: No se pudo obtener el ID del empleado. Verifica los datos.');
        } else {
            console.log(`ID del empleado: ${idEmpleado}`);
        }
    }, [idEmpleado]);

    const validateForm = () => {
        let isValid = true;

        if (!codEtiqueta) {
            alert('Debe seleccionar un código de etiqueta.');
            isValid = false;
        }

        if (!problemaIdent.trim()) {
            alert('El campo "Problema Identificado" no puede estar vacío.');
            isValid = false;
        }

        if (!comentario.trim()) {
            alert('El campo "Comentario" no puede estar vacío.');
            isValid = false;
        }

        return isValid;
    };

    const handleSeleccionEtiqueta = (etiqueta) => {
        setCodEtiqueta(etiqueta.codigoEtiqueta); // Asignamos el código de etiqueta seleccionado
        setMostrarBusqueda(false); // Cerramos la ventana emergente
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!validateForm()) {
            return;
        }

        try {
            const ticket = {
                codEtiqueta,
                problemaIdent,
                idConv,
                comentario,
            };

            await asignarTicket(idEmpleado, ticket);
            alert('Ticket asignado con éxito');
        } catch (error) {
            console.error('Error al asignar el ticket:', error);
            alert('Error al asignar el ticket');
        }
    };

    return (
        <div className="form-container">
            <h1>Asignar Ticket</h1>
            <form onSubmit={handleSubmit} className="ticket-form">
                <label htmlFor="codEtiqueta">Código de Etiqueta:</label>
                <input
                    id="codEtiqueta"
                    type="text"
                    value={codEtiqueta}
                    readOnly
                    placeholder="Seleccione una etiqueta"
                />
                <button
                    type="button"
                    className="search-button"
                    onClick={() => setMostrarBusqueda(true)}
                >
                    Buscar Etiqueta
                </button>
                <br />
                <label htmlFor="problemaIdent">Problema Identificado:</label>
                <textarea
                    id="problemaIdent"
                    value={problemaIdent}
                    onChange={(e) => setProblemaIdent(e.target.value)}
                    required
                />
                <br />
                <input type="hidden" id="idConv" value={idConv} readOnly />
                <br />
                <label htmlFor="comentario">Comentario:</label>
                <textarea
                    id="comentario"
                    value={comentario}
                    onChange={(e) => setComentario(e.target.value)}
                    required
                />
                <br />
                <button type="submit" className="submit-button">
                    Asignar Ticket
                </button>
            </form>

            {/* Ventana emergente para buscar etiquetas */}
            {mostrarBusqueda && (
                <div className="modal">
                    <div className="modal-content">
                        <span className="close" onClick={() => setMostrarBusqueda(false)}>
                            &times;
                        </span>
                        <h2>Buscar Etiqueta</h2>
                        <BuscarEtiquetas onSeleccion={handleSeleccionEtiqueta} />
                    </div>
                </div>
            )}
        </div>
    );
};

export default AsignarTickets;
