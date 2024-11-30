import api from '../api';

export const asignarTicket = async (idEmpleado, ticket) => {
    try {
        await api.post(`/api-asesor/${idEmpleado}/ticket/asignar`, ticket);
        console.log('Ticket asignado con éxito');
    } catch (error) {
        console.error('Error al asignar el ticket:', error);
        throw error; // Propaga el error para manejarlo en el componente
    }
};
export const obtenerTicketsPorEmpleado = async (idEmpleado) => {
    try {
        const url = `/api-asesor/${idEmpleado}/ticket/listar`;
        console.log('URL de solicitud:', url);  // Imprimir la URL para verificar
        const response = await api.get(url);
        return response.data;
    } catch (error) {
        console.error('Error al obtener los tickets:', error);
        throw error;
    }
};
export const obtenerTicketPorId = async (idEmpleado, idTicket) => {
    try {
        const response = await api.get(`/api-asesor/${idEmpleado}/ticket/${idTicket}`);
        return response.data;
    } catch (error) {
        console.error('Error al obtener el ticket:', error.response?.data || error.message);
        throw error;
    }
};


export const obtenerEstados = async (idEmpleado) => {
    try {
        const response = await api.get(`/api-asesor/${idEmpleado}/ticket/estados`);
        return response.data;
    } catch (error) {
        console.error('Error al obtener los estados:', error);
        throw error;
    }
};

// En ticketService.js
export const actualizarTicket = async (idTicket, ticketData) => {
    const idEmpleado = localStorage.getItem('idEmpleado'); // Obtener el ID del empleado desde el localStorage

    try {
        const response = await api.put(`/api-asesor/${idEmpleado}/ticket/actualizar/${idTicket}`, ticketData);

        if (response.status !== 200) {
            throw new Error('Error al actualizar el ticket');
        }

        return response.data;
    } catch (error) {
        console.error('Error al actualizar el ticket:', error);
        throw error;
    }
};


