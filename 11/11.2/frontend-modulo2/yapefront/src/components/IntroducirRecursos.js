import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./IntroducirRecursos.css";

function IntroducirRecursos() {
  const location = useLocation();
  const navigate = useNavigate();

  // Obtener el codPrototipo de location.state o de sessionStorage
  const codPrototipo = location.state?.codPrototipo || sessionStorage.getItem("codPrototipo");
  const nombrePrototipo = location.state?.nombrePrototipo || "Nombre del Prototipo";

  const [imagen, setImagen] = useState(null);
  const [video, setVideo] = useState(null);
  const [guion, setGuion] = useState(null);
  const [analisis, setAnalisis] = useState(null);

  const handleFileChange = (e, setFile) => {
    setFile(e.target.files[0]);
  };

  const handleSave = async () => {
    if (!codPrototipo) {
      alert("El código del prototipo no está definido. Por favor, vuelve a la pantalla anterior.");
      return;
    }

    let success = true;
    const errores = [];

    if (imagen) {
      const result = await uploadFile(imagen, 1, "Imagen");
      if (!result) errores.push("Imagen");
      success = success && result;
    }
    if (video) {
      const result = await uploadFile(video, 2, "Video");
      if (!result) errores.push("Video");
      success = success && result;
    }
    if (guion) {
      const result = await uploadFile(guion, 3, "Guion");
      if (!result) errores.push("Guion");
      success = success && result;
    }
    if (analisis) {
      const result = await uploadFile(analisis, 4, "Análisis");
      if (!result) errores.push("Análisis");
      success = success && result;
    }

    if (success) {
      alert("Todos los archivos fueron subidos exitosamente.");
      navigate("/campana-detalles", { state: { codPrototipo, nombrePrototipo } });
    } else {
      alert(`Los siguientes archivos no pudieron subirse: ${errores.join(", ")}`);
    }
  };

  const uploadFile = async (file, idTipoRecurso, nombreRecurso) => {
    const formData = new FormData();
    formData.append("file", file);
    formData.append("id_tipo_recurso", idTipoRecurso);
    formData.append("cod_prototipo", codPrototipo);
    formData.append("nombre_recurso", nombreRecurso);

    try {
      const response = await fetch("http://host.docker.internal:8080/recursos/upload", {
        method: "POST",
        body: formData,
      });

      if (!response.ok) {
        console.error(`Error al subir el archivo ${nombreRecurso}: ${response.statusText}`);
        return false;
      }
      return true;
    } catch (error) {
      console.error(`Error en la subida del archivo ${nombreRecurso}:`, error);
      return false;
    }
  };

  return (
    <div className="introducir-recursos">
      <h15>Prototipo Publicitario</h15>
      <h2>Introducir Recursos</h2>
      <div className="resources-grid">
  <div className="resource-item">
    <input
      type="file"
      onChange={(e) => handleFileChange(e, setImagen)}
      style={{ display: "none" }}
      id="imagen-upload"
    />
    <label htmlFor="imagen-upload">Subir Imagen</label>
  </div>
  <div className="resource-item">
    <input
      type="file"
      onChange={(e) => handleFileChange(e, setVideo)}
      style={{ display: "none" }}
      id="video-upload"
    />
    <label htmlFor="video-upload">Subir Video</label>
  </div>
  <div className="resource-item">
    <input
      type="file"
      onChange={(e) => handleFileChange(e, setGuion)}
      style={{ display: "none" }}
      id="guion-upload"
    />
    <label htmlFor="guion-upload">Subir Guion</label>
  </div>
  <div className="resource-item">
    <input
      type="file"
      onChange={(e) => handleFileChange(e, setAnalisis)}
      style={{ display: "none" }}
      id="analisis-upload"
    />
    <label htmlFor="analisis-upload">Subir Análisis</label>
  </div>
</div>

      <button onClick={handleSave} className="upload-button">
        Guardar Recursos
      </button>
    </div>
  );
}

export default IntroducirRecursos;
