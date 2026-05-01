package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Requisito;
import com.parcialdos.Repositoriros.RequisitoRepositorio;
import com.parcialdos.Repositoriros.ProyectoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/requisitos")
public class RequisitoWebControlador {

    @Autowired
    private RequisitoRepositorio requisitoRepo;

    @Autowired
    private ProyectoRepositorio proyectoRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("requisitos", requisitoRepo.findAll());
        model.addAttribute("titulo", "Requisitos");
        model.addAttribute("currentPage", "requisitos");
        return "verRequisito";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("requisito", new Requisito());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("titulo", "Nuevo Requisito");
        model.addAttribute("currentPage", "requisitos");
        return "formRequisito";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("requisito", requisitoRepo.findById(id).orElseThrow());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("titulo", "Editar Requisito");
        model.addAttribute("currentPage", "requisitos");
        return "formRequisito";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable String id, Model model) {
        Requisito requisito = requisitoRepo.findById(id).orElseThrow();
        model.addAttribute("requisito", requisito);
        model.addAttribute("proyecto", proyectoRepo.findById(
                requisito.getProyectoId() != null ? requisito.getProyectoId() : "").orElse(null));
        model.addAttribute("titulo", requisito.getNombre());
        model.addAttribute("currentPage", "requisitos");
        return "detalleRequisito";
    }
}