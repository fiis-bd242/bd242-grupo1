import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const PromocionesActivas = () => {
  const [promociones, setPromociones] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch de las promociones activas
    axios
      .get("http://localhost:8082/promociones-activas")
      .then((response) => {
        console.log("Promociones activas recibidas:", response.data);
        setPromociones(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error al obtener las promociones activas:", error);
        setError(
          error.message || "No se pudieron cargar las promociones activas."
        );
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Promociones Activas</h2>
      <table className="table">
        <thead>
          <tr>
            <th>Fecha de Inicio</th>
            <th>Código de Promoción</th>
            <th>Precio Final</th>
            <th>Descuento</th>
            <th>Seller</th>
            <th>Vigencia</th>
            <th>Descripción</th>
            <th>Productos</th>
          </tr>
        </thead>
        <tbody>
          {promociones.length === 0 ? (
            <tr>
              <td colSpan="8">No hay promociones activas.</td>
            </tr>
          ) : (
            promociones.map((promo) => (
              <tr key={promo.codPromocion}>
                <td>{promo.fechaInicio}</td>
                <td>{promo.codPromocion}</td>
                <td>{promo.precioFinal}</td>
                <td>{promo.dscto}%</td>
                <td>{promo.seller}</td>
                <td>{promo.vigencia}</td>
                <td>{promo.descripcion}</td>
                <td>
                  <Link to={`/productos-activos/${promo.codPromocion}`}>
                    <button className="btn btn-primary">Ver Productos</button>
                  </Link>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default PromocionesActivas;
