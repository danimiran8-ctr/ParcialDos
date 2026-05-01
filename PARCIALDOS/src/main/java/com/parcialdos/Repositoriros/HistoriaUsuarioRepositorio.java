package com.parcialdos.Repositoriros;

import com.parcialdos.Entidades.HistoriaUsuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistoriaUsuarioRepositorio extends MongoRepository<HistoriaUsuario, String> {
    List<HistoriaUsuario> findByProyectoId(String proyectoId);
    List<HistoriaUsuario> findBySprintId(String sprintId);
    List<HistoriaUsuario> findByProyectoIdAndEstado(String proyectoId, String estado);
    List<HistoriaUsuario> findByProyectoIdAndSprintIdIsNull(String proyectoId);
    long countByProyectoIdAndEstado(String proyectoId, String estado);
}