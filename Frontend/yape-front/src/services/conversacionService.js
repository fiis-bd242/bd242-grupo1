import api from '../api';

export const obtenerHistorial = async (idEmpleado) => {
    try {
        const response = await api.get(`/api-asesor/${idEmpleado}/historial`);
        return response.data;
    } catch (error) {
        console.error('Error al obtener el historial:', error);
        throw error;
    }
};

export const obtenerMensajes = async (idEmpleado, idConv, idCliente) => {
    try {
        // Validar que los parámetros estén definidos
        if (!idEmpleado || !idConv || !idCliente) {
            console.error('Faltan parámetros necesarios:', { idEmpleado, idConv, idCliente });
            throw new Error('Parámetros inválidos o incompletos');
        }

        // Mostrar la URL generada
        const url = `/api-asesor/${idEmpleado}/${idConv}`;
        console.log('URL generada:', `${url}?idCliente=${idCliente}`);

        // Realizar la solicitud
        const response = await api.get(url, { params: { idCliente } });

        // Mostrar la respuesta recibida
        console.log('Respuesta del servidor:', response.data);

        return response.data;
    } catch (error) {
        // Log completo del error
        console.error('Error al obtener los mensajes:', error.response || error.message || error);

        // Re-emitir el error para que sea manejado en otros niveles
        throw error;
    }
};

