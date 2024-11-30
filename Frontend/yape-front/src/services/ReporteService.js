// src/services/ReporteService.js
import api from '../api';

export const ReporteService = {
    generarReporteBusiness: async (anio, periodo) => {
        try {
            const response = await api.post('/api/reporte/business/generar', null, {
                params: { anio, periodo }
            });
            return response.data;
        } catch (error) {
            console.error("Error al generar el reporte:", error);
            throw error;
        }
    },
    obtenerTiempoResolucion: async (tipoPeriodo, numeroPeriodo) => {
        const response = await fetch(`/api/reporte/business/tiempo-resolucion?tipoPeriodo=${tipoPeriodo}&numeroPeriodo=${numeroPeriodo}`);
        if (!response.ok) throw new Error('Error en la solicitud');
        return response.json();
    },
    obtenerTipificacionGeneral: async (tipoPeriodo, numeroPeriodo) => {
        const response = await fetch(`/api/reporte/business/tipificacion-general?tipoPeriodo=${tipoPeriodo}&numeroPeriodo=${numeroPeriodo}`);
        if (!response.ok) throw new Error('Error en la solicitud');
        return response.json();
    },
    obtenerTipificacionDetallada: async (tipoPeriodo, numeroPeriodo) => {
        const response = await fetch(`/api/reporte/business/tipificacion-detallada?tipoPeriodo=${tipoPeriodo}&numeroPeriodo=${numeroPeriodo}`);
        if (!response.ok) throw new Error('Error en la solicitud');
        return response.json();
    },
    obtenerVolumenTickets: async (tipoPeriodo, numeroPeriodo) => {
        const response = await fetch(`/api/reporte/business/volumen-tickets?tipoPeriodo=${tipoPeriodo}&numeroPeriodo=${numeroPeriodo}`);
        if (!response.ok) throw new Error('Error en la solicitud');
        return response.json();
    },
    obtenerClientesActivos: async (tipoPeriodo, numeroPeriodo) => {
        const response = await fetch(`/api/reporte/business/clientes-activos?tipoPeriodo=${tipoPeriodo}&numeroPeriodo=${numeroPeriodo}`);
        if (!response.ok) throw new Error('Error en la solicitud');
        return response.json();
    },
    obtenerTicketsEmpleado: async (tipoPeriodo, numeroPeriodo) => {
        const response = await fetch(`/api/reporte/business/tickets-empleado?tipoPeriodo=${tipoPeriodo}&numeroPeriodo=${numeroPeriodo}`);
        if (!response.ok) throw new Error('Error en la solicitud');
        return response.json();
    }
};
