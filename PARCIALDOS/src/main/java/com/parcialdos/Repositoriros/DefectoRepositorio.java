package com.parcialdos.Repositoriros;

import com.parcialdos.Entidades.Defecto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DefectoRepositorio extends MongoRepository<Defecto, String> {
    List<Defecto> findByProyectoId(String proyectoId);
    List<Defecto> findByProyectoIdAndEstado(String proyectoId, String estado);
    List<Defecto> findByProyectoIdAndSeveridad(String proyectoId, String severidad);
    long countByProyectoIdAndEstado(String proyectoId, String estado);
    long countByProyectoIdAndSeveridad(String proyectoId, String severidad);
}