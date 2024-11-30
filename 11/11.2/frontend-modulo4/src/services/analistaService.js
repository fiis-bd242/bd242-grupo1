import api from '../api';
export const insertarSugerencia = async (idEmpleado, notificacion) => {
    try {
        const response = await api.post(`/api/analista/${idEmpleado}/sugerencia`, notificacion);
        return response.data;
    } catch (error) {
        console.error('Error inserting suggestion:', error);
        throw error;
    }
};