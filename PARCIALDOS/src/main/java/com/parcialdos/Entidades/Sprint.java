package com.parcialdos.Entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sprints")
public class Sprint {

    @Id
    private String id;
    private String proyectoId;
    private Integer numero;
    private String objetivo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Integer capacidadHoras;
    private String estado;  // PLANIFICADO, ACTIVO, COMPLETADO, CANCELADO
    private Integer historiasTotal;
    private Integer historiasCompletadas;
    private Integer puntosTotales;
    private Integer puntosCompletados;
    private LocalDateTime fechaCreacion;
}