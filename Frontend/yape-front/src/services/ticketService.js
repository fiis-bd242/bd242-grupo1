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