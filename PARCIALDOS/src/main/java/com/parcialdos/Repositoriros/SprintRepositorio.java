package com.parcialdos.Repositoriros;

import com.parcialdos.Entidades.Sprint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SprintRepositorio extends MongoRepository<Sprint, String> {
    List<Sprint> findByProyectoId(String proyectoId);
    List<Sprint> findByProyectoIdOrderByNumeroDesc(String proyectoId);
    Optional<Sprint> findByProyectoIdAndEstado(String proyectoId, String estado);
}