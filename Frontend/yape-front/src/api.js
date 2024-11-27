import axios from 'axios';

// Configura la instancia de Axios con tu baseURL
const api = axios.create({
    baseURL: 'http://localhost:8080', // Cambia esta URL por la de tu backend
    timeout: 5000, // Tiempo de espera opcional
    headers: {
        'Content-Type': 'application/json', // Configura headers si es necesario
    },
});

export default api;