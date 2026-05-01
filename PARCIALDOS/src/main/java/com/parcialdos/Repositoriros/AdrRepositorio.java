package com.parcialdos.Repositoriros;

import com.parcialdos.Entidades.Adr;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdrRepositorio extends MongoRepository<Adr, String> {
    List<Adr> findByProyectoId(String proyectoId);
    List<Adr> findByProyectoIdAndEstado(String proyectoId, String estado);
    List<Adr> findByProyectoIdOrderByNumeroDesc(String proyectoId);
    long countByProyectoId(String proyectoId);
    long countByProyectoIdAndEstado(String proyectoId, String estado);
}