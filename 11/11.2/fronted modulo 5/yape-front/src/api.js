import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080', // Cambia esta URL por la de tu backend
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json',
  },
});

export default api;
