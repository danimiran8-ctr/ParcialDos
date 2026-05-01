package com.parcialdos.Entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "requisitos")
public class Requisito {

    @Id
    private String id;

    private String proyectoId;
    private String codigo;        // RF-01, RNF-01
    private String tipo;          // FUNCIONAL, NO_FUNCIONAL
    private String nombre;
    private String descripcion;
    private String prioridad;     // ALTA, MEDIA, BAJA
    private String estado;        // BORRADOR, REVISION, APROBADO, OBSOLETO
    private Integer version;
    private String analistaNombre;
    private String fuente;        // De dónde viene el requisito

    private List<String> historiasVinculadas = new ArrayList<>();
    private List<String> casosUsoVinculados = new ArrayList<>();
    private List<VersionHistorial> historialVersiones = new ArrayList<>();

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VersionHistorial {
        private Integer version;
        private String descripcionCambio;
        private String usuarioNombre;
        private LocalDateTime fecha;
    }
}
