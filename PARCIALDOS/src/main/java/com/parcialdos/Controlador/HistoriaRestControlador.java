package com.parcialdos.Controlador;

import com.parcialdos.Entidades.HistoriaUsuario;
import com.parcialdos.Repositoriros.HistoriaUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historias")
public class HistoriaRestControlador {

    @Autowired
    private HistoriaUsuarioRepositorio historiaRepo;

    @GetMapping
    public List<HistoriaUsuario> listar() {
        return historiaRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<HistoriaUsuario> buscarPorId(@PathVariable String id) {
        return historiaRepo.findById(id);
    }

    @GetMapping("/proyecto/{proyectoId}")
    public List<HistoriaUsuario> listarPorProyecto(@PathVariable String proyectoId) {
        return historiaRepo.findByProyectoId(proyectoId);
    }

    @GetMapping("/backlog/{proyectoId}")
    public List<HistoriaUsuario> listarBacklog(@PathVariable String proyectoId) {
        return historiaRepo.findByProyectoIdAndSprintIdIsNull(proyectoId);
    }

    @PostMapping
    public HistoriaUsuario crear(@RequestBody HistoriaUsuario historia) {
        historia.setFechaCreacion(LocalDateTime.now());
        historia.setEstado("BACKLOG");
        return historiaRepo.save(historia);
    }

    @PutMapping("/{id}")
    public HistoriaUsuario actualizar(@PathVariable String id,
                                      @RequestBody HistoriaUsuario historia) {
        historia.setId(id);
        return historiaRepo.save(historia);
    }

    @PatchMapping("/{id}/aprobar")
    public HistoriaUsuario aprobar(@PathVariable String id) {
        HistoriaUsuario historia = historiaRepo.findById(id).orElseThrow();
        historia.setEstado("ACEPTADA");
        return historiaRepo.save(historia);
    }

    @PatchMapping("/{id}/rechazar")
    public HistoriaUsuario rechazar(@PathVariable String id,
                                    @RequestParam String observaciones) {
        HistoriaUsuario historia = historiaRepo.findById(id).orElseThrow();
        historia.setEstado("RECHAZADA");
        historia.setObservaciones(observaciones);
        return historiaRepo.save(historia);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        historiaRepo.deleteById(id);
        return "Historia eliminada correctamente";
    }
}