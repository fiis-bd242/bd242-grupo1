import api from '../api';
export const buscarEtiqueta = async (idEmpleado, busqueda, tipologia, funcionalidad, motivo) => {
    try {
        const response = await api.get(`/api-asesor/${idEmpleado}/etiqueta/buscar`, {
            params: {
                busqueda,
                tipologia,
                funcionalidad,
                motivo,
            },
        });

        // Si la respuesta no contiene data, asegúrate de retornar un arreglo vacío
        return response.data || [];
    } catch (error) {
        console.error('Error al buscar etiquetas:', error);
        throw error;
    }
};
