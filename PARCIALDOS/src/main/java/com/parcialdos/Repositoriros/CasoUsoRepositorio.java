package com.parcialdos.Repositoriros;

import com.parcialdos.Entidades.CasoUso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CasoUsoRepositorio extends MongoRepository<CasoUso, String> {
    List<CasoUso> findByProyectoId(String proyectoId);
    List<CasoUso> findByProyectoIdAndEstado(String proyectoId, String estado);
    List<CasoUso> findByProyectoIdAndActor(String proyectoId, String actor);
    long countByProyectoId(String proyectoId);
    long countByProyectoIdAndEstado(String proyectoId, String estado);
}