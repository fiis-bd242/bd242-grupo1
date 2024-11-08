import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/MenuPrincipal.css';
import '../styles/Vacante.css'; // Asegúrate de que el nombre del archivo CSS sea consistente
import GestionarEntrevistas from './GestionarEntrevistas'; // Import the component
import axios from 'axios';

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
    cantidad: 1,  // Add default value for cantidad
    nombre: '',
    correo: '',
    telefono: '',
    idiomas: [],
    educaciones: [],
    habilidades: [],
    experienciasLaborales: []
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
    estado: '',
    id_postulante: '',
    nombre: '',
    correo: '',
    telefono: '',
    idiomas: [],
    educaciones: [],
    habilidades: [],
    experienciasLaborales: []
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

  // Añadir nuevo estado para almacenar las funciones de los puestos
  const [puestosFunciones, setPuestosFunciones] = useState({});

  // Nuevo estado para almacenar los postulantes
  const [postulantes, setPostulantes] = useState([]);
  const [showPostulantesModal, setShowPostulantesModal] = useState(false);

  // Nuevo estado para almacenar la vacante seleccionada para detalles
  const [selectedPostulante, setSelectedPostulante] = useState(null);
  const [showPostulanteDetailsModal, setShowPostulanteDetailsModal] = useState(false);

  // Nuevos estados para manejar crear y editar postulantes
  const [isCreatePostulanteModalOpen, setIsCreatePostulanteModalOpen] = useState(false);
  const [isEditPostulanteModalOpen, setIsEditPostulanteModalOpen] = useState(false);
  const [editPostulanteData, setEditPostulanteData] = useState({
    id_postulante: '',
    nombre: '',
    correo: '',
    telefono: '',
    idiomas: [],
    educaciones: [],
    habilidades: [],
    experienciasLaborales: []
  });

  // Estados para el popup de detalles del postulante
  const [loadingDetails, setLoadingDetails] = useState(false);

  // Añadir nuevo estado para el caché
  const [postulanteCache, setPostulanteCache] = useState({});
  const [loadingPostulantes, setLoadingPostulantes] = useState(false);

  // Añadir nuevo estado para entrevistas
  const [entrevistas, setEntrevistas] = useState([]);
  const [showEntrevistasModal, setShowEntrevistasModal] = useState(false);
  const [entrevistasDetails, setEntrevistasDetails] = useState([]);

  // Estado para almacenar la oferta laboral
  const [ofertaLaboral, setOfertaLaboral] = useState(null);

  // Añadir estos nuevos estados después de los estados existentes
  const [showEditOfertaModal, setShowEditOfertaModal] = useState(false);
  const [editOfertaData, setEditOfertaData] = useState({
    id_oferta: '',
    fecha_oferta: '',
    fecha_inicio_propuesta: '',
    link_documento_legal_sin_firma: '',
    link_documento_legal_con_firma: '',
    id_postulante: '',
    id_vacante: '',
    estado: 'Pendiente', // Add the estado field with default value
    beneficios: []
  });

  // Añadir este estado después de los otros estados
  const [beneficiosDisponibles] = useState([
    { id_beneficio: 1, descripcion: "Seguro de salud" },
    { id_beneficio: 2, descripcion: "Bono de desempeño" },
    { id_beneficio: 3, descripcion: "Vacaciones pagadas" },
    { id_beneficio: 4, descripcion: "Plan de pensiones" },
    { id_beneficio: 5, descripcion: "Capacitación y desarrollo" },
    { id_beneficio: 6, descripcion: "Horario flexible" },
    { id_beneficio: 7, descripcion: "Trabajo remoto" },
    { id_beneficio: 8, descripcion: "Descuentos en productos" },
    { id_beneficio: 9, descripcion: "Gimnasio en la empresa" },
    { id_beneficio: 10, descripcion: "Comedor subsidiado" }
  ]);

  // Añadir este nuevo estado después de los otros estados
  const [observacionesPostulante, setObservacionesPostulante] = useState([]);

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
      cantidad: 1,
      nombre: '',
      correo: '',
      telefono: '',
      idiomas: [],
      educaciones: [],
      habilidades: [],
      experienciasLaborales: []
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
        cantidad: 1,
        nombre: '',
        correo: '',
        telefono: '',
        idiomas: [],
        educaciones: [],
        habilidades: [],
        experienciasLaborales: []
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

        // Enviar email a todos los postulantes si el estado se cambia a 'Cerrada'
        if (editFormData.estado === 'Cerrada') {
          const postulantesResponse = await fetch(`http://localhost:8080/vacantes/${selectedVacante.id_vacante}/postulantes`);
          const postulantes = await postulantesResponse.json();

          for (const p of postulantes) {
            await handleSendEmail(p.id_postulante, p.correo);
          }
        }
        
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
    e.preventDefault();
    
    try {
      if (window.confirm('¿Está seguro que desea eliminar esta vacante?')) {
        const response = await fetch(`http://localhost:8080/vacantes/${vacanteId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
          },
        });
  
        if (response.ok) {
          // Actualizar la lista de vacantes eliminando la vacante eliminada
          setVacantes(prevVacantes => prevVacantes.filter(vacante => vacante.id_vacante !== vacanteId));
          alert('Vacante eliminada exitosamente');
        } else {
          alert('Error al eliminar la vacante');
        }
      }
    } catch (error) {
      console.error('Error al eliminar la vacante:', error);
      alert('No se pudo eliminar la vacante.');
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
    setLoadingPostulantes(true);
    try {
      // Get basic list of postulantes
      const response = await fetch(`http://localhost:8080/vacantes/${vacanteId}/postulantes`);
      if (!response.ok) throw new Error(`Error: ${response.statusText}`);
      
      const postulantesBasicos = await response.json();
      
      // Show basic list immediately
      const basicPostulantes = postulantesBasicos.map(p => ({
        ...p,
        idiomas: [],
        educaciones: [],
        habilidades: [],
        experienciasLaborales: []
      }));
      
      setPostulantes(basicPostulantes);
      setShowPostulantesModal(true);
      
      // Load details in background if cache doesn't exist
      if (!postulanteCache[vacanteId]) {
        loadPostulantesDetails(postulantesBasicos, vacanteId);
      }
    } catch (error) {
      console.error('Error al obtener postulantes:', error);
      alert('Error al cargar los postulantes');
    } finally {
      setLoadingPostulantes(false);
    }
  };

  // Nueva función para cargar detalles en segundo plano
  const loadPostulantesDetails = async (postulantesBasicos, vacanteId) => {
    setLoadingDetails(true);
    try {
      const postulantesDetallados = await Promise.all(
        postulantesBasicos.map(async (postulante) => {
          const [detallesResponse, entrevistasResponse] = await Promise.all([
            fetch(`http://localhost:8080/api/postulantes/${postulante.id_postulante}`),
            fetch(`http://localhost:8080/api/postulantes/${postulante.id_postulante}/entrevistas-hechas`)
          ]);

          const detalles = await detallesResponse.json();
          const entrevistas = entrevistasResponse.ok ? await entrevistasResponse.json() : [];

          return {
            ...detalles,
            entrevistas
          };
        })
      );

      // Ordenar postulantes
      const postulantesOrdenados = ordenarPostulantes(postulantesDetallados);
      
      // Actualizar caché y estado
      setPostulanteCache(prev => ({
        ...prev,
        [vacanteId]: postulantesOrdenados
      }));
      setPostulantes(postulantesOrdenados);
    } catch (error) {
      console.error('Error al cargar detalles:', error);
    } finally {
      setLoadingDetails(false);
    }
  };

  // Nueva función auxiliar para ordenar postulantes
  const ordenarPostulantes = (postulantes) => {
    const todosPuntajeCero = postulantes.every(p => !p.puntaje || p.puntaje === 0);
    
    return postulantes.sort((a, b) => {
      if (todosPuntajeCero) {
        return (b.entrevistas?.length || 0) - (a.entrevistas?.length || 0);
      }
      return (b.puntaje || 0) - (a.puntaje || 0);
    });
  };

  // Actualizar el manejador del botón "Postulantes"
  const handleVerPostulantes = (vacante) => {
    fetchPostulantes(vacante.id_vacante);
    setShowPopup(false);
  };

  const closePostulanteDetailsModal = () => {
    setShowPostulanteDetailsModal(false);
  };

  // Funciones para abrir y cerrar modales
  const openCreatePostulanteModal = () => setIsCreatePostulanteModalOpen(true);
  const closeCreatePostulanteModal = () => setIsCreatePostulanteModalOpen(false);

  const openEditPostulanteModal = (postulante) => {
    setEditPostulanteData(postulante);
    setIsEditPostulanteModalOpen(true);
  };
  const closeEditPostulanteModal = () => setIsEditPostulanteModalOpen(false);

  // Función para crear un nuevo postulante
  const handleCreatePostulante = async (e) => {
    e.preventDefault();
    try {
      // Include id_vacante in the form data
      const newPostulanteData = {
        ...formData,
        id_vacante: selectedVacante.id_vacante
      };

      // Create postulante
      const response = await fetch(`http://localhost:8080/api/postulantes`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newPostulanteData),
      });
      
      if (response.ok) {
        const postulante = await response.json();
        
        // Create interview
        const interviewData = {
          estado: 'Pendiente',
          fecha: new Date().toISOString().split('T')[0],
          id_postulante: postulante.id_postulante,
          id_empleado: employee.id_empleado,
          id_tipo_entrevista: 1,
        };
        
        await fetch(`http://localhost:8080/api/entrevistas`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(interviewData),
        });

        // Create oferta laboral
        const ofertaData = {
          fecha_oferta: "",
          fecha_inicio_propuesta: "",
          link_documento_legal_sin_firma: "",
          link_documento_legal_con_firma: "",
          id_postulante: postulante.id_postulante,
          id_vacante: selectedVacante.id_vacante,
          beneficios: []
        };

        await fetch(`http://localhost:8080/api/ofertas-laborales`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(ofertaData),
        });

        alert('Postulante, entrevista y oferta laboral creados exitosamente');
        
        // Reset form data
        setFormData({
          comentario: '',
          id_puesto: '',
          fecha_fin: '',
          cantidad: 1,
          nombre: '',
          correo: '',
          telefono: '',
          idiomas: [],
          educaciones: [],
          habilidades: [],
          experienciasLaborales: []
        });

        // Actualizar la lista de postulantes
        await fetchPostulantes(selectedVacante.id_vacante);
        
        closeCreatePostulanteModal();
      }
    } catch (error) {
      console.error('Error:', error);
      alert('Error al crear el postulante');
    }
  };

  // Función para editar un postulante
  const handleEditPostulante = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`http://localhost:8080/api/postulantes/${editPostulanteData.id_postulante}`, { // Actualizado
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(editPostulanteData),
      });
      if (response.ok) {
        alert('Postulante actualizado exitosamente');
        // Actualizar la lista de postulantes
        fetchPostulantes(selectedVacante.id_vacante);
        closeEditPostulanteModal();
      }
    } catch (error) {
      console.error('Error al actualizar postulante:', error);
      alert('Error al actualizar el postulante');
    }
  };

  // Función para eliminar un postulante
  const handleDeletePostulante = async (postulanteId) => {
    if (window.confirm('¿Está seguro que desea eliminar este postulante?')) {
      try {
        // Primero obtenemos la oferta laboral del postulante
        const response = await fetch(`http://localhost:8080/api/postulantes/${postulanteId}`);
        const postulante = await response.json();
        
        // Si el postulante tiene una oferta laboral, la eliminamos primero
        if (postulante.ofertaLaboral) {
          await fetch(`http://localhost:8080/api/ofertas-laborales/${postulante.ofertaLaboral.id_oferta}`, {
            method: 'DELETE',
            headers: {
              'Content-Type': 'application/json',
            },
          });
        }
  
        // Después eliminamos el postulante
        const deleteResponse = await fetch(`http://localhost:8080/api/postulantes/${postulanteId}`, {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json',
          },
        });
  
        if (deleteResponse.ok) {
          alert('Postulante eliminado exitosamente');
          // Actualizar la lista de postulantes
          fetchPostulantes(selectedVacante.id_vacante);
        }
      } catch (error) {
        console.error('Error al eliminar postulante:', error);
        alert('Error al eliminar el postulante');
      }
    }
  };

  // También actualizar cuando se selecciona un postulante para ver detalles
  const handlePostulanteClick = async (postulante) => {
    setSelectedPostulante(postulante);
    
    try {
      // 1. Obtener todas las entrevistas del postulante
      const entrevistasResponse = await fetch(`http://localhost:8080/api/postulantes/${postulante.id_postulante}/entrevistas`);
      if (!entrevistasResponse.ok) {
        throw new Error(`Error: ${entrevistasResponse.statusText}`);
      }
      const entrevistasData = await entrevistasResponse.json();
      setEntrevistas(entrevistasData);
  
      // 2. Obtener las observaciones para cada entrevista
      const allObservaciones = [];
      for (const entrevista of entrevistasData) {
        const observacionesResponse = await fetch(`http://localhost:8080/api/observaciones/entrevista/${entrevista.id_entrevista}`);
        if (observacionesResponse.ok) {
          const observacionesData = await observacionesResponse.json();
          // Solo agregamos las descripciones de las observaciones
          allObservaciones.push(...observacionesData.map(obs => ({
            descripcion: obs.descripcion,
            tipoEntrevista: entrevista.tipo_entrevista, // Opcional: agregar el tipo de entrevista
            fecha: entrevista.fecha // Opcional: agregar la fecha de la entrevista
          })));
        }
      }
  
      // 3. Actualizar el estado con todas las observaciones
      setObservacionesPostulante(allObservaciones);
  
      // Calculate average score and update postulante data
      const totalPuntaje = entrevistasData.reduce((sum, entrevista) => sum + (entrevista.puntaje_general || 0), 0);
      const averagePuntaje = entrevistasData.length > 0 ? totalPuntaje / entrevistasData.length : 0;
      
      setSelectedPostulante({
        ...postulante,
        puntaje: averagePuntaje
      });
  
    } catch (error) {
      console.error("Error fetching entrevistas and observaciones:", error);
    }
  
    setShowPostulanteDetailsModal(true);
  }; // Add missing semicolon here

