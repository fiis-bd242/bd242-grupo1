package com.example.Promo.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearPromocionModel {
    private String fecha_inicio;
    private String fecha_fin;
    private double dscto;
    private double precio_final;
    private boolean estado_promo;
    private String dscrip_promo;
    private int id_empleado;
}