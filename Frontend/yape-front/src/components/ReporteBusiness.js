import React, { useState } from 'react';
import { ReporteService } from '../services/ReporteService';

const ReporteBusiness = () => {
    const [reporte, setReporte] = useState(null);
    const [loading, setLoading] = useState(false);
    const [periodo, setPeriodo] = useState('mes'); // Default value 'mes'
    const [numeroPeriodo, setNumeroPeriodo] = useState(1); // Default value for the period number
    const [anio, setAnio] = useState(new Date().getFullYear()); // Default value current year
    const generarReporte = async () => {
        setLoading(true);
        try {
            const data = await ReporteService.generarReporteBusiness(anio, periodo);
            setReporte(data);
        } catch (error) {
            console.error("Error al generar el reporte:", error);
        }
        setLoading(false);
    };
    const handleObtenerReporte = async (tipoReporte) => {
        setLoading(true);
        try {
            let data;
            switch (tipoReporte) {
                case 'tiempo-resolucion':
                    data = await ReporteService.obtenerTiempoResolucion(periodo, numeroPeriodo);
                    break;
                case 'tipificacion-general':
                    data = await ReporteService.obtenerTipificacionGeneral(periodo, numeroPeriodo);
                    break;
                case 'tipificacion-detallada':
                    data = await ReporteService.obtenerTipificacionDetallada(periodo, numeroPeriodo);
                    break;
                case 'volumen-tickets':
                    data = await ReporteService.obtenerVolumenTickets(periodo, numeroPeriodo);
                    break;
                case 'clientes-activos':
                    data = await ReporteService.obtenerClientesActivos(periodo, numeroPeriodo);
                    break;
                case 'tickets-empleado':
                    data = await ReporteService.obtenerTicketsEmpleado(periodo, numeroPeriodo);
                    break;
                default:
                    break;
            }

            // Llamada a la nueva función generarReporte
            if (tipoReporte === 'generar') {
                data = await ReporteService.generarReporteBusiness(anio, periodo);  // Usamos los dos parámetros anio y periodo
            }

            setReporte(data);
        } catch (error) {
            console.error("Error al obtener el reporte:", error);
        }
        setLoading(false);
    };

    const getMaxNumeroPeriodo = () => {
        switch (periodo) {
            case 'mes':
                return 12;
            case 'trimestre':
                return 4;
            case 'semestre':
                return 2;
            default:
                return 1;
        }
    };

    const handlePeriodoChange = (e) => {
        const selectedPeriodo = e.target.value;
        setPeriodo(selectedPeriodo);
        if (selectedPeriodo === 'anio') {
            setNumeroPeriodo(anio); // Set numeroPeriodo to the selected year when 'anio' is chosen
        } else {
            setNumeroPeriodo(1); // Reset to the first period (e.g., month 1, quarter 1, etc.)
        }
    };

    return (
        <div className="reporte-container">
            <h2>Reportes Business</h2>

            {/* Aquí ya no es necesario el botón para generar el reporte */}
            <div className="filters">
                <label>
                    Año:
                    <input
                        type="number"
                        value={anio}
                        onChange={(e) => setAnio(e.target.value)}
                        placeholder="Año"
                        min="2020"
                    />
                </label>
                <label>
                    Periodo:
                    <select
                        value={periodo}
                        onChange={handlePeriodoChange}
                    >
                        <option value="mes">Mensual</option>
                        <option value="trimestre">Trimestral</option>
                        <option value="semestre">Semestral</option>
                        <option value="anio">Anual</option>
                    </select>
                </label>
                {periodo !== 'anio' && (
                    <label>
                        Número de Periodo:
                        <input
                            type="number"
                            value={numeroPeriodo}
                            onChange={(e) => setNumeroPeriodo(parseInt(e.target.value))}
                            placeholder="Número de periodo"
                            min="1"
                            max={getMaxNumeroPeriodo()}
                        />
                    </label>
                )}
            </div>

            {/* Links de los reportes */}
            <div className="report-links">
                <button onClick={generarReporte}>Generar Reporte</button>
                <button onClick={() => handleObtenerReporte('tiempo-resolucion')}>Tiempo de Resolución por Etiqueta</button>
                <button onClick={() => handleObtenerReporte('tipificacion-general')}>Tipificación General</button>
                <button onClick={() => handleObtenerReporte('tipificacion-detallada')}>Tipificación Detallada</button>
                <button onClick={() => handleObtenerReporte('volumen-tickets')}>Volumen de Tickets</button>
                <button onClick={() => handleObtenerReporte('clientes-activos')}>Clientes Activos</button>
                <button onClick={() => handleObtenerReporte('tickets-empleado')}>Tickets por Empleado</button>
            </div>

            {/* Cargando o mostrando el reporte */}
            {loading ? (
                <p>Cargando...</p>
            ) : (
                reporte && (
                    <div className="reporte-content">
                        <pre>{JSON.stringify(reporte, null, 2)}</pre> {/* Puedes usar un componente de tabla o gráficos si lo deseas */}
                    </div>
                )
            )}
        </div>
    );
};

export default ReporteBusiness;