const fetchEntrevistasDetails = async (postulanteId) => {
  try {
    const response = await fetch(`http://localhost:8080/api/postulantes/${postulanteId}/entrevistas`);
    if (!response.ok) {
      throw new Error(`Error: ${response.statusText}`);
    }
    const data = await response.json();
    
    // Ordenar las entrevistas por fecha
    const sortedData = data.sort((a, b) => new Date(b.fecha) - new Date(a.fecha));
    
    setEntrevistasDetails(sortedData);
    setShowEntrevistasModal(true);
  } catch (error) {
    console.error('Error fetching entrevistas details:', error);
    alert('Error al cargar las entrevistas');
  }
};

  const handleVerEntrevistasPostulante = (postulanteId) => {
    fetchEntrevistasDetails(postulanteId);
    setShowPostulanteDetailsModal(false);
  };

  // Función auxiliar para manejar arrays en los forms
  const handleArrayFieldChange = (fieldName, index, value, formSetter, currentData) => {
    const newArray = [...(currentData[fieldName] || [])];
    newArray[index] = { ...newArray[index], ...value };
    formSetter({ ...currentData, [fieldName]: newArray });
  };

  // Función para agregar nuevo elemento a un array
  const handleAddArrayField = (fieldName, formSetter, currentData) => {
    const newField = {};
    switch (fieldName) {
      case 'idiomas':
        newField.nombre = '';
        break;
      case 'educaciones':
        newField.titulo = '';
        newField.institucion = '';
        newField.fecha_inicio = '';
        newField.fecha_fin = '';
        newField.en_curso = false;
        break;
      case 'habilidades':
        newField.nombre = '';
        newField.nivel = 'Básico';
        break;
      case 'experienciasLaborales':
        newField.puesto = '';
        newField.empresa = '';
        newField.fecha_inicio = '';
        newField.fecha_fin = '';
        newField.habilidades = [];
        break;
      default:
        console.warn(`Campo no reconocido: ${fieldName}`);
        break;
    }
    formSetter({
      ...currentData,
      [fieldName]: [...(currentData[fieldName] || []), newField]
    });
  };

  // Función para eliminar elemento de un array
  const handleRemoveArrayField = (fieldName, index, formSetter, currentData) => {
    const newArray = currentData[fieldName].filter((_, i) => i !== index);
    formSetter({ ...currentData, [fieldName]: newArray });
  };

  // Agregar esta función auxiliar
  const isEnCurso = (fecha_fin) => {
    if (!fecha_fin) return true;
    const today = new Date();
    const endDate = new Date(fecha_fin);
    return endDate > today;
  };

  // Función para obtener la oferta laboral del postulante
  const fetchOfertaLaboral = async (postulanteId) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/postulantes/${postulanteId}`);
      setOfertaLaboral(response.data.ofertaLaboral);
    } catch (error) {
      console.error('Error fetching oferta laboral:', error);
    }
  };

  // Llamar a fetchOfertaLaboral cuando se selecciona un postulante
  useEffect(() => {
    if (selectedPostulante) {
      fetchOfertaLaboral(selectedPostulante.id_postulante);
    }
  }, [selectedPostulante]);

  // Añadir estas nuevas funciones antes del return
  const handleEditOferta = (postulante) => {
    const currentOferta = ofertaLaboral || {};
    setEditOfertaData({
      id_oferta: currentOferta.id_oferta || '',
      fecha_oferta: currentOferta.fecha_oferta || '',
      fecha_inicio_propuesta: currentOferta.fecha_inicio_propuesta || '',
      link_documento_legal_sin_firma: currentOferta.link_documento_legal_sin_firma || '',
      link_documento_legal_con_firma: currentOferta.link_documento_legal_con_firma || '',
      id_postulante: postulante.id_postulante,
      id_vacante: selectedVacante.id_vacante,
      estado: currentOferta.estado || 'Pendiente',
      beneficios: currentOferta.beneficios || []
    });
    setShowEditOfertaModal(true);
  };

  const handleEditOfertaSubmit = async (e) => {
    e.preventDefault();
    
    try {
      const payload = {
        fecha_oferta: editOfertaData.fecha_oferta,
        fecha_inicio_propuesta: editOfertaData.fecha_inicio_propuesta,
        link_documento_legal_sin_firma: editOfertaData.link_documento_legal_sin_firma,
        link_documento_legal_con_firma: editOfertaData.link_documento_legal_con_firma,
        id_postulante: editOfertaData.id_postulante,
        id_vacante: editOfertaData.id_vacante,
        estado: editOfertaData.estado,
        beneficios: editOfertaData.beneficios.map(beneficio => ({
          id_beneficio: beneficio.id_beneficio,
          descripcion: beneficio.descripcion
        }))
      };

      console.log('Sending payload:', payload); // For debugging

      const response = await axios.put(
        `http://localhost:8080/api/ofertas-laborales/${editOfertaData.id_oferta}`, 
        payload
      );
      
      if (response.status === 200) {
        setOfertaLaboral(response.data);
        setShowEditOfertaModal(false);
        alert('Oferta laboral actualizada exitosamente.');
        
        // Refresh the postulante data to show updated oferta
        if (selectedPostulante) {
          await fetchOfertaLaboral(selectedPostulante.id_postulante);
        }
      }
    } catch (error) {
      console.error('Error al actualizar la oferta laboral:', error.response?.data || error.message);
      alert('Ocurrió un error al actualizar la oferta laboral.');
    }
  };

  const handleContratar = async (postulante) => {
    if (!window.confirm('¿Está seguro que desea contratar a este postulante?')) {
      return;
    }
  
    try {
      const vacanteId = postulante.id_vacante;
      const postulanteId = postulante.id_postulante;
  
      // Obtener la vacante actualizada
      const vacante = vacantes.find(v => v.id_vacante === vacanteId);
      if (!vacante) {
        alert('Vacante no encontrada');
        return;
      }
  
      // Restar en 1 la cantidad de la vacante
      const nuevaCantidad = vacante.cantidad - 1;
  
      // Editar la vacante con la nueva cantidad
      const response = await fetch(`http://localhost:8080/vacantes/${vacanteId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          ...vacante,
          cantidad: nuevaCantidad,
          estado: nuevaCantidad === 0 ? 'Cerrada' : vacante.estado,
        }),
      });
  
      if (response.ok) {
        // Actualizar la cantidad y estado localmente
        setVacantes(prevVacantes =>
          prevVacantes.map(v =>
            v.id_vacante === vacanteId
              ? { ...v, cantidad: nuevaCantidad, estado: nuevaCantidad === 0 ? 'Cerrada' : v.estado }
              : v
          )
        );
  
        // Enviar email a todos los postulantes si la cantidad es 0
        if (nuevaCantidad === 0) {
          const postulantesResponse = await fetch(`http://localhost:8080/vacantes/${vacanteId}/postulantes`);
          const postulantes = await postulantesResponse.json();
  
          for (const p of postulantes) {
            await handleSendEmail(p.id_postulante, p.correo);
          }
        }
  
        // Crear el empleado con datos del postulante
        await fetch('http://localhost:8080/empleados', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(postulante),
        });
  
        // Eliminar al postulante
        await fetch(`http://localhost:8080/postulantes/${postulanteId}`, {
          method: 'DELETE',
        });
  
        // Actualizar la lista de postulantes localmente
        setPostulantes(prevPostulantes =>
          prevPostulantes.filter(p => p.id_postulante !== postulanteId)
        );
  
        alert('El postulante ha sido contratado exitosamente');
        setShowPostulanteDetailsModal(false);
        navigate('/menu/vacantes');
      } else {
        alert('Error al actualizar la vacante');
      }
    } catch (error) {
      console.error('Error al contratar:', error);
      alert('Ocurrió un error al contratar al postulante');
    }
  };
  
  // Asegúrate de tener una función para obtener detalles del postulante
