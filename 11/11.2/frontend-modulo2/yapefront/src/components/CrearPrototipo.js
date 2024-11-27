import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useLocation } from "react-router-dom";
import "./CrearPrototipo.css";

function CrearPrototipo() {
  const navigate = useNavigate();
  const location = useLocation();
  const [idEmpleado, setIdEmpleado] = useState(null);
  const [formData, setFormData] = useState({
    nombre: "",
    presupuesto: "",
    objetivos: "",
    descripcion: "",
    edadRango: "",
    genero: "",
    ubicacion: "",
  });
  const [selectedChannels, setSelectedChannels] = useState([]);

  const channels = [
    { name: "Redes Sociales", cod_canal: 1 },
    { name: "Televisión", cod_canal: 2 },
    { name: "Radio", cod_canal: 3 },
    { name: "Medios Impresos", cod_canal: 4 },
    { name: "Publicidad Digital", cod_canal: 5 },
  ];

  useEffect(() => {
    // Recuperar idEmpleado de la navegación o de localStorage
    const empleadoId = location.state?.idEmpleado || localStorage.getItem("idEmpleado");
    if (!empleadoId) {
      alert("No se encontró el ID del empleado. Regresando al login.");
      navigate("/");
    } else {
      setIdEmpleado(empleadoId);
    }
  }, [location.state, navigate]);

  const handleChannelChange = (e) => {
    const { value, checked } = e.target;
    const channel = channels.find((ch) => ch.name === value);
    setSelectedChannels((prevChannels) =>
      checked && channel
        ? [...prevChannels, channel.cod_canal]
        : prevChannels.filter((cod) => cod !== channel.cod_canal)
    );
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({ ...prevData, [name]: value }));
  };

  const validateFields = () => {
    if (
      !formData.nombre ||
      !formData.presupuesto ||
      !formData.objetivos ||
      !formData.descripcion ||
      !formData.edadRango ||
      !formData.genero ||
      !formData.ubicacion
    ) {
      alert("Por favor, complete todos los campos.");
      return false;
    }

    if (selectedChannels.length === 0) {
      alert("Por favor, seleccione al menos un canal de difusión.");
      return false;
    }

    const presupuestoValue = parseFloat(formData.presupuesto);
    if (isNaN(presupuestoValue) || presupuestoValue <= 0) {
      alert("Por favor, ingrese un presupuesto válido.");
      return false;
    }

    return true;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateFields()) return;

    const payload = {
      nombre_prot: formData.nombre,
      prop_presupuesto: parseFloat(formData.presupuesto),
      objetivos: formData.objetivos,
      descripcion: formData.descripcion,
      edad_rango: formData.edadRango,
      genero: formData.genero,
      ubicacion: formData.ubicacion,
      cod_canales: selectedChannels,
      id_empleado: idEmpleado, // Usa el idEmpleado aquí
    };

    try {
      const response = await axios.post("http://host.docker.internal:8080/prototipos/crear", payload);

      if (response.status === 200) {
        const { codPrototipo } = response.data; // Obtener el código del prototipo creado
        alert("Prototipo creado exitosamente");

        // Redirigir a la vista para añadir recursos pasando el codPrototipo
        navigate("/introducir-recursos", { state: { codPrototipo } });
      } else {
        alert("Error: No se recibió el código del prototipo");
      }
    } catch (error) {
      console.error("Error al crear el prototipo:", error);
      alert("Error al crear el prototipo");
    }
  };

  return (
    <div className="crear-prototipo">
      <header66>
        <h1>Crear Publicidad</h1>
        <p>10:50 a.m.</p>
      </header66>
      <div className="campaign-specs">
        <div className="specifications-header">Especificaciones de campaña</div>
        <form onSubmit={handleSubmit}>
          <label>
            Nombre:
            <input
              type="text"
              name="nombre"
              value={formData.nombre}
              onChange={handleInputChange}
            />
          </label>
          <label>
            Canal de difusión:
            <div className="channel-list">
              {channels.map((channel) => (
                <div key={channel.cod_canal} className="channel-option">
                  <input
                    type="checkbox"
                    value={channel.name}
                    onChange={handleChannelChange}
                  />
                  <label>{channel.name}</label>
                </div>
              ))}
            </div>
          </label>
          <label>
            Audiencia:
            <div className="audience-fields">
              <input
                type="text"
                placeholder="Rango edades"
                name="edadRango"
                value={formData.edadRango}
                onChange={handleInputChange}
              />
              <input
                type="text"
                placeholder="Género"
                name="genero"
                value={formData.genero}
                onChange={handleInputChange}
              />
              <input
                type="text"
                placeholder="Distrito o Provincia"
                name="ubicacion"
                value={formData.ubicacion}
                onChange={handleInputChange}
              />
            </div>
          </label>
          <label>
            Presupuesto:
            <input
              type="number"
              name="presupuesto"
              value={formData.presupuesto}
              onChange={handleInputChange}
              step="0.01"
            />
          </label>
          <label>
            Objetivos de la campaña:
            <input
              type="text"
              name="objetivos"
              value={formData.objetivos}
              onChange={handleInputChange}
            />
          </label>
          <label>
            Descripción:
            <textarea
              name="descripcion"
              value={formData.descripcion}
              onChange={handleInputChange}
            ></textarea>
          </label>
          <button type="submit" className="next-button">
            Siguiente
          </button>
        </form>
      </div>
    </div>
  );
}

export default CrearPrototipo;
