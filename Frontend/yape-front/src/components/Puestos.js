// Puestos.js
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Puestos.css'; // Asegúrate de que el archivo CSS exista y sea correcto

const getDepthColor = (level) => {
  switch (level) {
    case 2:
      return 'level-2';
    case 3:
      return 'level-3';
    case 4:
      return 'level-4';
    case 5:
      return 'level-5';
    case 6:
      return 'level-6';
    default:
      return 'level-default';
  }
};

const DepartamentoNode = ({ departamento, level, onDepartmentClick, onAddPuesto, onEditPuesto }) => {
  if (!departamento) return null;

  const handleClick = () => {
    if (departamento.subDepartamentos?.length > 0) {
      onDepartmentClick(departamento);
    }
  };

  return (
    <div className={`department ${getDepthColor(level)}`} onClick={handleClick}>
      <div className="dept-name">
        {departamento.descripcion || 'Sin descripción'}
      </div>

      {/* Puestos del departamento */}
      <div className="puestos">
        {departamento.puestos && departamento.puestos.length > 0 ? (
          departamento.puestos.map(puesto => (
            <div key={puesto.id_puesto} className="puesto">
              {puesto.nombre || 'Sin nombre'}
              <button
                className="edit-button"
                onClick={(e) => {
                  e.stopPropagation();
                  onEditPuesto(puesto, departamento.id_departamento);
                }}
              >
                ✏️
              </button>
            </div>
          ))
        ) : (
          <p>No hay puestos disponibles.</p>
        )}
        <button
          className="add-button"
          onClick={(e) => {
            e.stopPropagation();
            onAddPuesto(departamento.id_departamento);
          }}
        >
          ➕
        </button>
      </div>
    </div>
  );
};