const fetchPostulanteDetails = async (postulanteId) => {
  try {
    const response = await fetch(`http://localhost:8080/api/postulantes/${postulanteId}`);
    if (!response.ok) throw new Error(`Error: ${response.statusText}`);
    const data = await response.json();

    // Obtener todas las entrevistas del postulante
    const entrevistasResponse = await fetch(`http://localhost:8080/api/postulantes/${postulanteId}/entrevistas`);
    if (!entrevistasResponse.ok) {
      throw new Error(`Error: ${entrevistasResponse.statusText}`);
    }
    const entrevistasData = await entrevistasResponse.json();
    setEntrevistas(entrevistasData);

    // Obtener las observaciones para cada entrevista
    const allObservaciones = [];
    for (const entrevista of entrevistasData) {
      const observacionesResponse = await fetch(`http://localhost:8080/api/observaciones/entrevista/${entrevista.id_entrevista}`);
      if (observacionesResponse.ok) {
        const observacionesData = await observacionesResponse.json();
        allObservaciones.push(...observacionesData.map(obs => ({
          descripcion: obs.descripcion,
          tipoEntrevista: entrevista.tipo_entrevista,
          fecha: entrevista.fecha
        })));
      }
    }
    setObservacionesPostulante(allObservaciones);

    // Calcular el puntaje promedio y actualizar los datos del postulante
    const totalPuntaje = entrevistasData.reduce((sum, entrevista) => sum + (entrevista.puntaje_general || 0), 0);
    const averagePuntaje = entrevistasData.length > 0 ? totalPuntaje / entrevistasData.length : 0;

    setSelectedPostulante({
      ...data,
      puntaje: averagePuntaje
    });
  } catch (error) {
    console.error('Error al obtener detalles del postulante:', error);
    alert('Error al cargar los detalles del postulante');
  }
};

