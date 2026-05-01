package com.parcialdos.Entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "defectos")
public class Defecto {

    @Id
    private String id;
    private String proyectoId;
    private String tareaId;
    private String titulo;
    private String descripcion;
    private String pasosReproduccion;
    private String resultadoEsperado;
    private String resultadoObtenido;
    private String severidad;   // CRITICO, MAYOR, MENOR, TRIVIAL
    private String prioridad;   // URGENTE, ALTA, MEDIA, BAJA
    private String estado;      // NUEVO, ASIGNADO, RESUELTO, CERRADO, REABIERTO
    private String ambiente;    // DEV, STAGING, PRODUCCION
    private String reportadoPorNombre;
    private String asignadoANombre;
    private LocalDateTime fechaReporte;
    private LocalDateTime fechaResolucion;
}