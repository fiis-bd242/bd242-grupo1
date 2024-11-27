import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./CrearPromocion.css";

function CrearPromocion() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    fecha_inicio: "",
    fecha_fin: "",
    dscto: "",
    precio_final: "",
    dscrip_promo: "",
    id_empleado: 1, // Suponiendo que el ID del empleado está fijo o lo obtienes de otro lugar
  });

  const [productosDisponibles, setProductosDisponibles] = useState([]);
  const [productosSeleccionados, setProductosSeleccionados] = useState([]);
  const [filtros, setFiltros] = useState({
    nombreProducto: "",
    empresa: "",
    nombreTipoProducto: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleFiltroChange = (e) => {
    const { name, value } = e.target;
    setFiltros({
      ...filtros,
      [name]: value,
    });
  };

  const fetchProductos = () => {
    const { nombreProducto, empresa, nombreTipoProducto } = filtros;
    axios
      .get("http://localhost:8082/asignarProducto/productosDisponibles", {
        params: { nombreProducto, empresa, nombreTipoProducto },
      })
      .then((response) => {
        setProductosDisponibles(response.data);
      })
      .catch((error) => console.log(error));
  };

  useEffect(() => {
    fetchProductos();
  }, [filtros]);

  const handleProductoSeleccionado = (codProducto) => {
    if (productosSeleccionados.includes(codProducto)) {
      setProductosSeleccionados(
        productosSeleccionados.filter((producto) => producto !== codProducto)
      );
    } else {
      setProductosSeleccionados([...productosSeleccionados, codProducto]);
    }
  };

  const handleCrearPromocion = () => {
    axios
      .post("http://localhost:8082/crearPromocion/nueva", formData)
      .then((response) => {
        alert("Promoción creada exitosamente");
        const codPromocion = response.data.codPromocion;
        asociarProductos(codPromocion);
      })
      .catch((error) => console.log(error));
  };

  const asociarProductos = (codPromocion) => {
    const productosParaAsignar = productosSeleccionados.map((codProducto) => ({
      cod_producto: codProducto,
    }));
    axios
      .post(
        `http://localhost:8082/crearPromocion/asociarProductos/${codPromocion}`,
        productosParaAsignar
      )
      .then(() => {
        alert("Productos asignados a la promoción");
        navigate("/"); // Redirigir a la página principal
      })
      .catch((error) => console.log(error));
  };

  return (
    <div>
      <h2>Crear Promoción</h2>
      <form>
        <div>
          <label>Fecha de inicio:</label>
          <input
            type="date"
            name="fecha_inicio"
            value={formData.fecha_inicio}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Fecha de fin:</label>
          <input
            type="date"
            name="fecha_fin"
            value={formData.fecha_fin}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Descuento:</label>
          <input
            type="number"
            name="dscto"
            value={formData.dscto}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Precio Final:</label>
          <input
            type="number"
            name="precio_final"
            value={formData.precio_final}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <label>Descripción:</label>
          <textarea
            name="dscrip_promo"
            value={formData.dscrip_promo}
            onChange={handleInputChange}
          />
        </div>
        <button type="button" onClick={handleCrearPromocion}>
          Crear Promoción
        </button>
      </form>
    </div>
  );
}

export default CrearPromocion;