// Modifica el manejador para cerrar entrevistas y refrescar detalles
const handleEntrevistasClose = async () => {
  setShowEntrevistasModal(false);
  await fetchPostulanteDetails(selectedPostulante.id_postulante);
  setShowPostulanteDetailsModal(true); // Abre el modal de detalles del postulante
};

// Update the email sending code where it exists
const handleSendEmail = async (entrevistaId, postulanteEmail) => {
  try {
    // Create form data
    const formData = new FormData();
    formData.append('to', postulanteEmail);
    formData.append('subject', 'Resultados de tu entrevista');

    // Log the data being sent (for debugging)
    console.log('Sending email to:', postulanteEmail);
    console.log('Interview ID:', entrevistaId);

    const response = await fetch(`http://localhost:8080/api/entrevistas/${entrevistaId}/send-email`, {
      method: 'POST',
      headers: {
        // Remove Content-Type header to let the browser set it automatically with boundary
        'Accept': 'application/json',
      },
      body: formData
    });

    // Log the response for debugging
    console.log('Response status:', response.status);
    const responseText = await response.text();
    console.log('Response body:', responseText);

    if (response.ok) {
      alert('Email enviado exitosamente');
    } else {
      throw new Error(`Error al enviar el email: ${responseText}`);
    }
  } catch (error) {
    console.error('Error details:', error);
    alert(`Error al enviar el email: ${error.message}`);
  }
};

