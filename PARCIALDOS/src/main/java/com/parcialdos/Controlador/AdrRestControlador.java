package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Adr;
import com.parcialdos.Repositoriros.AdrRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adrs")
public class AdrRestControlador {

    @Autowired
    private AdrRepositorio adrRepo;

    @GetMapping
    public List<Adr> listar() {
        return adrRepo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Adr> buscarPorId(@PathVariable String id) {
        return adrRepo.findById(id);
    }

    @GetMapping("/proyecto/{proyectoId}")
    public List<Adr> listarPorProyecto(@PathVariable String proyectoId) {
        return adrRepo.findByProyectoIdOrderByNumeroDesc(proyectoId);
    }

    @PostMapping
    public Adr crear(@RequestBody Adr adr) {
        adr.setFechaCreacion(LocalDateTime.now());
        adr.setFechaActualizacion(LocalDateTime.now());
        adr.setFechaDecision(LocalDateTime.now());
        if (adr.getEstado() == null) {
            adr.setEstado("PROPUESTA");
        }
        List<Adr> existentes = adrRepo.findByProyectoId(adr.getProyectoId());
        adr.setNumero(existentes.size() + 1);
        return adrRepo.save(adr);
    }

    @PutMapping("/{id}")
    public Adr actualizar(@PathVariable String id, @RequestBody Adr adr) {
        adr.setId(id);
        adr.setFechaActualizacion(LocalDateTime.now());
        return adrRepo.save(adr);
    }

    @PatchMapping("/{id}/estado")
    public Adr cambiarEstado(@PathVariable String id, @RequestParam String estado) {
        Adr adr = adrRepo.findById(id).orElseThrow();
        adr.setEstado(estado);
        adr.setFechaActualizacion(LocalDateTime.now());
        return adrRepo.save(adr);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable String id) {
        adrRepo.deleteById(id);
        return "ADR eliminado correctamente";
    }
}