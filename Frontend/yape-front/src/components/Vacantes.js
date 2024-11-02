// Vacantes.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/MenuPrincipal.css';
import '../styles/Vacante.css'; // Asegúrate de que el nombre del archivo CSS sea consistente

const Vacantes = () => {
  const navigate = useNavigate();
  const [time, setTime] = useState(new Date());
  const [vacantes, setVacantes] = useState([]);
  const [filteredVacantes, setFilteredVacantes] = useState([]);
  const [filter, setFilter] = useState('A'); // Inicializa en 'A' por defecto
  const [employee, setEmployee] = useState({ nombre: '', apellido: '' });
  const [puestos, setPuestos] = useState([]); // Estado para almacenar los puestos
  const [hiddenVacantes, setHiddenVacantes] = useState({});
  const [groupedPuestos, setGroupedPuestos] = useState({});

  // Estados para el modal
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [departamentos, setDepartamentos] = useState([]); // Estado para almacenar departamentos
  const [selectedDepartamento, setSelectedDepartamento] = useState('');
  const [filteredPuestos, setFilteredPuestos] = useState([]);
  const [formData, setFormData] = useState({
    comentario: '',
    id_puesto: '',
    fecha_fin: '',
    cantidad: 1  // Add default value for cantidad
  });

  // Add these states near the top of the component
  const [selectedVacante, setSelectedVacante] = useState(null);
  const [showPopup, setShowPopup] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [editFormData, setEditFormData] = useState({
    comentario: '',
    id_puesto: '',
    fecha_fin: '',
    cantidad: 1,
    estado: ''
  });

  // Añadir nuevos estados al inicio del componente
  const [showConvocatoriasModal, setShowConvocatoriasModal] = useState(false);
  const [convocatorias, setConvocatorias] = useState([]);
  const [showConvocatoriaEditModal, setShowConvocatoriaEditModal] = useState(false);
  const [selectedConvocatoria, setSelectedConvocatoria] = useState(null);
  const [convocatoriaFormData, setConvocatoriaFormData] = useState({
    medio_publicacion: '',
    fecha_inicio: new Date().toISOString().split('T')[0],
    fecha_fin: '',
    estado: 'Abierta' // Valor por defecto que coincide con la DB
  });

  // Agregar nuevo estado para almacenar la información de si una vacante se puede eliminar
  const [deletableVacantes, setDeletableVacantes] = useState({});

  // Añadir nuevo estado para almacenar las funciones de los puestos
  const [puestosFunciones, setPuestosFunciones] = useState({});

  // Nuevo estado para almacenar los postulantes
  const [postulantes, setPostulantes] = useState([]);
  const [showPostulantesModal, setShowPostulantesModal] = useState(false);

  useEffect(() => {
    const timer = setInterval(() => {
      setTime(new Date());
    }, 1000);

    const fetchVacantes = async () => {
      try {
        const response = await fetch('http://localhost:8080/vacantes');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setVacantes(data);

        // Verificar cada vacante directamente con la información que ya tenemos
        const deletableStatus = {};
        data.forEach(vacante => {
          deletableStatus[vacante.id_vacante] = checkDeletableVacante(vacante);
        });
        console.log('Deletable status:', deletableStatus); // Para debugging
        setDeletableVacantes(deletableStatus);
      } catch (error) {
        console.error('Error fetching vacantes:', error);
      }
    };

    const fetchPuestos = async () => {
      try {
        const response = await fetch('http://localhost:8080/puestos');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setPuestos(data);
      } catch (error) {
        console.error('Error fetching puestos:', error);
      }
    };

    const fetchDepartamentos = async () => {
      try {
        const response = await fetch('http://localhost:8080/departamentos/1/organigrama');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        // Extraer todos los subDepartamentos excepto el primero
        const extractedDepartamentos = [];
        const traverseDepartamentos = (dept) => {
          if (dept.id_departamento !== 1) {
            extractedDepartamentos.push({ id_departamento: dept.id_departamento, descripcion: dept.descripcion });
          }
          dept.subDepartamentos.forEach(subDept => traverseDepartamentos(subDept));
        };
        traverseDepartamentos(data);
        setDepartamentos(extractedDepartamentos);
      } catch (error) {
        console.error('Error fetching departamentos:', error);
      }
    };

    const fetchEmployee = async () => {
      try {
        const response = await fetch('http://localhost:8080/empleados/1');
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setEmployee(data);
      } catch (error) {
        console.error('Error fetching employee:', error);
      }
    };

    fetchVacantes();
    fetchPuestos();
    fetchDepartamentos();
    fetchEmployee();

    return () => clearInterval(timer);
  }, []);

  useEffect(() => {
    let estadoFilter = '';
    switch (filter) {
      case 'A':
        estadoFilter = 'Abierta';
        break;
      case 'C':
        estadoFilter = 'Cerrada';
        break;
      case 'V':
        estadoFilter = 'Vencida';
        break;
      default:
        estadoFilter = 'Abierta';
    }

    const newFilteredVacantes = vacantes.filter(
      (vacante) => vacante.estado === estadoFilter
    );
    setFilteredVacantes(newFilteredVacantes);
  }, [filter, vacantes]);

  useEffect(() => {
    const grouped = filteredVacantes.reduce((acc, vacante) => {
      const puestoId = vacante.id_puesto.toString();
      if (!acc[puestoId]) {
        acc[puestoId] = [];
      }
      acc[puestoId].push(vacante);
      return acc;
    }, {});
    setGroupedPuestos(grouped);
  }, [filteredVacantes]);

  const handleLogout = () => {
    console.log('Cerrando sesión');
    // Implementar lógica de logout
  };

  const getNombrePuesto = (id_puesto) => {
    const puesto = puestos.find((p) => p.id_puesto === Number(id_puesto));
    return puesto ? puesto.nombre : 'Puesto desconocido';
  };

  const cycleFilter = () => {
    setFilter((prevFilter) => {
      if (prevFilter === 'A') return 'C';
      if (prevFilter === 'C') return 'V';
      return 'A';
    });
  };

  const getFilterText = () => {
    switch (filter) {
      case 'A':
        return 'A - Abiertas';
      case 'C':
        return 'C - Cerradas';
      case 'V':
        return 'V - Vencidas';
      default:
        return 'A - Abiertas';
    }
  };

  const toggleHidden = (id_puesto) => {
    setHiddenVacantes((prev) => ({ ...prev, [id_puesto]: !prev[id_puesto] }));
  };

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => {
    setIsModalOpen(false);
    setFormData({
      comentario: '',
      id_puesto: '',
      fecha_fin: '',
      cantidad: 1
    });
    setSelectedDepartamento('');
    setFilteredPuestos([]);
  };

  const handleDepartamentoChange = (e) => {
    const deptId = e.target.value;
    setSelectedDepartamento(deptId);
    if (deptId) {
      const puestosFiltrados = puestos.filter(p => p.id_departamento.toString() === deptId);
      setFilteredPuestos(puestosFiltrados);
    } else {
      setFilteredPuestos([]);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const today = new Date().toISOString().split('T')[0];
        
    if (formData.fecha_fin < today) {
      alert('La fecha de fin debe ser posterior a la fecha actual');
      return;
    }

    const nuevoFormData = {
      estado: 'Abierta',
      fecha_inicio: today,
      fecha_fin: formData.fecha_fin,
      comentario: formData.comentario,
      id_puesto: parseInt(formData.id_puesto),
      cantidad: parseInt(formData.cantidad) // Add cantidad to the request
    };

    try {
      // Create new vacancy
      const response = await fetch('http://localhost:8080/vacantes', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(nuevoFormData),
      });

      await response.json();
      
      // Reset form states
      setFormData({
        comentario: '',
        id_puesto: '',
        fecha_fin: '',
        cantidad: 1
      });
      setIsModalOpen(false);
      setSelectedDepartamento('');
      setFilteredPuestos([]);

      // Fetch updated vacantes
      const vacantesResponse = await fetch('http://localhost:8080/vacantes');
      const updatedVacantes = await vacantesResponse.json();
      setVacantes(updatedVacantes);
      
      // Show success message
      alert('Vacante creada exitosamente');

    } catch (error) {
      console.error('Error:', error);
      alert('Error al crear la vacante');
    }
  };

  // Add these handlers
  const handleVacanteClick = (vacante) => {
    setSelectedVacante(vacante);
    setShowPopup(true);
  };

  const handleClosePopup = () => {
    setShowPopup(false);
    setSelectedVacante(null);
  };

  const handleEditClick = () => {
    setEditFormData({
      comentario: selectedVacante.comentario,
      id_puesto: selectedVacante.id_puesto,
      fecha_fin: selectedVacante.fecha_fin.split('T')[0],
      cantidad: selectedVacante.cantidad,
      estado: selectedVacante.estado
    });
    setShowEditModal(true);
    setShowPopup(false);
  };

  const handleEditSubmit = async (e) => {
    e.preventDefault();
    const today = new Date().toISOString().split('T')[0];
        
    if (editFormData.fecha_fin < today) {
      alert('La fecha de fin debe ser posterior a la fecha actual');
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/vacantes/${selectedVacante.id_vacante}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          ...selectedVacante,
          ...editFormData,
          id_puesto: parseInt(editFormData.id_puesto),
          cantidad: parseInt(editFormData.cantidad)
        }),
      });

      if (response.ok) {
        // Actualizar la lista de vacantes
        const vacantesResponse = await fetch('http://localhost:8080/vacantes');
        const updatedVacantes = await vacantesResponse.json();
        setVacantes(updatedVacantes);
        
        setShowEditModal(false);
        setSelectedVacante(null);
        alert('Vacante actualizada exitosamente');
      }
    } catch (error) {
      console.error('Error:', error);
      alert('Error al actualizar la vacante');
    }
  };

  const handleBackToPopup = () => {
    setShowEditModal(false);
    setShowPopup(true);
  };

  // Agregar esta nueva función después de handleEditSubmit
  const handleDelete = async (e, vacanteId) => {
    e.stopPropagation(); // Prevenir que se abra el popup al hacer click en eliminar
    if (window.confirm('¿Está seguro que desea eliminar esta vacante?')) {
      try {
        const response = await fetch(`http://localhost:8080/vacantes/${vacanteId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
          },
        });

        if (response.ok) {
          // Actualizar la lista de vacantes
          const vacantesResponse = await fetch('http://localhost:8080/vacantes');
          const updatedVacantes = await vacantesResponse.json();
          setVacantes(updatedVacantes);
          alert('Vacante eliminada exitosamente');
        }
      } catch (error) {
        console.error('Error:', error);
        alert('Error al eliminar la vacante');
      }
    }
  };

  // Añadir nuevas funciones para manejar convocatorias
  const handleVerConvocatorias = async () => {
    // Usar las convocatorias que ya vienen en la vacante seleccionada
    setConvocatorias(selectedVacante.convocatorias);
    await fetchPuestoFunciones(selectedVacante.id_puesto);
    setShowConvocatoriasModal(true);
    setShowPopup(false);
  };

  const handleCreateConvocatoria = () => {
    setConvocatoriaFormData({
      medio_publicacion: '',
      fecha_inicio: new Date().toISOString().split('T')[0],
      fecha_fin: '',
      estado: 'Abierta'
    });
    setSelectedConvocatoria(null);
    setShowConvocatoriaEditModal(true);
  };

  const handleEditConvocatoria = (convocatoria) => {
    setConvocatoriaFormData({
      medio_publicacion: convocatoria.medio_publicacion,
      fecha_inicio: convocatoria.fecha_inicio.split('T')[0],
      fecha_fin: convocatoria.fecha_fin.split('T')[0],
      estado: convocatoria.estado
    });
    setSelectedConvocatoria(convocatoria);
    setShowConvocatoriaEditModal(true);
  };

  const handleDeleteConvocatoria = async (id) => {
    if (window.confirm('¿Está seguro que desea eliminar esta convocatoria?')) {
      try {
        const response = await fetch(`http://localhost:8080/vacantes/convocatorias/${id}`, {
          method: 'DELETE'
        });

        if (response.ok) {
          // Actualizar la lista de convocatorias directamente desde la vacante
          const vacantesResponse = await fetch('http://localhost:8080/vacantes');
          const updatedVacantes = await vacantesResponse.json();
          const updatedVacante = updatedVacantes.find(v => v.id_vacante === selectedVacante.id_vacante);
          setConvocatorias(updatedVacante.convocatorias);
          alert('Convocatoria eliminada exitosamente');
        }
      } catch (error) {
        console.error('Error:', error);
        alert('Error al eliminar la convocatoria');
      }
    }
  };

  const handleConvocatoriaSubmit = async (e) => {
    e.preventDefault();
    const method = selectedConvocatoria ? 'PUT' : 'POST';
    const url = selectedConvocatoria 
      ? `http://localhost:8080/vacantes/convocatorias/${selectedConvocatoria.id_convocatoria}`
      : `http://localhost:8080/vacantes/${selectedVacante.id_vacante}/convocatorias`;

    const payload = {
      medio_publicacion: convocatoriaFormData.medio_publicacion,
      fecha_inicio: convocatoriaFormData.fecha_inicio,
      fecha_fin: convocatoriaFormData.fecha_fin,
      estado: convocatoriaFormData.estado // Mantener el estado tal cual está
    };

    if (selectedConvocatoria) {
      payload.id_vacante = selectedVacante.id_vacante;
    }

    try {
      console.log('Enviando convocatoria:', payload); // Para debugging
      const response = await fetch(url, {
        method,
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(`Error del servidor: ${errorData}`);
      }

      // Actualizar la lista de convocatorias
      const vacantesResponse = await fetch('http://localhost:8080/vacantes');
      const updatedVacantes = await vacantesResponse.json();
      const updatedVacante = updatedVacantes.find(v => v.id_vacante === selectedVacante.id_vacante);
      setConvocatorias(updatedVacante.convocatorias);
      
      setShowConvocatoriaEditModal(false);
      alert(selectedConvocatoria ? 'Convocatoria actualizada exitosamente' : 'Convocatoria creada exitosamente');
    } catch (error) {
      console.error('Error:', error);
      alert(`Error al guardar la convocatoria: ${error.message}`);
    }
  };

  // Agregar función para verificar si una vacante se puede eliminar
  const checkDeletableVacante = (vacante) => {
    // Una vacante es eliminable solo si no tiene postulantes ni convocatorias
    return vacante.postulantes.length === 0 && vacante.convocatorias.length === 0;
  };

  // Añadir función para obtener las funciones de un puesto
  const fetchPuestoFunciones = async (id_puesto) => {
    try {
      const response = await fetch(`http://localhost:8080/puestos/${id_puesto}`);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      setPuestosFunciones(prev => ({
        ...prev,
        [id_puesto]: data.funciones || []
      }));
    } catch (error) {
      console.error('Error fetching puesto funciones:', error);
      setPuestosFunciones(prev => ({
        ...prev,
        [id_puesto]: []
      }));
    }
  };

  // Función para obtener los postulantes de una vacante
  const fetchPostulantes = async (vacanteId) => {
    try {
      // Obtener los IDs de los postulantes para la vacante
      const responseIds = await fetch(`http://localhost:8080/vacantes/${vacanteId}/postulantes`);
      if (!responseIds.ok) throw new Error('Error al obtener los IDs de los postulantes');
      const postulantesIds = await responseIds.json(); // Asumiendo que devuelve un array de IDs

      // Obtener los detalles de cada postulante
      const postulantesPromises = postulantesIds.map(id => 
        fetch(`http://localhost:8080/api/postulantes/${id}`)
          .then(res => res.json())
      );
      const postulantesData = await Promise.all(postulantesPromises);

      // Ordenar los postulantes por puntaje general de forma descendente
      postulantesData.sort((a, b) => b.puntaje_general - a.puntaje_general);

      setPostulantes(postulantesData);
      setShowPostulantesModal(true);
    } catch (error) {
      console.error('Error al obtener los postulantes:', error);
      alert('Hubo un error al obtener los postulantes.');
    }
  };

  // Actualizar el manejador del botón "Postulantes"
  const handleVerPostulantes = (vacante) => {
    fetchPostulantes(vacante.id_vacante);
    setShowPopup(false);
  };

  return (
    <div className="layout-container">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="sidebar-content">
          <div className="logo-container">
            <img
              src="https://vectorseek.com/wp-content/uploads/2023/09/Yape-App-Logo-Vector.svg-.png"
              alt="Logo"
              className="logo"
              onClick={() => navigate('/menu')}
            />
          </div>

          <div className="user-profile">
            <div className="avatar"></div>
            <div className="welcome-text">¡Bienvenido/a!</div>
            <div className="user-name">{`${employee.apellido},`}</div>
            <div className="user-name">{employee.nombre}</div>
          </div>

          <nav className="nav-menu">
            <button
              className="nav-button"
              onClick={() => navigate('/menu/puestos')}
            >
              Puestos
            </button>
            <button
              className="nav-button"
              onClick={() => navigate('/menu/vacantes')}
            >
              Vacantes
            </button>
          </nav>
        </div>

        <button className="logout-button" onClick={handleLogout}>
          Cerrar sesión
        </button>
      </aside>

      <main className="main-content">
        <header className="header">
          <h1 className="page-title">Vacantes</h1>
          <div className="time">{time.toLocaleTimeString()}</div>
        </header>

        {/* Botón de filtro único */}
        <div className="filter-buttons">
          <button onClick={cycleFilter}>{getFilterText()}</button>
        </div>

        {/* Botón para crear vacante */}
        <div className="create-button">
          <button onClick={openModal}>Crear Vacante</button>
        </div>

        {/* Modal para crear vacante */}
        {isModalOpen && (
          <div className="modal-overlay">
            <div className="modal">
              <h2>Crear Nueva Vacante</h2>
              <form onSubmit={handleSubmit}>
                <label>
                  Estado:
                  <input type="text" value="Abierta" disabled />
                </label>
                <label>
                  Fecha Inicio:
                  <input
                    type="date"
                    value={new Date().toISOString().split('T')[0]}
                    disabled
                  />
                </label>
                <label>
                  Fecha Fin:
                  <input
                    type="date"
                    name="fecha_fin"
                    value={formData.fecha_fin}
                    onChange={handleChange}
                    required
                  />
                </label>
                <label>
                  Comentario:
                  <textarea
                    name="comentario"
                    value={formData.comentario}
                    onChange={handleChange}
                  ></textarea>
                </label>
                <label>
                  Departamento:
                  <select
                    value={selectedDepartamento}
                    onChange={handleDepartamentoChange}
                    required
                  >
                    <option value="">Seleccione un departamento</option>
                    {departamentos.map((departamento) => (
                      <option
                        key={departamento.id_departamento}
                        value={departamento.id_departamento}
                      >
                        {departamento.descripcion}
                      </option>
                    ))}
                  </select>
                </label>
                <label>
                  Puesto:
                  <select
                    name="id_puesto"
                    value={formData.id_puesto}
                    onChange={handleChange}
                    required
                    disabled={!selectedDepartamento}
                  >
                    <option value="">Seleccione un puesto</option>
                    {filteredPuestos.map((puesto) => (
                      <option key={puesto.id_puesto} value={puesto.id_puesto}>
                        {puesto.nombre}
                      </option>
                    ))}
                  </select>
                </label>
                <label>
                  Cantidad de vacantes:
                  <input
                    type="number"
                    name="cantidad"
                    value={formData.cantidad}
                    onChange={handleChange}
                    min="1"
                    required
                  />
                </label>
                <div className="modal-buttons">
                  <button type="submit">Crear</button>
                  <button type="button" onClick={closeModal}>
                    Cancelar
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}

        {/* Contenido principal de Vacantes con scroll */}
        <div className="vacantes-list">
          {Object.keys(groupedPuestos).map((id_puesto) => (
            <div 
              key={id_puesto} 
              className="vacante"
            >
              <h2>{getNombrePuesto(id_puesto)}</h2>
              {groupedPuestos[id_puesto]
                .slice(0, hiddenVacantes[id_puesto] ? undefined : 1)
                .map((vacante) => (
                  <div 
                    key={vacante.id_vacante} 
                    className="vacante-item"
                  >
                    <div 
                      className="vacante-content"
                      onClick={() => handleVacanteClick(vacante)}
                      style={{ cursor: 'pointer' }}
                    >
                      <p>Cantidad de vacantes: {vacante.cantidad}</p>
                      <p>Estado: {vacante.estado}</p>
                    </div>
                    {deletableVacantes[vacante.id_vacante] && (
                      <button 
                        className="delete-button"
                        onClick={(e) => handleDelete(e, vacante.id_vacante)}
                        title="Esta vacante se puede eliminar porque no tiene postulantes ni convocatorias"
                      >
                        <i className="fas fa-trash"></i>
                      </button>
                    )}
                  </div>
                ))}
              {groupedPuestos[id_puesto].length > 1 && (
                <button 
                  onClick={(e) => {
                    e.stopPropagation();
                    toggleHidden(id_puesto);
                  }}
                >
                  {hiddenVacantes[id_puesto] ? 'Mostrar Más' : 'Ocultar'}
                </button>
              )}
            </div>
          ))}
        </div>

        {/* Add the popup */}
        {showPopup && selectedVacante && (
          <>
            <div className="popup-overlay" onClick={handleClosePopup}></div>
            <div className="vacante-popup">
              <button className="close-popup" onClick={handleClosePopup}>&times;</button>
              <h2>{getNombrePuesto(selectedVacante.id_puesto)}</h2>
              <div className="popup-columns">
                <div className="popup-column">
                  <i className="fas fa-edit popup-icon"></i>
                  <h3>Editar</h3>
                  <button 
                    className="popup-button"
                    onClick={handleEditClick}
                  >
                    Editar Vacante
                  </button>
                </div>
                <div className="popup-column">
                  <i className="fas fa-users popup-icon"></i>
                  <h3>Postulantes</h3>
                  <button 
                    className="popup-button"
                    onClick={() => handleVerPostulantes(selectedVacante)}
                  >
                    Ver Postulantes
                  </button>
                </div>
                <div className="popup-column">
                  <i className="fas fa-bullhorn popup-icon"></i>
                  <h3>Convocatorias</h3>
                  <button 
                    className="popup-button"
                    onClick={handleVerConvocatorias}
                  >
                    Ver Convocatorias
                  </button>
                </div>
              </div>
            </div>
          </>
        )}

        {/* Añadir el modal de edición */}
        {showEditModal && selectedVacante && (
          <div className="modal-overlay">
            <div className="modal">
              <h2>Editar Vacante</h2>
              <form onSubmit={handleEditSubmit}>
                <label>
                  Estado:
                  <select
                    name="estado"
                    value={editFormData.estado}
                    onChange={(e) => setEditFormData({...editFormData, estado: e.target.value})}
                    required
                  >
                    <option value="Abierta">Abierta</option>
                    <option value="Cerrada">Cerrada</option>
                    <option value="Vencida">Vencida</option>
                  </select>
                </label>
                <label>
                  Fecha Inicio:
                  <input
                    type="date"
                    value={selectedVacante.fecha_inicio.split('T')[0]}
                    disabled
                  />
                </label>
                <label>
                  Fecha Fin:
                  <input
                    type="date"
                    name="fecha_fin"
                    value={editFormData.fecha_fin}
                    onChange={(e) => setEditFormData({...editFormData, fecha_fin: e.target.value})}
                    required
                  />
                </label>
                <label>
                  Comentario:
                  <textarea
                    name="comentario"
                    value={editFormData.comentario}
                    onChange={(e) => setEditFormData({...editFormData, comentario: e.target.value})}
                  ></textarea>
                </label>
                <label>
                  Puesto:
                  <select
                    name="id_puesto"
                    value={editFormData.id_puesto}
                    onChange={(e) => setEditFormData({...editFormData, id_puesto: e.target.value})}
                    required
                  >
                    <option value="">Seleccione un puesto</option>
                    {puestos.map((puesto) => (
                      <option key={puesto.id_puesto} value={puesto.id_puesto}>
                        {puesto.nombre}
                      </option>
                    ))}
                  </select>
                </label>
                <label>
                  Cantidad de vacantes:
                  <input
                    type="number"
                    name="cantidad"
                    value={editFormData.cantidad}
                    onChange={(e) => setEditFormData({...editFormData, cantidad: e.target.value})}
                    min="1"
                    required
                  />
                </label>
                <div className="modal-buttons">
                  <button type="button" onClick={handleBackToPopup}>
                    Volver
                  </button>
                  <button type="submit">Guardar</button>
                </div>
              </form>
            </div>
          </div>
        )}

        {/* Añadir los nuevos modales al final del componente antes del último </main> */}
        {showConvocatoriasModal && (
          <div className="modal-overlay">
            <div className="modal">
              <h2>Convocatorias</h2>
              <div className="convocatorias-list">
                {convocatorias.map((convocatoria) => {
                  const funciones = puestosFunciones[selectedVacante.id_puesto] || [];
                  
                  return (
                    <div key={convocatoria.id_convocatoria} className="convocatoria-item">
                      <h3>Medio de Publicación: {convocatoria.medio_publicacion}</h3>
                      <p>Fecha Inicio: {new Date(convocatoria.fecha_inicio).toLocaleDateString()}</p>
                      <p>Fecha Fin: {new Date(convocatoria.fecha_fin).toLocaleDateString()}</p>
                      <p>Estado: {convocatoria.estado}</p>
                      <div className="convocatoria-slogan">
                        <p>
                          Únete a Yape para el puesto de {getNombrePuesto(selectedVacante.id_puesto)}. 
                        </p>
                        <p>Las funciones para este rol son:</p>
                        <ul>
                          {funciones.map((funcion, index) => (
                            <li key={index}>
                              <strong>{funcion.nombre}:</strong> {funcion.descripcion}
                            </li>
                          ))}
                        </ul>
                        <p>Postula hasta el {new Date(convocatoria.fecha_fin).toLocaleDateString()}.</p>
                      </div>
                      <div className="convocatoria-buttons">
                        <button onClick={() => handleEditConvocatoria(convocatoria)}>
                          Editar
                        </button>
                        <button 
                          className="delete-button"
                          onClick={() => handleDeleteConvocatoria(convocatoria.id_convocatoria)}
                        >
                          Eliminar
                        </button>
                      </div>
                    </div>
                  );
                })}
              </div>
              <div className="modal-buttons">
                <button type="submit" onClick={handleCreateConvocatoria}>
                  Nueva Convocatoria
                </button>
                <button 
                  type="button" 
                  className="secondary-button"
                  onClick={() => {
                    setShowConvocatoriasModal(false);
                    setShowPopup(true);
                  }}
                >
                  Volver
                </button>
              </div>
            </div>
          </div>
        )}

        {showConvocatoriaEditModal && (
          <div className="modal-overlay">
            <div className="modal">
              <h2>{selectedConvocatoria ? 'Editar Convocatoria' : 'Nueva Convocatoria'}</h2>
              <form onSubmit={handleConvocatoriaSubmit}>
                <label>
                  Medio de Publicación:
                  <input
                    type="text"
                    value={convocatoriaFormData.medio_publicacion}
                    onChange={(e) => setConvocatoriaFormData({
                      ...convocatoriaFormData,
                      medio_publicacion: e.target.value
                    })}
                    required
                  />
                </label>
                <label>
                  Fecha Inicio:
                  <input
                    type="date"
                    value={convocatoriaFormData.fecha_inicio}
                    onChange={(e) => setConvocatoriaFormData({
                      ...convocatoriaFormData,
                      fecha_inicio: e.target.value
                    })}
                    required
                  />
                </label>
                <label>
                  Fecha Fin:
                  <input
                    type="date"
                    value={convocatoriaFormData.fecha_fin}
                    onChange={(e) => setConvocatoriaFormData({
                      ...convocatoriaFormData,
                      fecha_fin: e.target.value
                    })}
                    required
                  />
                </label>
                <label>
                  Estado:
                  <select
                    value={convocatoriaFormData.estado}
                    onChange={(e) => setConvocatoriaFormData({
                      ...convocatoriaFormData,
                      estado: e.target.value
                    })}
                    required
                  >
                    <option value="Abierta">Abierta</option>
                    <option value="Cerrada">Cerrada</option>
                    <option value="En proceso">En proceso</option>
                  </select>
                </label>
                <div className="modal-buttons">
                  <button type="button" onClick={() => setShowConvocatoriaEditModal(false)}>
                    Cancelar
                  </button>
                  <button type="submit">
                    {selectedConvocatoria ? 'Guardar' : 'Crear'}
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}

        {/* Agregar el modal para mostrar los postulantes */}
        {showPostulantesModal && (
          <div className="modal-overlay" onClick={() => setShowPostulantesModal(false)}>
            <div className="modal" onClick={(e) => e.stopPropagation()}>
              <h2>Postulantes</h2>
              <div className="postulantes-list">
                {postulantes.map(postulante => (
                  <button key={postulante.id_postulante} className="postulante-button">
                    {postulante.nombre}
                  </button>
                ))}
              </div>
              <div className="modal-buttons">
                <button type="button" onClick={() => setShowPostulantesModal(false)}>
                  Cerrar
                </button>
              </div>
            </div>
          </div>
        )}
      </main>
    </div>
  );
};

export default Vacantes;