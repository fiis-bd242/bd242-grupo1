import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./Promociones.css"

const Promociones = () => {
  const [promociones, setPromociones] = useState([]);
  const [modificaciones, setModificaciones] = useState([]);
  const [error, setError] = useState("");
  const [filtroSeller, setFiltroSeller] = useState("");
  const [filtroVigencia, setFiltroVigencia] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    obtenerPromociones();
  }, []);

  // Función para obtener las promociones
  const obtenerPromociones = async () => {
    try {
      const { data } = await axios.get("http://localhost:8082/gestionpromos/mostrarpromos");
      setPromociones(data.map((promo) => ({ ...promo, estadoPromo: !!promo.estadoPromo })));
    } catch (error) {
      manejarError("Error al cargar las promociones.", error);
    }
  };

  // Manejar cambios en el estado de una promoción
  const manejarCambioEstado = (indice, nuevoEstado) => {
    const actualizadas = [...promociones];
    actualizadas[indice].estadoPromo = nuevoEstado ? 1 : 0;
    setPromociones(actualizadas);

    actualizarModificaciones(actualizadas[indice]);
  };

  // Actualizar la lista de promociones modificadas
  const actualizarModificaciones = (promoModificada) => {
    setModificaciones((prev) =>
      prev.some((p) => p.codPromocion === promoModificada.codPromocion)
        ? prev.map((p) =>
            p.codPromocion === promoModificada.codPromocion ? promoModificada : p
          )
        : [...prev, promoModificada]
    );
  };

  // Guardar cambios en los estados de las promociones
  const guardarCambios = async () => {
    if (modificaciones.length === 0) return;
    try {
      await axios.put("http://localhost:8082/gestionpromos/actualizarEstados", modificaciones);
      alert("Estados actualizados correctamente.");
      setModificaciones([]);
      obtenerPromociones();
    } catch (error) {
      manejarError("Error al guardar cambios.", error);
    }
  };

  // Eliminar una promoción por su código
  const eliminarPromocion = async (codPromocion) => {
    if (window.confirm(`¿Está seguro de que quiere eliminar la promoción con código ${codPromocion}?`)) {
      try {
        await axios.delete(`http://localhost:8082/gestionpromos/eliminar/${codPromocion}`);
        alert("Promoción eliminada correctamente.");
        obtenerPromociones();
      } catch (error) {
        manejarError("Error al eliminar promoción.", error);
      }
    }
  };

  // Filtrar promociones según filtros de seller y vigencia
  const filtrarPromociones = async () => {
    try {
      const { data } = await axios.get("http://localhost:8082/gestionpromos/filtrar", {
        params: { seller: filtroSeller, vigencia: filtroVigencia },
      });
      setPromociones(data.map((promo) => ({ ...promo, estadoPromo: !!promo.estadoPromo })));
    } catch (error) {
      manejarError("Error al filtrar promociones.", error);
    }
  };

  // Redireccionar a la página de edición de promociones
  const irAEditarPromocion = (codPromocion) => {
    navigate(`/editar-promocion/${codPromocion}`);
  };

  // Manejo centralizado de errores
  const manejarError = (mensaje, error) => {
    setError(mensaje);
    console.error(error);
  };

  return (
    <div>
      <h1 className="titulo-principal">Gestión de Promociones</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}

      <div>
        <button onClick={() => navigate("/crear-promocion")}>Crear Promoción</button>
        <button onClick={() => navigate("/promociones-activas")}>Ver Promociones Activas</button>
        <button onClick={() => navigate("/productos-por-promocion")}>Asignación de Productos</button>
      </div>

      <div>
        <label>
          Filtro por Seller:
          <input
            type="text"
            value={filtroSeller}
            onChange={(e) => setFiltroSeller(e.target.value)}
          />
        </label>
        <label>
          Filtro por Vigencia:
          <input
            type="number"
            value={filtroVigencia}
            onChange={(e) => setFiltroVigencia(e.target.value)}
          />
        </label>
        <button onClick={filtrarPromociones}>Aplicar Filtros</button>
      </div>

      <table border="1">
        <thead>
          <tr>
            <th>Fecha de Inicio</th>
            <th>Código de Promoción</th>
            <th>Descuento</th>
            <th>Precio Final</th>
            <th>Seller</th>
            <th>Vigencia (Días)</th>
            <th>Estado</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {promociones.map((promo, index) => (
            <tr key={promo.codPromocion}>
              <td>{promo.fechaInicio || "N/A"}</td>
              <td>{promo.codPromocion || "N/A"}</td>
              <td>{promo.dscto || "N/A"}</td>
              <td>{promo.precioFinal || "N/A"}</td>
              <td>{promo.seller || "Sin Seller"}</td>
              <td>{promo.vigencia || "N/A"}</td>
              <td>
                <input
                  type="checkbox"
                  checked={promo.estadoPromo}
                  onChange={(e) => manejarCambioEstado(index, e.target.checked)}
                />
              </td>
              <td>
  <button 
    className="editar" 
    onClick={() => irAEditarPromocion(promo.codPromocion)}
  >
    ✏️
  </button>
  <button 
    className="eliminar" 
    onClick={() => eliminarPromocion(promo.codPromocion)}
  >
    🗑️
  </button>
</td>

            </tr>
          ))}
        </tbody>
      </table>

      {modificaciones.length > 0 && (
        <button onClick={guardarCambios} style={{ marginTop: "15px" }}>
          Guardar Cambios
        </button>
      )}
    </div>
  );
};

export default Promociones;
