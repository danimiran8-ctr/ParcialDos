package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Defecto;
import com.parcialdos.Repositoriros.DefectoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/defectos")
public class DefectoRestControlador {

    @Autowired
    private DefectoRepositorio defectoRepo;

    @GetMapping
    public List<Defecto> listar() {
        return defectoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Defecto> buscarPorId(@PathVariable String id) {
        return defectoRepo.findById(id);
    }

    @GetMapping("/proyecto/{proyectoId}")
    public List<Defecto> listarPorProyecto(@PathVariable String proyectoId) {
        return defectoRepo.findByProyectoId(proyectoId);
    }

    @PostMapping
    public Defecto crear(@RequestBody Defecto defecto) {
        defecto.setFechaReporte(LocalDateTime.now());
        defecto.setEstado("NUEVO");
        return defectoRepo.save(defecto);
    }

    @PutMapping("/{id}")
    public Defecto actualizar(@PathVariable String id, @RequestBody Defecto defecto) {
        defecto.setId(id);
        return defectoRepo.save(defecto);
    }

    @PatchMapping("/{id}/estado")
    public Defecto cambiarEstado(@PathVariable String id, @RequestParam String estado) {
        Defecto defecto = defectoRepo.findById(id).orElseThrow();
        defecto.setEstado(estado);
        if ("CERRADO".equals(estado)) {
            defecto.setFechaResolucion(LocalDateTime.now());
        }
        return defectoRepo.save(defecto);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        defectoRepo.deleteById(id);
        return "Defecto eliminado correctamente";
    }
}