const Puestos = () => {
  const navigate = useNavigate();
  const [time, setTime] = useState(new Date());
  const [currentDepartments, setCurrentDepartments] = useState([]);
  const [level, setLevel] = useState(2);
  const [history, setHistory] = useState([]);
  const [modalOpen, setModalOpen] = useState(false);
  const [modalData, setModalData] = useState({ 
    id_puesto: null, 
    nombre: '', 
    paga: '', 
    id_departamento: null, 
    funciones: [{ nombre: '', descripcion: '' }] 
  });
  const [isDeleting, setIsDeleting] = useState(false);
  const [employee, setEmployee] = useState({ nombre: '', apellido: '' });

  // Función para obtener el organigrama de departamentos
  const fetchOrganigrama = async () => {
    try {
      const response = await fetch('http://localhost:8080/departamentos/1/organigrama');
      if (!response.ok) {
        throw new Error(`Error HTTP! status: ${response.status}`);
      }
      const data = await response.json();
      console.log('Datos recibidos de la API (organigrama):', data);
      setCurrentDepartments(data.subDepartamentos || []);
    } catch (error) {
      console.error('Error al obtener el organigrama:', error);
      setCurrentDepartments([]);
    }
  };

  // Función para obtener los datos del empleado
  const fetchEmployee = async () => {
    try {
      const response = await fetch('http://localhost:8080/empleados/1');
      if (!response.ok) {
        throw new Error(`Error HTTP! status: ${response.status}`);
      }
      const data = await response.json();
      console.log('Datos recibidos de la API (empleado):', data);
      setEmployee(data);
    } catch (error) {
      console.error('Error al obtener los datos del empleado:', error);
    }
  };

  useEffect(() => {
    const timer = setInterval(() => {
      setTime(new Date());
    }, 1000);

    fetchOrganigrama();
    fetchEmployee();

    return () => {
      clearInterval(timer);
    };
  }, []);

  const handleAddPuesto = (id_departamento) => {
    setModalData({ 
      id_puesto: null, 
      nombre: '', 
      paga: '', 
      id_departamento, 
      funciones: [{ nombre: '', descripcion: '' }] 
    });
    setIsDeleting(false);
    setModalOpen(true);
  };

  const handleEditPuesto = (puesto, id_departamento) => {
    setModalData({ 
      id_puesto: puesto.id_puesto, 
      nombre: puesto.nombre, 
      paga: puesto.paga, 
      id_departamento, 
      funciones: puesto.funciones || [{ nombre: '', descripcion: '' }] 
    });
    setIsDeleting(true);
    setModalOpen(true);
  };

  const handleModalChange = (e, index = null, field = null) => {
    const { name, value } = e.target;
    if (field === 'funciones') {
      const funciones = [...modalData.funciones];
      funciones[index][e.target.dataset.field] = value;
      setModalData({ ...modalData, funciones });
    } else {
      setModalData({ ...modalData, [name]: value });
    }
  };

  const addFuncion = () => {
    setModalData({ 
      ...modalData, 
      funciones: [...modalData.funciones, { nombre: '', descripcion: '' }] 
    });
  };

  const removeFuncion = (index) => {
    const funciones = [...modalData.funciones];
    funciones.splice(index, 1);
    setModalData({ ...modalData, funciones });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
  
    if (!modalData.nombre || !modalData.paga || !modalData.id_departamento) {
      alert('Por favor, completa todos los campos obligatorios.');
      return;
    }
  
    const puestoData = {
      nombre: modalData.nombre,
      paga: parseFloat(modalData.paga),
      id_departamento: parseInt(modalData.id_departamento),
      funciones: modalData.funciones
    };
  
    try {
      const response = await fetch('http://localhost:8080/puestos', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(puestoData),
      });
  
      await response.json();
      
      // Reset form states
      setModalData({
        id_puesto: null,
        nombre: '',
        paga: '',
        id_departamento: modalData.id_departamento,
        funciones: [{ nombre: '', descripcion: '' }]
      });
      setModalOpen(false);
      setIsDeleting(false);
  
      // Fetch updated data
      const organigramaResponse = await fetch('http://localhost:8080/departamentos/1/organigrama');
      const updatedData = await organigramaResponse.json();
      setCurrentDepartments(updatedData.subDepartamentos || []);
  
      // Show success message
      alert('Puesto creado exitosamente');
  
    } catch (error) {
      console.error('Error:', error);
      alert('Error al crear el puesto');
    }
  };

  const handleDeletePuesto = async () => {
    if (!modalData.id_puesto) return;

    const confirmDelete = window.confirm('¿Estás seguro de que deseas eliminar este puesto?');
    if (!confirmDelete) return;

    try {
      const response = await fetch(`http://localhost:8080/puestos/${modalData.id_puesto}`, {
        method: 'DELETE',
      });

      if (!response.ok) {
        throw new Error(`Error HTTP! status: ${response.status}`);
      }

      // Resetear formulario y cerrar modal
      setModalData({
        id_puesto: null,
        nombre: '',
        paga: '',
        id_departamento: null,
        funciones: [{ nombre: '', descripcion: '' }]
      });
      setModalOpen(false);
      setIsDeleting(false);

      // Actualizar la lista de departamentos y puestos
      fetchOrganigrama();

      // Mostrar mensaje de éxito
      alert('Puesto eliminado exitosamente.');

    } catch (error) {
      console.error('Error:', error);
      alert('Error al eliminar el puesto.');
    }
  };

  const handleDepartmentClick = (dept) => {
    if (dept.subDepartamentos?.length > 0) {
      setHistory(prevHistory => [
        ...prevHistory,
        { departments: currentDepartments, level }
      ]);
      setCurrentDepartments(dept.subDepartamentos || []);
      setLevel(prevLevel => prevLevel + 1);
    }
  };

  const handleBack = () => {
    if (history.length === 0) return;
    const lastState = history[history.length - 1];
    setCurrentDepartments(lastState.departments || []);
    setLevel(lastState.level || 2);
    setHistory(prevHistory => prevHistory.slice(0, prevHistory.length - 1));
  };

  const handleLogout = () => {
    // Implementar lógica de logout, por ejemplo, limpiar tokens y redirigir
    navigate('/');
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
            <button className="nav-button" onClick={() => navigate('/menu/puestos')}>Puestos</button>
            <button className="nav-button" onClick={() => navigate('/menu/vacantes')}>Vacantes</button>
          </nav>
        </div>

        <button className="logout-button" onClick={handleLogout}>
          Cerrar sesión
        </button>
      </aside>

      {/* Main Content */}
      <main className="main-content">
        <header className="header">
          <h1 className="page-title">Puestos</h1>
          <div className="time">{time.toLocaleTimeString()}</div>
        </header>
        <div className="content-container">
          {/* Botón de Back */}
          {history.length > 0 && (
            <button className="back-button" onClick={handleBack}>
              🡸 Volver
            </button>
          )}

          {/* Organigrama */}
          <div className="organigrama">
            {currentDepartments.length > 0 ? (
              currentDepartments.map(dept => (
                <DepartamentoNode
                  key={dept.id_departamento}
                  departamento={dept}
                  level={level}
                  onDepartmentClick={handleDepartmentClick}
                  onAddPuesto={handleAddPuesto}
                  onEditPuesto={handleEditPuesto}
                />
              ))
            ) : (
              <p>No hay departamentos disponibles.</p>
            )}
          </div>
        </div>
      </main>

      {/* Modal */}
      {modalOpen && (
        <div className="modal-overlay">
          <div className="modal">
            <h2>{modalData.id_puesto ? 'Editar Puesto' : 'Agregar Puesto'}</h2>
            <form onSubmit={handleSubmit}>
              <label>
                Nombre:
                <input 
                  type="text" 
                  name="nombre" 
                  value={modalData.nombre} 
                  onChange={handleModalChange} 
                  required 
                />
              </label>
              <label>
                Paga:
                <input 
                  type="number" 
                  name="paga" 
                  value={modalData.paga} 
                  onChange={handleModalChange} 
                  required 
                />
              </label>
              
              {/* Campo Departamento */}
              {modalData.id_puesto ? (
                <label>
                  Departamento:
                  <select
                    name="id_departamento"
                    value={modalData.id_departamento}
                    onChange={handleModalChange}
                    required
                  >
                    <option value="">Seleccione un departamento</option>
                    {currentDepartments.map(dept => (
                      <option key={dept.id_departamento} value={dept.id_departamento}>
                        {dept.descripcion}
                      </option>
                    ))}
                  </select>
                </label>
              ) : (
                <label>
                  Departamento:
                  <input 
                    type="text" 
                    value={
                      currentDepartments.find(dept => dept.id_departamento === modalData.id_departamento)?.descripcion || 
                      'Departamento Seleccionado'
                    } 
                    disabled 
                  />
                </label>
              )}

              <div className="funciones">
                <h3>Funciones:</h3>
                {modalData.funciones.length > 0 ? (
                  modalData.funciones.map((funcion, index) => (
                    <div key={index} className="funcion">
                      <label>
                        Nombre:
                        <input 
                          type="text" 
                          data-field="nombre" 
                          value={funcion.nombre} 
                          onChange={(e) => handleModalChange(e, index, 'funciones')} 
                          required 
                        />
                      </label>
                      <label>
                        Descripción:
                        <input 
                          type="text" 
                          data-field="descripcion" 
                          value={funcion.descripcion} 
                          onChange={(e) => handleModalChange(e, index, 'funciones')} 
                          required 
                        />
                      </label>
                      {modalData.funciones.length > 1 && (
                        <button type="button" onClick={() => removeFuncion(index)}>Eliminar</button>
                      )}
                    </div>
                  ))
                ) : (
                  <p>No hay funciones disponibles.</p>
                )}
                <button type="button" onClick={addFuncion}>Agregar Función</button>
              </div>
              
              <div className="modal-buttons">
                {isDeleting && (
                  <button type="button" className="delete-button" onClick={handleDeletePuesto}>
                    Eliminar Puesto
                  </button>
                )}
                <button type="submit">Guardar</button>
                <button type="button" onClick={() => setModalOpen(false)}>Cancelar</button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default Puestos;