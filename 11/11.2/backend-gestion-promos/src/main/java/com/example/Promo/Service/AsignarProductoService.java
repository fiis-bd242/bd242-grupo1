package com.example.Promo.Service;

import com.example.Promo.Models.AsignarProductoModel;
import com.example.Promo.Repository.AsignarProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignarProductoService {

    @Autowired
    private AsignarProductoRepository asignarProductoRepository;

    // Obtener tabla de promociones
    public List<Object[]> obtenerPromociones() {
        return asignarProductoRepository.obtenerPromociones();
    }

    // Obtener tabla de productos
    public List<Object[]> obtenerProductos() {
        return asignarProductoRepository.obtenerProductos();
    }

    // Obtener tabla de productoxseller
    public List<Object[]> obtenerProductoxSeller() {
        return asignarProductoRepository.obtenerProductoxSeller();
    }

    // Asignar productos a una promoción
    public void asignarProductos(Integer codPromocion, List<Integer> codigosProductos) {
        for (Integer codProducto : codigosProductos) {
            AsignarProductoModel asignarProductoModel = new AsignarProductoModel(codPromocion, codProducto);
            asignarProductoRepository.asignarProducto(asignarProductoModel);
        }
    }
}