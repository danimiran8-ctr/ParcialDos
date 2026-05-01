package com.parcialdos.Controlador;

import com.parcialdos.Entidades.StackTecnologico;
import com.parcialdos.Repositoriros.StackTecnologicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stack")
public class StackRestControlador {

    @Autowired
    private StackTecnologicoRepositorio stackRepo;

    @GetMapping
    public List<StackTecnologico> listar() {
        return stackRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<StackTecnologico> buscarPorId(@PathVariable String id) {
        return stackRepo.findById(id);
    }

    @GetMapping("/proyecto/{proyectoId}")
    public List<StackTecnologico> listarPorProyecto(@PathVariable String proyectoId) {
        return stackRepo.findByProyectoId(proyectoId);
    }

    @PostMapping
    public StackTecnologico crear(@RequestBody StackTecnologico stack) {
        stack.setFechaCreacion(LocalDateTime.now());
        stack.setFechaActualizacion(LocalDateTime.now());
        if (stack.getEstado() == null) {
            stack.setEstado("ACTIVO");
        }
        return stackRepo.save(stack);
    }

    @PutMapping("/{id}")
    public StackTecnologico actualizar(@PathVariable String id,
                                        @RequestBody StackTecnologico stack) {
        stack.setId(id);
        stack.setFechaActualizacion(LocalDateTime.now());
        return stackRepo.save(stack);
    }

    @PatchMapping("/{id}/estado")
    public StackTecnologico cambiarEstado(@PathVariable String id,
                                           @RequestParam String estado) {
        StackTecnologico stack = stackRepo.findById(id).orElseThrow();
        stack.setEstado(estado);
        stack.setFechaActualizacion(LocalDateTime.now());
        return stackRepo.save(stack);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        stackRepo.deleteById(id);
        return "Tecnología eliminada correctamente";
    }
}