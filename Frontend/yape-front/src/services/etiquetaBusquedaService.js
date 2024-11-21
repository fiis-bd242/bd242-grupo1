import api from '../api';

export const buscarEtiqueta = async (idEmpleado, busqueda) => {
    try {
        const response = await api.get(`/api-asesor/${idEmpleado}/etiqueta/buscar`, {
            params: { busqueda },
        });
        return response.data;
    } catch (error) {
        console.error('Error al buscar etiquetas:', error);
        throw error;
    }
};
