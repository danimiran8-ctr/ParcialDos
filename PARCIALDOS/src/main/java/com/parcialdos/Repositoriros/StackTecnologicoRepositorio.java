package com.parcialdos.Repositoriros;

import com.parcialdos.Entidades.StackTecnologico;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StackTecnologicoRepositorio extends MongoRepository<StackTecnologico, String> {
    List<StackTecnologico> findByProyectoId(String proyectoId);
    List<StackTecnologico> findByProyectoIdAndCapa(String proyectoId, String capa);
    List<StackTecnologico> findByProyectoIdAndEstado(String proyectoId, String estado);
    long countByProyectoId(String proyectoId);
    long countByProyectoIdAndCapa(String proyectoId, String capa);
}