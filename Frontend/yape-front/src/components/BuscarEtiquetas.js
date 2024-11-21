import React, { useState } from 'react';
import { buscarEtiqueta } from '../services/etiquetaBusquedaService';

const BuscarEtiquetas = () => {
    const [busqueda, setBusqueda] = useState('');
    const [resultados, setResultados] = useState([]);
    const idEmpleado = localStorage.getItem('idEmpleado'); // Simulando autenticación

    const handleSearch = async () => {
        try {
            const data = await buscarEtiqueta(idEmpleado, busqueda);
            setResultados(data);
        } catch (error) {
            alert('Error al buscar etiquetas');
        }
    };

    return (
        <div>
            <h1>Buscar Etiquetas</h1>
            <input
                type="text"
                value={busqueda}
                onChange={(e) => setBusqueda(e.target.value)}
                placeholder="Buscar..."
            />
            <button onClick={handleSearch}>Buscar</button>
            <ul>
                {resultados.map((etiqueta) => (
                    <li key={etiqueta.id}>{etiqueta.nombre}</li>
                ))}
            </ul>
        </div>
    );
};

export default BuscarEtiquetas;
