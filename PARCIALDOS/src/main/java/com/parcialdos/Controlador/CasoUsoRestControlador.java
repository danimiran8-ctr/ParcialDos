package com.parcialdos.Controlador;

import com.parcialdos.Entidades.CasoUso;
import com.parcialdos.Repositoriros.CasoUsoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/casosuso")
public class CasoUsoRestControlador {

    @Autowired
    private CasoUsoRepositorio casoUsoRepo;

    @GetMapping
    public List<CasoUso> listar() {
        return casoUsoRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<CasoUso> buscarPorId(@PathVariable String id) {
        return casoUsoRepo.findById(id);
    }

    @GetMapping("/proyecto/{proyectoId}")
    public List<CasoUso> listarPorProyecto(@PathVariable String proyectoId) {
        return casoUsoRepo.findByProyectoId(proyectoId);
    }

    @PostMapping
    public CasoUso crear(@RequestBody CasoUso casoUso) {
        casoUso.setFechaCreacion(LocalDateTime.now());
        casoUso.setFechaActualizacion(LocalDateTime.now());
        if (casoUso.getEstado() == null) {
            casoUso.setEstado("BORRADOR");
        }
        return casoUsoRepo.save(casoUso);
    }

    @PutMapping("/{id}")
    public CasoUso actualizar(@PathVariable String id,
                               @RequestBody CasoUso casoUso) {
        casoUso.setId(id);
        casoUso.setFechaActualizacion(LocalDateTime.now());
        return casoUsoRepo.save(casoUso);
    }

    @PatchMapping("/{id}/estado")
    public CasoUso cambiarEstado(@PathVariable String id,
                                  @RequestParam String estado) {
        CasoUso casoUso = casoUsoRepo.findById(id).orElseThrow();
        casoUso.setEstado(estado);
        casoUso.setFechaActualizacion(LocalDateTime.now());
        return casoUsoRepo.save(casoUso);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        casoUsoRepo.deleteById(id);
        return "Caso de uso eliminado correctamente";
    }
}