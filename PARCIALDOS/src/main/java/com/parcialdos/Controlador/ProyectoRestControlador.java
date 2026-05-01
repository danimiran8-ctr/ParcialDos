package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Proyecto;
import com.parcialdos.Repositoriros.ProyectoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoRestControlador {

    @Autowired
    private ProyectoRepositorio proyectoRepo;

    @GetMapping
    public List<Proyecto> listar() {
        return proyectoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Proyecto> buscarPorId(@PathVariable String id) {
        return proyectoRepo.findById(id);
    }

    @PostMapping
    public Proyecto crear(@RequestBody Proyecto proyecto) {
        proyecto.setFechaCreacion(LocalDateTime.now());
        proyecto.setEstado("PLANEACION");
        proyecto.setAvancePorcentaje(0.0);
        proyecto.setPresupuestoConsumido(0.0);
        return proyectoRepo.save(proyecto);
    }

    @PutMapping("/{id}")
    public Proyecto actualizar(@PathVariable String id, @RequestBody Proyecto proyecto) {
        proyecto.setId(id);
        return proyectoRepo.save(proyecto);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        proyectoRepo.deleteById(id);
        return "Proyecto eliminado correctamente";
    }

    @PatchMapping("/{id}/estado")
    public Proyecto cambiarEstado(@PathVariable String id, @RequestParam String estado) {
        Proyecto proyecto = proyectoRepo.findById(id).orElseThrow();
        proyecto.setEstado(estado);
        return proyectoRepo.save(proyecto);
    }
}