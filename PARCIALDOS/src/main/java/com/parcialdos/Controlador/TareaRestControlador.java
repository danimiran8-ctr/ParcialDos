package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Tarea;
import com.parcialdos.Repositoriros.TareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tareas")
public class TareaRestControlador {

    @Autowired
    private TareaRepositorio tareaRepo;

    @GetMapping
    public List<Tarea> listar() {
        return tareaRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Tarea> buscarPorId(@PathVariable String id) {
        return tareaRepo.findById(id);
    }

    @GetMapping("/proyecto/{proyectoId}")
    public List<Tarea> listarPorProyecto(@PathVariable String proyectoId) {
        return tareaRepo.findByProyectoId(proyectoId);
    }

    @GetMapping("/sprint/{sprintId}")
    public List<Tarea> listarPorSprint(@PathVariable String sprintId) {
        return tareaRepo.findBySprintId(sprintId);
    }

    @PostMapping
    public Tarea crear(@RequestBody Tarea tarea) {
        tarea.setFechaCreacion(LocalDateTime.now());
        if (tarea.getEstado() == null || tarea.getEstado().isEmpty()) {
            tarea.setEstado("TODO");
        }
        return tareaRepo.save(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizar(@PathVariable String id, @RequestBody Tarea tarea) {
        // ✅ Conserva fechaCreacion original para no perderla al actualizar
        Tarea existente = tareaRepo.findById(id).orElseThrow();
        tarea.setId(id);
        tarea.setFechaCreacion(existente.getFechaCreacion());
        return tareaRepo.save(tarea);
    }

    @PatchMapping("/{id}/estado")
    public Tarea cambiarEstado(@PathVariable String id, @RequestParam String estado) {
        Tarea tarea = tareaRepo.findById(id).orElseThrow();
        tarea.setEstado(estado.trim().toUpperCase());
        return tareaRepo.save(tarea);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        tareaRepo.deleteById(id);
        return "Tarea eliminada correctamente";
    }
}
