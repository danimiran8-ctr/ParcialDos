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
@Document(collection = "historias_usuario")
public class HistoriaUsuario {

    @Id
    private String id;
    private String proyectoId;
    private String sprintId;
    private String titulo;
    private String comoRol;
    private String quieroAccion;
    private String paraBeneficio;
    private String prioridadMoscow;  // MUST, SHOULD, COULD, WONT
    private Integer estimacionPuntos;
    private String estado;  // BACKLOG, EN_SPRINT, EN_PROGRESO, ACEPTADA, RECHAZADA
    private String observaciones;
    private List<String> criteriosAceptacion = new ArrayList<>();
    private LocalDateTime fechaCreacion;
}