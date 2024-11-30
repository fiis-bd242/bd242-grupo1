import React, { useState, useEffect } from "react";
import axios from "axios";

const ProductosPorPromocion = () => {
  const [promociones, setPromociones] = useState([]);
  const [productos, setProductos] = useState([]);
  const [productoxSeller, setProductoxSeller] = useState([]);
  const [codPromocion, setCodPromocion] = useState("");
  const [codigosProductos, setCodigosProductos] = useState("");

  // Cargar datos de las tablas al iniciar el componente
  useEffect(() => {
    const fetchData = async () => {
      try {
        const [promosResponse, productsResponse, productoxSellerResponse] = await Promise.all([
          axios.get("http://localhost:8082/asignarProducto/promociones"),
          axios.get("http://localhost:8082/asignarProducto/productos"),
          axios.get("http://localhost:8082/asignarProducto/productoxseller"),
        ]);
        setPromociones(promosResponse.data);
        setProductos(productsResponse.data);
        setProductoxSeller(productoxSellerResponse.data);
      } catch (error) {
        console.error("Error al cargar datos:", error);
      }
    };
    fetchData();
  }, []);

  // Manejo de envío del formulario
  const handleSubmit = async () => {
    if (!codPromocion || !codigosProductos) {
      alert("Por favor, ingresa todos los datos requeridos.");
      return;
    }

    const productosArray = codigosProductos.split(",").map((num) => parseInt(num.trim()));

    try {
      await axios.post("http://localhost:8082/asignarProducto/asignar", {
        codPromocion: parseInt(codPromocion),
        codigosProductos: productosArray,
      });
      alert("Productos asignados correctamente.");
    } catch (error) {
      console.error("Error al asignar productos:", error);
      alert("Hubo un error al asignar los productos.");
    }
  };

  return (
    <div>
      <h1>Asignar Productos a Promociones</h1>

      {/* Tabla de Promociones */}
      <h2>Promociones</h2>
      <table border="1">
        <thead>
          <tr>
            <th>Código</th>
            <th>Descripción</th>
            <th>Estado</th>
          </tr>
        </thead>
        <tbody>
          {promociones.map((promo, index) => (
            <tr key={index}>
              <td>{promo[0]}</td>
              <td>{promo[1]}</td>
              <td>{promo[2] ? "Activo" : "Inactivo"}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Tabla de Productos */}
      <h2>Productos</h2>
      <table border="1">
        <thead>
          <tr>
            <th>Código</th>
            <th>Nombre</th>
          </tr>
        </thead>
        <tbody>
          {productos.map((product, index) => (
            <tr key={index}>
              <td>{product[0]}</td>
              <td>{product[1]}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Tabla de Producto x Seller */}
      <h2>Producto x Seller</h2>
      <table border="1">
        <thead>
          <tr>
            <th>Código PxS</th>
            <th>Código Producto</th>
            <th>Código Seller</th>
          </tr>
        </thead>
        <tbody>
          {productoxSeller.map((item, index) => (
            <tr key={index}>
              <td>{item[0]}</td>
              <td>{item[1]}</td>
              <td>{item[2]}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Formulario para asignar productos */}
      <h2>Asignar Productos</h2>
      <div>
        <label>Código de Promoción:</label>
        <input
          type="number"
          value={codPromocion}
          onChange={(e) => setCodPromocion(e.target.value)}
        />
      </div>
      <div>
        <label>Códigos de Productos (separados por coma):</label>
        <input
          type="text"
          value={codigosProductos}
          onChange={(e) => setCodigosProductos(e.target.value)}
        />
      </div>
      <button onClick={handleSubmit}>Asignar Productos</button>
    </div>
  );
};

export default ProductosPorPromocion;
