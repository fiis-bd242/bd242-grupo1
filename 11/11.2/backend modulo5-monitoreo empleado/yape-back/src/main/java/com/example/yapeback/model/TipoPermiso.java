package com.example.yapeback.model;

import lombok.Data;

@Data 
public class TipoPermiso {
    private Long idTipoPermiso;
    private TipoPermisoEnum nombre; // Enum: FaltaJustificada, Vacaciones, Permiso, TardanzaJustificada

    public enum TipoPermisoEnum {
        Falta_justificada("Falta justificada"),
        Vacaciones("Vacaciones"),
        Permiso("Permiso"),
        Tardanza_justificada("Tardanza justificada");

        private final String value;

        TipoPermisoEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
