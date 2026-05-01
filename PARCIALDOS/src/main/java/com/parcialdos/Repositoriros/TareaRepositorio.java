package com.parcialdos.Repositoriros;

import com.parcialdos.Entidades.Tarea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TareaRepositorio extends MongoRepository<Tarea, String> {
    List<Tarea> findByProyectoId(String proyectoId);
    List<Tarea> findBySprintId(String sprintId);
    List<Tarea> findByHistoriaId(String historiaId);
    List<Tarea> findByProyectoIdAndEstado(String proyectoId, String estado);
    long countByProyectoIdAndEstado(String proyectoId, String estado);
}