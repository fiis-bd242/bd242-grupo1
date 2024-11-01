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
  });

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
    const nuevoFormData = {
      estado: 'Abierta',
      fecha_inicio: today,
      fecha_fin: formData.fecha_fin,
      comentario: formData.comentario,
      id_puesto: formData.id_puesto,
    };
    try {
      const response = await fetch('http://localhost:8080/vacantes', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(nuevoFormData),
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      closeModal(); // Cierra la ventana emergente
      window.location.reload(); // Refresca la página
    } catch (error) {
      console.error('Error creando vacante:', error);
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
            <div key={id_puesto} className="vacante">
              <h2>{getNombrePuesto(id_puesto)}</h2>
              {groupedPuestos[id_puesto]
                .slice(0, hiddenVacantes[id_puesto] ? undefined : 1)
                .map((vacante) => (
                  <div key={vacante.id_vacante}>
                  </div>
                ))}
              {groupedPuestos[id_puesto].length > 1 && (
                <button onClick={() => toggleHidden(id_puesto)}>
                  {hiddenVacantes[id_puesto] ? 'Mostrar Más' : 'Ocultar'}
                </button>
              )}
            </div>
          ))}
        </div>
      </main>
    </div>
  );
};

export default Vacantes;