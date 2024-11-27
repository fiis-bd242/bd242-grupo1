import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const EditarPromocion = () => {
  const { cod_promocion } = useParams(); // cod_promocion será una cadena
  const navigate = useNavigate();

  // Estado inicial para la promoción
  const [promocion, setPromocion] = useState({
    fecha_inicio: "",
    fecha_fin: "",
    dscto: 0,
    precio_final: 0,
    dscrip_promo: "", // Campo ajustado
  });

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Cargar datos de la promoción desde el backend
  useEffect(() => {
    const codPromocionId = parseInt(cod_promocion, 10);
    if (isNaN(codPromocionId)) {
      setError("ID de promoción no válido.");
      setLoading(false);
      return;
    }

    axios
      .get(`http://localhost:8082/editar-promocion/${codPromocionId}`)
      .then((response) => {
        const data = response.data;

        // Aseguramos que los valores se asignen correctamente al estado
        setPromocion({
          fecha_inicio: data.fecha_inicio || "", // Mapear backend -> frontend
          fecha_fin: data.fecha_fin || "",
          dscto: data.dscto || 0,
          precio_final: data.precio_final || 0,
          dscrip_promo: data.dscrip_Promo || "",
        });
        setLoading(false);
      })
      .catch(() => {
        setError("No se pudo cargar la promoción.");
        setLoading(false);
      });
  }, [cod_promocion]);

  // Manejar cambios en los campos del formulario
  const handleChange = (e) => {
    const { name, value } = e.target;
    setPromocion({ ...promocion, [name]: value });
  };

  // Actualizar la promoción
  const handleActualizar = () => {
    const dataToSend = {
      codPromocion: parseInt(cod_promocion, 10), // Convertimos el ID a número
      fechaInicio: promocion.fecha_inicio, // Convertimos al formato esperado
      fechaFin: promocion.fecha_fin,
      dscto: parseFloat(promocion.dscto), // Aseguramos que sea un número flotante
      precioFinal: parseFloat(promocion.precio_final),
      dscrip_Promo: promocion.dscrip_promo, // Mapeamos al formato esperado
    };

    console.log("Datos enviados al backend:", dataToSend); // Depuración

    axios
      .put(`http://localhost:8082/editar-promocion/guardar`, dataToSend)
      .then(() => {
        alert("Promoción actualizada exitosamente.");
        navigate("/");
      })
      .catch((error) => {
        console.error("Error al actualizar la promoción:", error.response?.data || error);
        alert("Error al actualizar la promoción.");
      });
  };

  // Cancelar y volver al inicio
  const handleCancelar = () => {
    navigate("/");
  };

  if (loading) return <p>Cargando...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h2>Editar Promoción</h2>
      <form>
        <label>
          Fecha de Inicio:
          <input
            type="date"
            name="fecha_inicio"
            value={promocion.fecha_inicio}
            onChange={handleChange}
          />
        </label>
        <br />
        <label>
          Fecha de Fin:
          <input
            type="date"
            name="fecha_fin"
            value={promocion.fecha_fin}
            onChange={handleChange}
          />
        </label>
        <br />
        <label>
          Descuento:
          <input
            type="number"
            step="0.01"
            name="dscto"
            value={promocion.dscto}
            onChange={handleChange}
          />
        </label>
        <br />
        <label>
          Precio Final:
          <input
            type="number"
            name="precio_final"
            value={promocion.precio_final}
            onChange={handleChange}
          />
        </label>
        <br />
        <label>
          Descripción:
          <textarea
            name="dscrip_promo"
            value={promocion.dscrip_promo || ""} // Asegura un valor válido
            onChange={handleChange}
          />
        </label>
        <br />
        <button type="button" onClick={handleActualizar}>
          Actualizar Promoción
        </button>
        <button
          type="button"
          onClick={handleCancelar}
          style={{ marginLeft: "10px" }}
        >
          Cancelar
        </button>
      </form>
    </div>
  );
};

export default EditarPromocion;