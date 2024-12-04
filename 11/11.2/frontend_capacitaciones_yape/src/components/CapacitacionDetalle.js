import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import Sidebar from "../components/Sidebar"; // Importamos el Sidebar
import "./CapacitacionDetalle.css"; // Archivo CSS actualizado

const CapacitacionDetalle = () => {
    const { slug } = useParams();
    const [detalle, setDetalle] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchDetalle = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/mis-capacitaciones/${slug}`);
                setDetalle(response.data);
            } catch (error) {
                console.error("Error al cargar los detalles de la capacitación:", error);
                setError("Error al cargar los detalles de la capacitación.");
            }
        };
        fetchDetalle();
    }, [slug]);

    if (error) return <p>{error}</p>;
    if (!detalle) return <p>Cargando...</p>;

    const renderRecursosYTest = (modulo) => {
        const recursos = modulo.recursos.sort((a, b) => a.orden - b.orden);
        const test = modulo.tests.length > 0 ? [modulo.tests[0]] : [];
        const recursosYTests = [...recursos, ...test];

        return recursosYTests.map((item, index) => {
            const isRecurso = !!item.id_recurso;
            const isTest = !!item.id_test;

            return (
                <div key={index} className="recurso-test-item">
                    <div className="item-icon">
                        <i className={isRecurso ? "fas fa-book" : "fas fa-edit"}></i>
                    </div>
                    <div className="item-content">
                        <a
                            href={isRecurso ? item.contenido : `/mis-capacitaciones/${slug}/test/${item.id_test}`}
                            target={isRecurso ? "_blank" : "_self"}
                            rel="noopener noreferrer"
                        >
                            {item.nombre}
                        </a>
                    </div>
                </div>
            );
        });
    };

    return (
        <div className="capacitacion-detalle-layout">
            <Sidebar /> {/* Barra lateral */}
            <div className="capacitacion-detalle-content">
                <div className="header">
                    <h1>{detalle.capacitacion_nombre}</h1>
                    <p>{detalle.capacitacion_desc}</p>
                </div>

                <div className="modulos-container">
                    <h2>Inicio</h2>
                    {detalle.modulos.map((modulo) => (
                        <div key={modulo.id_modulo} className="modulo-card">
                            <div className="modulo-header">
                                <span>{modulo.modulo_nombre}</span>
                                <i className="fas fa-eye"></i>
                            </div>
                            <div className="modulo-content">{renderRecursosYTest(modulo)}</div>
                        </div>
                    ))}
                    <button className="add-module-btn">+ Añadir módulo</button>
                </div>
            </div>
        </div>
    );
};

export default CapacitacionDetalle;
