import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Login.css'; 

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();
  
    const handleSubmit = (e) => {
      e.preventDefault();
      // Aquí puedes agregar la lógica para autenticar al usuario
      // Por ejemplo, puedes hacer una solicitud a un servidor para verificar las credenciales
      if (username === 'Modulo3' && password === '12345') {
        navigate('/menu');
      } else {
        setError('Credenciales incorrectas');
      }
    };
  
    return (
      <div className="split-screen">
        <div className="left-side">
          <div className="imagenes">
                <img alt="Logo yape" height="250" src="https://socialpubli.com/blog/wp-content/uploads/2021/06/logo.png"/>
            </div>
        </div>
        <div className="right-side">
          <div className="login-container">
            <h1>Login</h1>
            <form onSubmit={handleSubmit}>
              <label>
                Usuario:
                <br />
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
              </label>
              <label>
                Contraseña:
                <br />
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
              </label>
              <br />
              <button type="submit">Iniciar sesión</button>
              {error && <p className="error">{error}</p>}
              <br />
              <a href="/register">¿Olvidaste tu contraseña?</a>
            </form>
          </div>
        </div>
      </div>
    );
  };
  
  export default Login;