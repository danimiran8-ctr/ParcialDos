package com.parcialdos.Repositoriros;

import com.parcialdos.Entidades.Proyecto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProyectoRepositorio extends MongoRepository<Proyecto, String> {
    List<Proyecto> findByEstado(String estado);
    long countByEstado(String estado);
}