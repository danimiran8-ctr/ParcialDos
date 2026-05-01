package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Sprint;
import com.parcialdos.Repositoriros.SprintRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sprints")
public class SprintRestControlador {

    @Autowired
    private SprintRepositorio sprintRepo;

    @GetMapping
    public List<Sprint> listar() {
        return sprintRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Sprint> buscarPorId(@PathVariable String id) {
        return sprintRepo.findById(id);
    }

    @GetMapping("/proyecto/{proyectoId}")
    public List<Sprint> listarPorProyecto(@PathVariable String proyectoId) {
        return sprintRepo.findByProyectoIdOrderByNumeroDesc(proyectoId);
    }

    @PostMapping
    public Sprint crear(@RequestBody Sprint sprint) {
        sprint.setFechaCreacion(LocalDateTime.now());
        sprint.setEstado("PLANIFICADO");
        List<Sprint> existentes = sprintRepo.findByProyectoId(sprint.getProyectoId());
        sprint.setNumero(existentes.size() + 1);
        return sprintRepo.save(sprint);
    }

    @PutMapping("/{id}")
    public Sprint actualizar(@PathVariable String id, @RequestBody Sprint sprint) {
        sprint.setId(id);
        return sprintRepo.save(sprint);
    }

    @PatchMapping("/{id}/iniciar")
    public Sprint iniciar(@PathVariable String id) {
        Sprint sprint = sprintRepo.findById(id).orElseThrow();
        sprint.setEstado("ACTIVO");
        return sprintRepo.save(sprint);
    }

    @PatchMapping("/{id}/completar")
    public Sprint completar(@PathVariable String id) {
        Sprint sprint = sprintRepo.findById(id).orElseThrow();
        sprint.setEstado("COMPLETADO");
        return sprintRepo.save(sprint);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        sprintRepo.deleteById(id);
        return "Sprint eliminado correctamente";
    }
}