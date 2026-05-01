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
@Document(collection = "casos_uso")
public class CasoUso {

    @Id
    private String id;

    private String proyectoId;
    private String codigo;         // CU-01, CU-02
    private String nombre;
    private String actor;
    private String descripcion;
    private String precondiciones;
    private String postcondiciones;
    private String flujoBasico;
    private String flujosAlternos;
    private String flujoExcepcion;
    private String estado;         // BORRADOR, REVISION, APROBADO
    private String prioridad;      // ALTA, MEDIA, BAJA

    private List<String> requisitosVinculados = new ArrayList<>();

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
