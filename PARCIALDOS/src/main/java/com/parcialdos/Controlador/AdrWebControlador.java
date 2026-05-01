package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Adr;
import com.parcialdos.Repositoriros.AdrRepositorio;
import com.parcialdos.Repositoriros.ProyectoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/adrs")
public class AdrWebControlador {

    @Autowired
    private AdrRepositorio adrRepo;

    @Autowired
    private ProyectoRepositorio proyectoRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("adrs", adrRepo.findAll());
        model.addAttribute("titulo", "ADR - Decisiones Arquitectónicas");
        model.addAttribute("currentPage", "adrs");
        return "verAdr";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("adr", new Adr());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("titulo", "Nueva Decisión Arquitectónica");
        model.addAttribute("currentPage", "adrs");
        return "formAdr";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("adr", adrRepo.findById(id).orElseThrow());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("titulo", "Editar ADR");
        model.addAttribute("currentPage", "adrs");
        return "formAdr";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable String id, Model model) {
        Adr adr = adrRepo.findById(id).orElseThrow();
        model.addAttribute("adr", adr);
        model.addAttribute("proyecto", proyectoRepo.findById(
                adr.getProyectoId() != null ? adr.getProyectoId() : "").orElse(null));
        model.addAttribute("titulo", "ADR-" + adr.getNumero() + " " + adr.getTitulo());
        model.addAttribute("currentPage", "adrs");
        return "detalleAdr";
    }
}