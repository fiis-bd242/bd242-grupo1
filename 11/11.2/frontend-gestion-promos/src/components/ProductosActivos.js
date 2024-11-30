import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

const ProductosActivos = () => {
  const { codPromocion } = useParams(); // Obtén el parámetro desde la URL
  const [productos, setProductos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch de los productos relacionados a la promoción
    axios
      .get(`http://localhost:8082/productos-por-promocion/${codPromocion}`)
      .then((response) => {
        console.log("Productos activos recibidos:", response.data);
        setProductos(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error al obtener los productos activos:", error);
        setError(
          error.message || "No se pudieron cargar los productos para esta promoción."
        );
        setLoading(false);
      });
  }, [codPromocion]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div>
      <h2>Productos Activos para la Promoción {codPromocion}</h2>
      <table className="table">
        <thead>
          <tr>
            <th>Código de Producto</th>
            <th>Nombre del Producto</th>
          </tr>
        </thead>
        <tbody>
          {productos.length === 0 ? (
            <tr>
              <td colSpan="2">No hay productos asociados a esta promoción.</td>
            </tr>
          ) : (
            productos.map((producto) => (
              <tr key={producto.codProducto}>
                <td>{producto.codProducto}</td>
                <td>{producto.nombreProducto}</td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ProductosActivos;
