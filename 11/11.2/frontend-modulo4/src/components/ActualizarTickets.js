import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { obtenerTicketPorId, obtenerEstados, actualizarTicket } from '../services/ticketService';
import BuscarEtiquetas from './BuscarEtiquetas'; // Importar componente BuscarEtiquetas

const ActualizarTickets = () => {
    const { idTicket } = useParams(); // Obtener el idTicket desde los parámetros de la URL
    const [ticket, setTicket] = useState(null);
    const [estados, setEstados] = useState([]);
    const [codEtiqueta, setCodEtiqueta] = useState('');
    const [problemaIdent, setProblemaIdent] = useState('');
    const [nombreEstado, setNombreEstado] = useState('');
    const [comentario, setComentario] = useState('');
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [mostrarBusqueda, setMostrarBusqueda] = useState(false); // Estado para controlar la ventana emergente

    const idEmpleado = localStorage.getItem('idEmpleado'); // Obtener el idEmpleado desde localStorage

    useEffect(() => {
        const cargarDatos = async () => {
            setLoading(true);
            try {
                const ticketData = await obtenerTicketPorId(idEmpleado, idTicket);
                setTicket(ticketData);
                setCodEtiqueta(ticketData.codEtiqueta || '');
                setProblemaIdent(ticketData.problemaIdent || '');
                setNombreEstado(ticketData.nombreEstado || '');
                setComentario(ticketData.comentario || '');

                const estadosData = await obtenerEstados(idEmpleado);
                setEstados(estadosData);
            } catch (error) {
                console.error('Error al cargar datos:', error);
                setError('Hubo un error al cargar los datos. Por favor, inténtelo de nuevo.');
            } finally {
                setLoading(false);
            }
        };

        cargarDatos();
    }, [idEmpleado, idTicket]); // Solo cargar datos si idEmpleado o idTicket cambian

    const handleSeleccionEtiqueta = (etiqueta) => {
        setCodEtiqueta(etiqueta.codigoEtiqueta); // Asigna el código de etiqueta seleccionado
        setMostrarBusqueda(false); // Cierra la ventana emergente
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await actualizarTicket(idTicket, { // Llamada a la función actualizarTicket
                codEtiqueta,
                problemaIdent,
                nombreEstado,
                comentario,
            });
            alert('Ticket actualizado con éxito');
        } catch (error) {
            console.error('Error al actualizar el ticket:', error);
            alert('Error al actualizar el ticket. Por favor, inténtelo de nuevo.');
        }
    };

    if (loading) {
        return <p>Cargando datos...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <div>
            <h1>Actualizar Ticket: {idTicket}</h1>
            <form onSubmit={handleSubmit}>
                <label htmlFor="codEtiqueta">Código de Etiqueta:</label>
                <div style={{ display: 'flex', alignItems: 'center' }}>
                    <input
                        id="codEtiqueta"
                        type="text"
                        value={codEtiqueta}
                        readOnly
                        placeholder="Seleccione una etiqueta"
                        style={{ marginRight: '8px' }}
                    />
                    <button type="button" onClick={() => setMostrarBusqueda(true)}>
                        Buscar
                    </button>
                </div>
                <br />
                <label htmlFor="problemaIdent">Problema:</label>
                <input
                    id="problemaIdent"
                    type="text"
                    value={problemaIdent}
                    onChange={(e) => setProblemaIdent(e.target.value)}
                    required
                />
                <br />
                <label htmlFor="nombreEstado">Estado:</label>
                <select
                    id="nombreEstado"
                    value={nombreEstado}
                    onChange={(e) => setNombreEstado(e.target.value)}
                    required
                >
                    <option value="" disabled>Seleccionar estado</option>
                    {estados.map((estado, index) => (
                        <option key={index} value={estado}>
                            {estado}
                        </option>
                    ))}
                </select>
                <br />
                <label htmlFor="comentario">Comentario:</label>
                <textarea
                    id="comentario"
                    value={comentario}
                    onChange={(e) => setComentario(e.target.value)}
                    required
                />
                <br />
                <button type="submit">Actualizar Ticket</button>
            </form>

            {/* Ventana emergente para buscar etiquetas */}
            {mostrarBusqueda && (
                <div className="modal">
                    <div className="modal-content">
                        <span className="close" onClick={() => setMostrarBusqueda(false)}>&times;</span>
                        <BuscarEtiquetas onSeleccion={handleSeleccionEtiqueta} />
                    </div>
                </div>
            )}
        </div>
    );
};
export default ActualizarTickets;