const handleFileUpload = async (e) => {
  const file = e.target.files[0];
  if (!file) return;

  const formData = new FormData();
  formData.append('file', file);

  try {
    const response = await fetch('http://localhost:8080/api/upload', {
      method: 'POST',
      body: formData,
    });

    if (response.ok) {
      const data = await response.json();
      setFormData((prevData) => ({
        ...prevData,
        nombre: data.nombre || '',
        correo: data.correo || '',
        telefono: data.telefono || '',
      }));
      alert('Datos extraídos exitosamente');
    } else {
      alert('Error al extraer datos del archivo');
    }
  } catch (error) {
    console.error('Error al subir el archivo:', error);
    alert('Error al subir el archivo');
  }
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
          {Object.keys(groupedPuestos || {}).map((id_puesto) => (
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
                    <button 
                      className="delete-button"
                      onClick={(e) => handleDelete(e, vacante.id_vacante)}
                      title="Eliminar vacante"
                    >
                      <i className="fas fa-trash"></i>
                    </button>
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
              <button onClick={openCreatePostulanteModal}>Crear Postulante</button>
              
              {loadingPostulantes ? (
                <div className="loading-indicator">Cargando postulantes...</div>
              ) : (
                <div className="postulantes-list">
                  {postulantes.map(postulante => (
                    <div key={postulante.id_postulante} className="postulante-item">
                      <span onClick={() => handlePostulanteClick(postulante)} style={{ cursor: 'pointer', flex: 1 }}>
                        {postulante.nombre}
                        {loadingDetails && <small className="loading-details"> (Cargando detalles...)</small>}
                      </span>
                      <button onClick={() => openEditPostulanteModal(postulante)}>Editar</button>
                      <button onClick={() => handleDeletePostulante(postulante.id_postulante)}>Eliminar</button>
                    </div>
                  ))}
                </div>
              )}

              <div className="modal-buttons">
                <button type="button" onClick={() => setShowPostulantesModal(false)}>
                  Volver
                </button>
              </div>
            </div>
          </div>
        )}

        {/* Update the postulante details modal */}
        {showPostulanteDetailsModal && selectedPostulante && (
          <div className="modal-overlay" onClick={closePostulanteDetailsModal}>
            <div className="modal modal-large" onClick={(e) => e.stopPropagation()}>
              <button className="close-popup" onClick={closePostulanteDetailsModal}>&times;</button>
              
              <div className="postulante-details-container">
                {/* Column 1: Postulante Information */}
                <div className="postulante-info-column">
                  <h2>Información del Postulante</h2>
                  
                  <div className="info-section">
                    <h3>Datos Personales</h3>
                    <p><strong>Nombre:</strong> {selectedPostulante.nombre}</p>
                    <p><strong>Teléfono:</strong> {selectedPostulante.telefono}</p>
                    <p><strong>Correo:</strong> {selectedPostulante.correo}</p>
                  </div>

                  <div className="info-section">
                    <h3>Idiomas</h3>
                    <ul>
                      {(selectedPostulante.idiomas || []).map(idioma => (
                        <li key={idioma.id_idioma}>{idioma.nombre}</li>
                      ))}
                    </ul>
                  </div>

                  <div className="info-section">
                    <h3>Educación</h3>
                    {(selectedPostulante.educaciones || []).map(educacion => (
                      <div key={educacion.id_educacion} className="education-item">
                        <p><strong>{educacion.titulo}</strong></p>
                        <p>{educacion.institucion}</p>
                        <p>{educacion.fecha_inicio} - {educacion.en_curso ? 'En curso' : educacion.fecha_fin}</p>
                      </div>
                    ))}
                  </div>

                  <div className="info-section">
                    <h3>Habilidades</h3>
                    <ul>
                      {(selectedPostulante.habilidades || []).map(habilidad => (
                        <li key={habilidad.id_habilidad_postulante}>
                          {habilidad.nombre} - <span className="skill-level">{habilidad.nivel}</span>
                        </li>
                      ))}
                    </ul>
                  </div>

                  <div className="info-section">
                    <h3>Experiencia Laboral</h3>
                    {(selectedPostulante.experienciasLaborales || []).map(experiencia => (
                      <div key={experiencia.id_experiencia} className="experience-item">
                        <p><strong>{experiencia.puesto}</strong> en {experiencia.empresa}</p>
                        <p>{experiencia.fecha_inicio} - {experiencia.fecha_fin}</p>
                        <p><strong>Habilidades desarrolladas:</strong></p>
                        <ul>
                          {(experiencia.habilidades || []).map(habilidad => (
                            <li key={habilidad.id_habilidad_experiencia}>{habilidad.nombre}</li>
                          ))}
                        </ul>
                      </div>
                    ))}
                  </div>
                </div>

                {/* Column 2: Evaluation Section (to be implemented) */}
                <div className="evaluation-column">
                  <h2>Evaluación</h2>
                  <p>Puntaje: {selectedPostulante.puntaje}</p>
                  <h4>Entrevistas</h4>
                  <ul className="entrevistas-list">
                    {entrevistas.map((entrevista) => (
                      <li key={entrevista.id_entrevista} className="entrevista-item">
                        {entrevista.tipo_entrevista} - {entrevista.estado} on {entrevista.fecha} (Puntaje: {entrevista.puntaje_general})
                      </li>
                    ))}
                  </ul>
                  {selectedPostulante && (
                    <button 
                        onClick={() => handleVerEntrevistasPostulante(selectedPostulante.id_postulante)} 
                        className="manage-entrevistas-button"
                    >
                        Gestionar Entrevistas
                    </button>
                  )}
                  {/* Agregar espacio adicional */}
                  <div style={{ marginTop: '20px' }}></div>
                  {/* Nueva sección de Feedback */}
                  <section className="feedback-section">
                    <h2>Feedback</h2>
                    <div className="feedback-content">
                      <p className="feedback-greeting">
                        Hola, <strong>{selectedPostulante.nombre}</strong>. 
                        Este es el feedback de todo tu proceso de postulación. Esperamos te sea útil.
                      </p>
                      <div className="feedback-observations">
                        <h3>Lista de observaciones:</h3>
                        {observacionesPostulante.length > 0 ? (
                          <ol>
                            {observacionesPostulante.map((observacion, index) => (
                              <li key={index}>{observacion.descripcion}</li>
                            ))}
                          </ol>
                        ) : (
                          <p>No hay observaciones registradas.</p>
                        )}
                      </div>
                    </div>
                  </section>
                  {/* Nueva sección de contratación */}
                  {/* Sección de contratación siempre visible */}
                  <section>
                    <h2>Contratación</h2>
                    <p><strong>Fecha de Oferta:</strong> {ofertaLaboral ? ofertaLaboral.fecha_oferta : 'No asignada'}</p>
                    <p><strong>Fecha de Inicio Propuesta:</strong> {ofertaLaboral ? ofertaLaboral.fecha_inicio_propuesta : 'No asignada'}</p>
                    <p>
                      <strong>Documento Legal Sin Firma:</strong> {
                        ofertaLaboral && ofertaLaboral.link_documento_legal_sin_firma ? 
                          <a href={ofertaLaboral.link_documento_legal_sin_firma}>Ver Documento</a> : 
                          'No disponible'
                      }
                    </p>
                    <p>
                      <strong>Documento Legal Con Firma:</strong> {
                        ofertaLaboral && ofertaLaboral.link_documento_legal_con_firma ? 
                          <a href={ofertaLaboral.link_documento_legal_con_firma}>Ver Documento</a> : 
                          'No disponible'
                      }
                    </p>
                    <h3>Beneficios</h3>
                    {ofertaLaboral && ofertaLaboral.beneficios && ofertaLaboral.beneficios.length > 0 ? (
                      <ul>
                        {ofertaLaboral.beneficios.map(beneficio => (
                          <li key={beneficio.id_beneficio}>{beneficio.descripcion}</li>
                        ))}
                      </ul>
                    ) : (
                      <p>No hay beneficios asignados</p>
                    )}
                    <div className="contratacion-buttons">
                      <button onClick={() => handleEditOferta(selectedPostulante)}>Editar</button>
                      <button onClick={() => handleContratar(selectedPostulante)}>Contratar</button>
                    </div>
                  </section>
                </div>
              </div>
            </div>
          </div>
        )}

        {/* Modal para crear postulante */}
        {isCreatePostulanteModalOpen && (
          <div className="modal-overlay">
            <div className="modal modal-large">
              <h2>Crear Postulante</h2>
              <form onSubmit={handleCreatePostulante}>
                <div className="form-sections">
                  <div className="form-section">
                    <h3>Datos Personales</h3>
                    <label>
                      Nombre:
                      <input
                        type="text"
                        name="nombre"
                        value={formData.nombre}
                        onChange={handleChange}
                        required
                      />
                    </label>
                    <label>
                      Correo:
                      <input
                        type="email"
                        name="correo"
                        value={formData.correo}
                        onChange={handleChange}
                        required
                      />
                    </label>
                    <label>
                      Teléfono:
                      <input
                        type="text"
                        name="telefono"
                        value={formData.telefono}
                        onChange={handleChange}
                        required
                      />
                    </label>
                    <label>
                      Subir CV (PDF):
                      <input
                        type="file"
                        accept="application/pdf"
                        onChange={handleFileUpload}
                      />
                    </label>
                  </div>

                  <div className="form-section">
                    <h3>Idiomas</h3>
                    {formData.idiomas.map((idioma, index) => (
                      <div key={index} className="array-field">
                        <input
                          type="text"
                          value={idioma.nombre}
                          onChange={(e) => handleArrayFieldChange('idiomas', index, { nombre: e.target.value }, setFormData, formData)}
                          placeholder="Nombre del idioma"
                        />
                        <button type="button" onClick={() => handleRemoveArrayField('idiomas', index, setFormData, formData)}>
                          Eliminar
                        </button>
                      </div>
                    ))}
                    <button type="button" onClick={() => handleAddArrayField('idiomas', setFormData, formData)}>
                      Agregar Idioma
                    </button>
                  </div>

                  <div className="form-section">
                    <h3>Educación</h3>
                    {formData.educaciones.map((educacion, index) => (
                      <div key={index} className="array-field">
                        <input
                          type="text"
                          value={educacion.titulo}
                          onChange={(e) => handleArrayFieldChange('educaciones', index, { titulo: e.target.value }, setFormData, formData)}
                          placeholder="Título"
                        />
                        <input
                          type="text"
                          value={educacion.institucion}
                          onChange={(e) => handleArrayFieldChange('educaciones', index, { institucion: e.target.value }, setFormData, formData)}
                          placeholder="Institución"
                        />
                        <div className="date-inputs">
                          <div className="date-field">
                            <label>Fecha de inicio:</label>
                            <input
                              type="date"
                              value={educacion.fecha_inicio}
                              onChange={(e) => handleArrayFieldChange('educaciones', index, { 
                                fecha_inicio: e.target.value,
                                en_curso: isEnCurso(educacion.fecha_fin)
                              }, setFormData, formData)}
                            />
                          </div>
                          <div className="date-field">
                            <label>Fecha de fin:</label>
                            <input
                              type="date"
                              value={educacion.fecha_fin}
                              onChange={(e) => handleArrayFieldChange('educaciones', index, { 
                                fecha_fin: e.target.value,
                                en_curso: isEnCurso(e.target.value)
                              }, setFormData, formData)}
                            />
                          </div>
                        </div>
                        <div className="education-status">
                          <span className={`status-indicator ${educacion.en_curso ? 'en-curso' : 'finalizado'}`}>
                            {educacion.en_curso ? 'En curso' : 'Finalizado'}
                          </span>
                        </div>
                        <button 
                          type="button" 
                          className="remove-button"
                          onClick={() => handleRemoveArrayField('educaciones', index, setFormData, formData)}
                        >
                          Eliminar
                        </button>
                      </div>
                    ))}
                    <button type="button" onClick={() => handleAddArrayField('educaciones', setFormData, formData)}>
                      Agregar Educación
                    </button>
                  </div>

                  <div className="form-section">
                    <h3>Habilidades</h3>
                    {formData.habilidades.map((habilidad, index) => (
                      <div key={index} className="array-field">
                        <input
                          type="text"
                          value={habilidad.nombre}
                          onChange={(e) => handleArrayFieldChange('habilidades', index, { nombre: e.target.value }, setFormData, formData)}
                          placeholder="Nombre de la habilidad"
                        />
                        <select
                          value={habilidad.nivel}
                          onChange={(e) => handleArrayFieldChange('habilidades', index, { nivel: e.target.value }, setFormData, formData)}
                        >
                          <option value="Básico">Básico</option>
                          <option value="Intermedio">Intermedio</option>
                          <option value="Avanzado">Avanzado</option>
                        </select>
                        <button type="button" onClick={() => handleRemoveArrayField('habilidades', index, setFormData, formData)}>
                          Eliminar
                        </button>
                      </div>
                    ))}
                    <button type="button" onClick={() => handleAddArrayField('habilidades', setFormData, formData)}>
                      Agregar Habilidad
                    </button>
                  </div>

                  <div className="form-section">
                    <h3>Experiencia Laboral</h3>
                    {formData.experienciasLaborales.map((experiencia, index) => (
                      <div key={index} className="array-field">
                        <input
                          type="text"
                          value={experiencia.puesto}
                          onChange={(e) => handleArrayFieldChange('experienciasLaborales', index, { puesto: e.target.value }, setFormData, formData)}
                          placeholder="Puesto"
                        />
                        <input
                          type="text"
                          value={experiencia.empresa}
                          onChange={(e) => handleArrayFieldChange('experienciasLaborales', index, { empresa: e.target.value }, setFormData, formData)}
                          placeholder="Empresa"
                        />
                        <div className="date-inputs">
                          <div className="date-field">
                            <label>Fecha de inicio:</label>
                            <input
                              type="date"
                              value={experiencia.fecha_inicio}
                              onChange={(e) => handleArrayFieldChange('experienciasLaborales', index, { fecha_inicio: e.target.value }, setFormData, formData)}
                            />
                          </div>
                          <div className="date-field">
                            <label>Fecha de fin:</label>
                            <input
                              type="date"
                              value={experiencia.fecha_fin}
                              onChange={(e) => handleArrayFieldChange('experienciasLaborales', index, { fecha_fin: e.target.value }, setFormData, formData)}
                            />
                          </div>
                        </div>
                        <div className="habilidades-experiencia">
                          {experiencia.habilidades?.map((habilidad, habIndex) => (
                            <div key={habIndex}>
                              <input
                                type="text"
                                value={habilidad.nombre}
                                onChange={(e) => {
                                  const newHabilidades = [...experiencia.habilidades];
                                  newHabilidades[habIndex] = { ...habilidad, nombre: e.target.value };
                                  handleArrayFieldChange('experienciasLaborales', index, { habilidades: newHabilidades }, setFormData, formData);
                                }}
                                placeholder="Habilidad"
                              />
                              <button type="button" onClick={() => {
                                const newHabilidades = experiencia.habilidades.filter((_, i) => i !== habIndex);
                                handleArrayFieldChange('experienciasLaborales', index, { habilidades: newHabilidades }, setFormData, formData);
                              }}>
                                Eliminar Habilidad
                              </button>
                            </div>
                          ))}
                          <button type="button" onClick={() => {
                            const newHabilidades = [...(experiencia.habilidades || []), { nombre: '' }];
                            handleArrayFieldChange('experienciasLaborales', index, { habilidades: newHabilidades }, setFormData, formData);
                          }}>
                            Agregar Habilidad
                          </button>
                        </div>
                        <button type="button" onClick={() => handleRemoveArrayField('experienciasLaborales', index, setFormData, formData)}>
                          Eliminar Experiencia
                        </button>
                      </div>
                    ))}
                    <button type="button" onClick={() => handleAddArrayField('experienciasLaborales', setFormData, formData)}>
                      Agregar Experiencia Laboral
                    </button>
                  </div>
                </div>

                <div className="modal-buttons">
                  <button type="submit">Crear</button>
                  <button type="button" onClick={closeCreatePostulanteModal}>
                    Cancelar
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}

        {/* Modal para editar postulante */}
        {isEditPostulanteModalOpen && (
          <div className="modal-overlay">
            <div className="modal modal-large">
              <h2>Editar Postulante</h2>
              <form onSubmit={handleEditPostulante}>
                <div className="form-sections">
                  <div className="form-section">
                    <h3>Datos Personales</h3>
                    <label>
                      Nombre:
                      <input
                        type="text"
                        name="nombre"
                        value={editPostulanteData.nombre}
                        onChange={(e) => setEditPostulanteData({ ...editPostulanteData, nombre: e.target.value })}
                        required
                      />
                    </label>
                    <label>
                      Correo:
                      <input
                        type="email"
                        name="correo"
                        value={editPostulanteData.correo}
                        onChange={(e) => setEditPostulanteData({ ...editPostulanteData, correo: e.target.value })}
                        required
                      />
                    </label>
                    <label>
                      Teléfono:
                      <input
                        type="text"
                        name="telefono"
                        value={editPostulanteData.telefono}
                        onChange={(e) => setEditPostulanteData({ ...editPostulanteData, telefono: e.target.value })}
                        required
                      />
                    </label>
                  </div>

                  <div className="form-section">
                    <h3>Idiomas</h3>
                    {editPostulanteData.idiomas.map((idioma, index) => (
                      <div key={index} className="array-field">
                        <input
                          type="text"
                          value={idioma.nombre}
                          onChange={(e) => handleArrayFieldChange('idiomas', index, { nombre: e.target.value }, setEditPostulanteData, editPostulanteData)}
                          placeholder="Nombre del idioma"
                        />
                        <button type="button" onClick={() => handleRemoveArrayField('idiomas', index, setEditPostulanteData, editPostulanteData)}>
                          Eliminar
                        </button>
                      </div>
                    ))}
                    <button type="button" onClick={() => handleAddArrayField('idiomas', setEditPostulanteData, editPostulanteData)}>
                      Agregar Idioma
                    </button>
                  </div>

                  <div className="form-section">
                    <h3>Educación</h3>
                    {editPostulanteData.educaciones.map((educacion, index) => (
                      <div key={index} className="array-field">
                        <input
                          type="text"
                          value={educacion.titulo}
                          onChange={(e) => handleArrayFieldChange('educaciones', index, { titulo: e.target.value }, setEditPostulanteData, editPostulanteData)}
                          placeholder="Título"
                        />
                        <input
                          type="text"
                          value={educacion.institucion}
                          onChange={(e) => handleArrayFieldChange('educaciones', index, { institucion: e.target.value }, setEditPostulanteData, editPostulanteData)}
                          placeholder="Institución"
                        />
                        <div className="date-inputs">
                          <div className="date-field">
                            <label>Fecha de inicio:</label>
                            <input
                              type="date"
                              value={educacion.fecha_inicio}
                              onChange={(e) => handleArrayFieldChange('educaciones', index, { 
                                fecha_inicio: e.target.value,
                                en_curso: isEnCurso(educacion.fecha_fin)
                              }, setEditPostulanteData, editPostulanteData)}
                            />
                          </div>
                          <div className="date-field">
                            <label>Fecha de fin:</label>
                            <input
                              type="date"
                              value={educacion.fecha_fin}
                              onChange={(e) => handleArrayFieldChange('educaciones', index, { 
                                fecha_fin: e.target.value,
                                en_curso: isEnCurso(e.target.value)
                              }, setEditPostulanteData, editPostulanteData)}
                            />
                          </div>
                        </div>
                        <div className="education-status">
                          <span className={`status-indicator ${educacion.en_curso ? 'en-curso' : 'finalizado'}`}>
                            {educacion.en_curso ? 'En curso' : 'Finalizado'}
                          </span>
                        </div>
                        <button 
                          type="button" 
                          className="remove-button"
                          onClick={() => handleRemoveArrayField('educaciones', index, setEditPostulanteData, editPostulanteData)}
                        >
                          Eliminar
                        </button>
                      </div>
                    ))}
                    <button type="button" onClick={() => handleAddArrayField('educaciones', setEditPostulanteData, editPostulanteData)}>
                      Agregar Educación
                    </button>
                  </div>

                  <div className="form-section">
                    <h3>Habilidades</h3>
                    {editPostulanteData.habilidades.map((habilidad, index) => (
                      <div key={index} className="array-field">
                        <input
                          type="text"
                          value={habilidad.nombre}
                          onChange={(e) => handleArrayFieldChange('habilidades', index, { nombre: e.target.value }, setEditPostulanteData, editPostulanteData)}
                          placeholder="Nombre de la habilidad"
                        />
                        <select
                          value={habilidad.nivel}
                          onChange={(e) => handleArrayFieldChange('habilidades', index, { nivel: e.target.value }, setEditPostulanteData, editPostulanteData)}
                        >
                          <option value="Básico">Básico</option>
                          <option value="Intermedio">Intermedio</option>
                          <option value="Avanzado">Avanzado</option>
                        </select>
                        <button type="button" onClick={() => handleRemoveArrayField('habilidades', index, setEditPostulanteData, editPostulanteData)}>
                          Eliminar
                        </button>
                      </div>
                    ))}
                    <button type="button" onClick={() => handleAddArrayField('habilidades', setEditPostulanteData, editPostulanteData)}>
                      Agregar Habilidad
                    </button>
                  </div>

                  <div className="form-section">
                    <h3>Experiencia Laboral</h3>
                    {editPostulanteData.experienciasLaborales.map((experiencia, index) => (
                      <div key={index} className="array-field">
                        <input
                          type="text"
                          value={experiencia.puesto}
                          onChange={(e) => handleArrayFieldChange('experienciasLaborales', index, { puesto: e.target.value }, setEditPostulanteData, editPostulanteData)}
                          placeholder="Puesto"
                        />
                        <input
                          type="text"
                          value={experiencia.empresa}
                          onChange={(e) => handleArrayFieldChange('experienciasLaborales', index, { empresa: e.target.value }, setEditPostulanteData, editPostulanteData)}
                          placeholder="Empresa"
                        />
                        <div className="date-inputs">
                          <div className="date-field">
                            <label>Fecha de inicio:</label>
                            <input
                              type="date"
                              value={experiencia.fecha_inicio}
                              onChange={(e) => handleArrayFieldChange('experienciasLaborales', index, { fecha_inicio: e.target.value }, setEditPostulanteData, editPostulanteData)}
                            />
                          </div>
                          <div className="date-field">
                            <label>Fecha de fin:</label>
                            <input
                              type="date"
                              value={experiencia.fecha_fin}
                              onChange={(e) => handleArrayFieldChange('experienciasLaborales', index, { fecha_fin: e.target.value }, setEditPostulanteData, editPostulanteData)}
                            />
                          </div>
                        </div>
                        <div className="habilidades-experiencia">
                          {experiencia.habilidades?.map((habilidad, habIndex) => (
                            <div key={habIndex}>
                              <input
                                type="text"
                                value={habilidad.nombre}
                                onChange={(e) => {
                                  const newHabilidades = [...experiencia.habilidades];
                                  newHabilidades[habIndex] = { ...habilidad, nombre: e.target.value };
                                  handleArrayFieldChange('experienciasLaborales', index, { habilidades: newHabilidades }, setEditPostulanteData, editPostulanteData);
                                }}
                                placeholder="Habilidad"
                              />
                              <button type="button" onClick={() => {
                                const newHabilidades = experiencia.habilidades.filter((_, i) => i !== habIndex);
                                handleArrayFieldChange('experienciasLaborales', index, { habilidades: newHabilidades }, setEditPostulanteData, editPostulanteData);
                              }}>
                                Eliminar Habilidad
                              </button>
                            </div>
                          ))}
                          <button type="button" onClick={() => {
                            const newHabilidades = [...(experiencia.habilidades || []), { nombre: '' }];
                            handleArrayFieldChange('experienciasLaborales', index, { habilidades: newHabilidades }, setEditPostulanteData, editPostulanteData);
                          }}>
                            Agregar Habilidad
                          </button>
                        </div>
                        <button type="button" onClick={() => handleRemoveArrayField('experienciasLaborales', index, setEditPostulanteData, editPostulanteData)}>
                          Eliminar Experiencia
                        </button>
                      </div>
                    ))}
                    <button type="button" onClick={() => handleAddArrayField('experienciasLaborales', setEditPostulanteData, editPostulanteData)}>
                      Agregar Experiencia Laboral
                    </button>
                  </div>
                </div>

                <div className="modal-buttons">
                  <button type="submit">Guardar</button>
                  <button type="button" onClick={closeEditPostulanteModal}>
                    Cancelar
                  </button>
                </div>
              </form>            
            </div>          
          </div>        
        )}      
        {/* Add the GestionarEntrevistas modal */}
        {showEntrevistasModal && (
          <GestionarEntrevistas
            entrevistasDetails={entrevistasDetails}
            onClose={handleEntrevistasClose}
            postulanteId={selectedPostulante.id_postulante}
            fetchEntrevistasDetails={fetchEntrevistasDetails} // Pass the function
          />
        )}

        {/* Añadir el modal de edición de oferta laboral antes del último </main> */}
        {showEditOfertaModal && (
          <div className="modal-overlay">
            <div className="modal">
              <h2>Editar Oferta Laboral</h2>
              <form onSubmit={handleEditOfertaSubmit}>
                <label>
                  Fecha de Oferta:
                  <input
                    type="date"
                    value={editOfertaData.fecha_oferta}
                    onChange={(e) => setEditOfertaData({
                      ...editOfertaData,
                      fecha_oferta: e.target.value
                    })}
                  />
                </label>
                <label>
                  Fecha de Inicio Propuesta:
                  <input
                    type="date"
                    value={editOfertaData.fecha_inicio_propuesta}
                    onChange={(e) => setEditOfertaData({
                      ...editOfertaData,
                      fecha_inicio_propuesta: e.target.value
                    })}
                  />
                </label>
                <label>
                  Link Documento Legal Sin Firma:
                  <input
                    type="text"
                    value={editOfertaData.link_documento_legal_sin_firma}
                    onChange={(e) => setEditOfertaData({
                      ...editOfertaData,
                      link_documento_legal_sin_firma: e.target.value
                    })}
                  />
                </label>
                <label>
                  Link Documento Legal Con Firma:
                  <input
                    type="text"
                    value={editOfertaData.link_documento_legal_con_firma}
                    onChange={(e) => setEditOfertaData({
                      ...editOfertaData,
                      link_documento_legal_con_firma: e.target.value
                    })}
                  />
                </label>
                
                <h3>Beneficios</h3>
                <div className="beneficios-selector">
                  <select
                    value=""
                    onChange={(e) => {
                      const beneficioSeleccionado = beneficiosDisponibles.find(
                        b => b.id_beneficio === parseInt(e.target.value)
                      );
                      if (beneficioSeleccionado && !editOfertaData.beneficios.some(
                        b => b.id_beneficio === beneficioSeleccionado.id_beneficio
                      )) {
                        setEditOfertaData({
                          ...editOfertaData,
                          beneficios: [...editOfertaData.beneficios, beneficioSeleccionado]
                        });
                      }
                    }}
                  >
                    <option value="">Seleccionar beneficio</option>
                    {beneficiosDisponibles.map(beneficio => (
                      <option 
                        key={beneficio.id_beneficio} 
                        value={beneficio.id_beneficio}
                        disabled={editOfertaData.beneficios.some(
                          b => b.id_beneficio === beneficio.id_beneficio
                        )}
                      >
                        {beneficio.descripcion}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="beneficios-list">
                  {editOfertaData.beneficios.map((beneficio, index) => (
                    <div key={beneficio.id_beneficio} className="beneficio-item">
                      <span>{beneficio.descripcion}</span>
                      <button 
                        type="button"
                        onClick={() => {
                          setEditOfertaData({
                            ...editOfertaData,
                            beneficios: editOfertaData.beneficios.filter((_, i) => i !== index)
                          });
                        }}
                      >
                        Eliminar
                      </button>
                    </div>
                  ))}
                </div>

                <div className="modal-buttons">
                  <button type="submit">Guardar</button>
                  <button type="button" onClick={() => setShowEditOfertaModal(false)}>
                    Cancelar
                  </button>
                </div>
              </form>
            </div>
          </div>
        )}
      </main>    
    </div>  
  );
};

export default Vacantes;