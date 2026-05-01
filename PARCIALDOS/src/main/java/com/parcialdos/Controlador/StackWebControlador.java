package com.parcialdos.Controlador;

import com.parcialdos.Entidades.StackTecnologico;
import com.parcialdos.Repositoriros.StackTecnologicoRepositorio;
import com.parcialdos.Repositoriros.ProyectoRepositorio;
import com.parcialdos.Repositoriros.AdrRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stack")
public class StackWebControlador {

    @Autowired
    private StackTecnologicoRepositorio stackRepo;

    @Autowired
    private ProyectoRepositorio proyectoRepo;

    @Autowired
    private AdrRepositorio adrRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("stacks", stackRepo.findAll());
        model.addAttribute("titulo", "Stack Tecnológico");
        model.addAttribute("currentPage", "stack");
        return "verStack";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("stack", new StackTecnologico());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("titulo", "Nueva Tecnología");
        model.addAttribute("currentPage", "stack");
        return "formStack";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("stack", stackRepo.findById(id).orElseThrow());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("titulo", "Editar Tecnología");
        model.addAttribute("currentPage", "stack");
        return "formStack";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("stacks", stackRepo.findAll());
        model.addAttribute("adrs", adrRepo.findAll());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("titulo", "Dashboard de Arquitectura");
        model.addAttribute("currentPage", "arquitectura");
        return "dashboardArquitectura";
    }
}