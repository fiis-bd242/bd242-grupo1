import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import "./TestDetalle.css";
import Sidebar from "./Sidebar"; // Sidebar ya creado previamente

const TestDetalle = () => {
    const { slug, id_test } = useParams(); // Recibimos el slug y el ID del test
    const [test, setTest] = useState(null);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchTest = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/test/${id_test}`);
                setTest(response.data);
            } catch (error) {
                console.error("Error al cargar el detalle del test:", error);
                setError("Error al cargar el detalle del test.");
            }
        };
        fetchTest();
    }, [id_test]);

    if (error) return <p className="error">{error}</p>;
    if (!test) return <p className="loading">Cargando...</p>;

    return (
        <div className="test-detalle-container">
            <Sidebar /> {/* Sidebar importado */}
            <main className="test-detalle-content">
                <h1>{test.capacitacion_nombre}</h1>
                <div className="test-info">
                    <h2>{test.test_nombre}</h2>
                    <p><strong>Descripción:</strong> {test.test_descripcion}</p>
                    <p><strong>Fecha de finalización:</strong> {new Date(test.test_fecha).toLocaleDateString()}</p>
                    <p><strong>Estado:</strong> {test.test_estado}</p>
                </div>
                <button 
                    className="btn"
                    onClick={() => navigate(`/mis-capacitaciones/${slug}/test/${id_test}/notas`)}
                >
                    Ver Notas
                </button>
                <button 
                    className="btn"
                    onClick={() => navigate(`/mis-capacitaciones/${slug}/test/${id_test}/prueba`)}
                >
                    Probar Test
                </button>
            </main>
        </div>
    );
};

export default TestDetalle;