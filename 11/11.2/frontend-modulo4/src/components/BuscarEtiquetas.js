import React, { useState } from 'react';
import { buscarEtiqueta } from '../services/etiquetaBusquedaService';

const BuscarEtiquetas = ({ onSeleccion }) => {
    // Estados para los parámetros de búsqueda
    const [busqueda, setBusqueda] = useState('');
    const [tipologia, setTipologia] = useState('');
    const [funcionalidad, setFuncionalidad] = useState('');
    const [motivo, setMotivo] = useState('');
    const [resultados, setResultados] = useState([]);

    const idEmpleado = localStorage.getItem('idEmpleado');

    // Manejar la búsqueda con los nuevos parámetros
    const handleSearch = async () => {
        try {
            const data = await buscarEtiqueta(idEmpleado, busqueda, tipologia, funcionalidad, motivo);

            // Asegúrate de que la respuesta sea un arreglo
            if (Array.isArray(data)) {
                setResultados(data); // Actualizar los resultados
            } else {
                setResultados([]); // Respuesta no es válida, limpiar resultados
                console.error('La API no devolvió un arreglo:', data);
            }
        } catch (error) {
            alert('Error al buscar etiquetas');
            console.error('Error al buscar etiquetas:', error);
        }
    };


    const handleSeleccionar = (etiqueta) => {
        onSeleccion(etiqueta);
    };

    return (
        <div>
            <h1>Buscar Etiquetas</h1>
            <div>
                {/* Entrada principal de búsqueda */}
                <input
                    type="text"
                    value={busqueda}
                    onChange={(e) => setBusqueda(e.target.value)}
                    placeholder="Buscar descripción..."
                />
            </div>
            <div>
                {/* Filtro por tipología */}
                <input
                    type="text"
                    value={tipologia}
                    onChange={(e) => setTipologia(e.target.value)}
                    placeholder="Filtrar por tipología..."
                />
            </div>
            <div>
                {/* Filtro por funcionalidad */}
                <input
                    type="text"
                    value={funcionalidad}
                    onChange={(e) => setFuncionalidad(e.target.value)}
                    placeholder="Filtrar por funcionalidad..."
                />
            </div>
            <div>
                {/* Filtro por motivo */}
                <input
                    type="text"
                    value={motivo}
                    onChange={(e) => setMotivo(e.target.value)}
                    placeholder="Filtrar por motivo..."
                />
            </div>
            <button onClick={handleSearch}>Buscar</button>
            <ul>
                {resultados.map((etiqueta) => (
                    <li key={etiqueta.codigoEtiqueta}>
                        {etiqueta.descripcion} -{etiqueta.funcionalidad}-{etiqueta.tipologia}-{etiqueta.motivo} - {etiqueta.categoria}
                        <button onClick={() => handleSeleccionar(etiqueta)}>Seleccionar</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default BuscarEtiquetas;
