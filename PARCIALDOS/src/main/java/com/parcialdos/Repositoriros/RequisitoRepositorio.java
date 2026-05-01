package com.parcialdos.Repositoriros;

import com.parcialdos.Entidades.Requisito;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequisitoRepositorio extends MongoRepository<Requisito, String> {
    List<Requisito> findByProyectoId(String proyectoId);
    List<Requisito> findByProyectoIdAndTipo(String proyectoId, String tipo);
    List<Requisito> findByProyectoIdAndEstado(String proyectoId, String estado);
    long countByProyectoId(String proyectoId);
    long countByProyectoIdAndTipo(String proyectoId, String tipo);
    long countByProyectoIdAndEstado(String proyectoId, String estado);
}