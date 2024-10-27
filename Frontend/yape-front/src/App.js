import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import MenuPrincipal from './components/MenuPrincipal';

function App() {
  return (
    <div className="app">
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/menu" element={<MenuPrincipal />} />
      </Routes>
    </div>
  );
}

export default App;