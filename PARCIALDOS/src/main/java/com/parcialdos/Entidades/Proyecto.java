

package com.parcialdos.Entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "proyectos")
public class Proyecto {

    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private String clienteNombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double presupuesto;
    private String metodologia;   // SCRUM, KANBAN, CASCADA
    private String estado;        // PLANEACION, EN_PROGRESO, COMPLETADO, CANCELADO
    private Double avancePorcentaje = 0.0;
    private Double presupuestoConsumido = 0.0;
    private List<String> miembrosIds = new ArrayList<>();
    private LocalDateTime fechaCreacion;
    
}
