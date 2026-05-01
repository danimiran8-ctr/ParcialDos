package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Requisito;
import com.parcialdos.Repositoriros.RequisitoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/requisitos")
public class RequisitoRestControlador {

    @Autowired
    private RequisitoRepositorio requisitoRepo;

    @GetMapping
    public List<Requisito> listar() {
        return requisitoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Requisito> buscarPorId(@PathVariable String id) {
        return requisitoRepo.findById(id);
    }

    @GetMapping("/proyecto/{proyectoId}")
    public List<Requisito> listarPorProyecto(@PathVariable String proyectoId) {
        return requisitoRepo.findByProyectoId(proyectoId);
    }

    @GetMapping("/proyecto/{proyectoId}/tipo/{tipo}")
    public List<Requisito> listarPorTipo(@PathVariable String proyectoId,
                                          @PathVariable String tipo) {
        return requisitoRepo.findByProyectoIdAndTipo(proyectoId, tipo);
    }

    @PostMapping
    public Requisito crear(@RequestBody Requisito requisito) {
        requisito.setFechaCreacion(LocalDateTime.now());
        requisito.setFechaActualizacion(LocalDateTime.now());
        if (requisito.getVersion() == null) {
            requisito.setVersion(1);
        }
        if (requisito.getEstado() == null) {
            requisito.setEstado("BORRADOR");
        }
        return requisitoRepo.save(requisito);
    }

    @PutMapping("/{id}")
    public Requisito actualizar(@PathVariable String id,
                                 @RequestBody Requisito requisito) {
        requisito.setId(id);
        requisito.setFechaActualizacion(LocalDateTime.now());
        return requisitoRepo.save(requisito);
    }

    @PatchMapping("/{id}/estado")
    public Requisito cambiarEstado(@PathVariable String id,
                                    @RequestParam String estado) {
        Requisito requisito = requisitoRepo.findById(id).orElseThrow();
        requisito.setEstado(estado);
        requisito.setFechaActualizacion(LocalDateTime.now());
        return requisitoRepo.save(requisito);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        requisitoRepo.deleteById(id);
        return "Requisito eliminado correctamente";
    }
}