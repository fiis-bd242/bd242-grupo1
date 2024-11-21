import React, { useState } from 'react';
import { insertarSugerencia } from '../services/analistaService';

const Sugerencias = () => {
    const [titulo, setTitulo] = useState('');
    const [descripcion, setDescripcion] = useState('');
    const idEmpleado = localStorage.getItem('idEmpleado'); // Simulando autenticación

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await insertarSugerencia(idEmpleado, { titulo, descripcion });
            alert('Sugerencia enviada con éxito');
            setTitulo('');
            setDescripcion('');
        } catch (error) {
            alert('Error al enviar la sugerencia');
        }
    };

    return (
        <div>
            <h1>Enviar Sugerencia</h1>
            <form onSubmit={handleSubmit}>
                <label>
                    Título:
                    <input
                        type="text"
                        value={titulo}
                        onChange={(e) => setTitulo(e.target.value)}
                        required
                    />
                </label>
                <br />
                <label>
                    Descripción:
                    <textarea
                        value={descripcion}
                        onChange={(e) => setDescripcion(e.target.value)}
                        required
                    />
                </label>
                <br />
                <button type="submit">Enviar</button>
            </form>
        </div>
    );
};

export default Sugerencias;
