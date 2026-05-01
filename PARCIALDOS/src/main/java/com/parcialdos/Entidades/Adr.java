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
@Document(collection = "adrs")
public class Adr {

    @Id
    private String id;

    private String proyectoId;
    private Integer numero;
    private String titulo;
    private String contexto;
    private String decision;
    private String consecuencias;
    private String alternativasEvaluadas;
    private String estado;        // PROPUESTA, ACEPTADA, OBSOLETA, RECHAZADA
    private String arquitectoNombre;
    private LocalDateTime fechaDecision;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}