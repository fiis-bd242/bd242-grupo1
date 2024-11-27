import api from '../api'; // Asegúrate de que api.js tenga la configuración para hacer peticiones

// src/service/IncidenteService.js

const API_BASE_URL = 'http://localhost:8080/incidentes';  // Cambia la URL base si es necesario

// Función para crear un incidente
export const crearIncidente = async (incidente) => {
  try {
    const response = await fetch(`${API_BASE_URL}/crear-incidente`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(incidente),
    });

    if (response.ok) {
      return await response.text();  // Regresa el mensaje de éxito
    } else {
      throw new Error('Error al registrar el incidente');
    }
  } catch (error) {
    console.error(error);
    return error.message;
  }
};

// Función para obtener todos los incidentes
export const obtenerIncidentes = async () => {
  try {
    const response = await fetch(`${API_BASE_URL}/obtener-incidentes`);
    if (response.ok) {
      return await response.json();  // Regresa los datos de los incidentes
    } else {
      throw new Error('Error al obtener los incidentes');
    }
  } catch (error) {
    console.error(error);
    return null;
  }
};

// Función para eliminar un incidente
export const eliminarIncidente = async (id) => {
  try {
    const response = await fetch(`${API_BASE_URL}/eliminar-incidente/${id}`, {
      method: 'DELETE',
    });

    if (response.ok) {
      return await response.text();
    } else {
      throw new Error('Error al eliminar el incidente');
    }
  } catch (error) {
    console.error(error);
    return error.message;
  }
};
