import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Sidebar from "./components/SideBar";
import Promociones from "./components/Promociones"; // Ruta a la página de gestión
import CrearPromocion from "./components/CrearPromocion"; // Ruta a la página de crear promoción
import PromocionesActivas from "./components/PromocionesActivas"; // Nueva ruta
import ProductosPorPromocion from "./components/ProductosPorPromocion"; // Nueva ruta
import EditarPromocion from "./components/EditarPromocion";
import ProductosActivos from "./components/ProductosActivos";


function App() {
  return (
    <Router>
      {/* Diseño general que incluye el Sidebar */}
      <div className="layout">
        <Sidebar /> {/* Sidebar fijo */}
        <div className="contenido">
          {/* Rutas dinámicas */}
          <Routes>
            <Route path="/" element={<Promociones />} />
            <Route path="/crear-promocion" element={<CrearPromocion />} />
            <Route path="/promociones-activas" element={<PromocionesActivas />} />
            <Route path="/productos-por-promocion" element={<ProductosPorPromocion />} />
            <Route path="/editar-promocion/:cod_promocion" element={<EditarPromocion />} />
            <Route path="/productos-activos/:codPromocion" element={<ProductosActivos />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
