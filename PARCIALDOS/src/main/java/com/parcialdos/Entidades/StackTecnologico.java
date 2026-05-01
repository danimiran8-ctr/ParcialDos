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
@Document(collection = "stack_tecnologico")
public class StackTecnologico {

    @Id
    private String id;

    private String proyectoId;
    private String nombre;
    private String capa;          // FRONTEND, BACKEND, BASE_DATOS, INFRAESTRUCTURA, SEGURIDAD, DEVOPS
    private String version;
    private String descripcion;
    private String justificacion;
    private String estado;        // ACTIVO, DEPRECADO, EN_EVALUACION
    private String arquitectoNombre;
    private List<String> alternativas = new ArrayList<>();
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}