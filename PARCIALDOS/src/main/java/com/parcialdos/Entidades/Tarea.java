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
@Document(collection = "tareas")
public class Tarea {

    @Id
    private String id;
    private String proyectoId;
    private String historiaId;
    private String sprintId;
    private String titulo;
    private String descripcion;
    private String tipo;       // FRONTEND, BACKEND, QA, DEVOPS
    private String prioridad;  // CRITICA, ALTA, MEDIA, BAJA
    private String estado;     // TODO, EN_PROGRESO, CODE_REVIEW, TESTING, DONE
    private String asignadoNombre;
    private Double horasEstimadas;
    private Double horasReales;
    private LocalDate fechaLimite;
    private LocalDateTime fechaCreacion;
}