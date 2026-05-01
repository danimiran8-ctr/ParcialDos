package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Defecto;
import com.parcialdos.Repositoriros.DefectoRepositorio;
import com.parcialdos.Repositoriros.ProyectoRepositorio;
import com.parcialdos.Repositoriros.TareaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/defectos")
public class DefectoWebControlador {

    @Autowired private DefectoRepositorio defectoRepo;
    @Autowired private ProyectoRepositorio proyectoRepo;
    @Autowired private TareaRepositorio tareaRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("defectos", defectoRepo.findAll());
        model.addAttribute("titulo", "Defectos");
        model.addAttribute("currentPage", "defectos");
        return "verDefecto";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("defecto", new Defecto());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("tareas", tareaRepo.findAll());
        model.addAttribute("titulo", "Reportar Defecto");
        model.addAttribute("currentPage", "defectos");
        return "formDefecto";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable String id, Model model) {
        Defecto defecto = defectoRepo.findById(id).orElseThrow();
        model.addAttribute("defecto", defecto);
        model.addAttribute("proyecto", proyectoRepo.findById(defecto.getProyectoId()).orElse(null));
        model.addAttribute("titulo", defecto.getTitulo());
        model.addAttribute("currentPage", "defectos");
        return "detalleDefecto";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("defecto", defectoRepo.findById(id).orElseThrow());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("tareas", tareaRepo.findAll());
        model.addAttribute("titulo", "Editar Defecto");
        model.addAttribute("currentPage", "defectos");
        return "formDefecto";
    }
}