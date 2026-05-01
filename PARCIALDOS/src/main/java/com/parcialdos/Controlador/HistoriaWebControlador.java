package com.parcialdos.Controlador;

import com.parcialdos.Entidades.HistoriaUsuario;
import com.parcialdos.Repositoriros.HistoriaUsuarioRepositorio;
import com.parcialdos.Repositoriros.ProyectoRepositorio;
import com.parcialdos.Repositoriros.SprintRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/historias")
public class HistoriaWebControlador {

    @Autowired private HistoriaUsuarioRepositorio historiaRepo;
    @Autowired private ProyectoRepositorio proyectoRepo;
    @Autowired private SprintRepositorio sprintRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("historias", historiaRepo.findAll());
        model.addAttribute("titulo", "Historias de Usuario");
        model.addAttribute("currentPage", "historias");
        return "verHistoria";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("historia", new HistoriaUsuario());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("sprints", sprintRepo.findAll());
        model.addAttribute("titulo", "Nueva Historia");
        model.addAttribute("currentPage", "historias");
        return "formHistoria";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable String id, Model model) {
        HistoriaUsuario historia = historiaRepo.findById(id).orElseThrow();
        model.addAttribute("historia", historia);
        model.addAttribute("proyecto", proyectoRepo.findById(historia.getProyectoId()).orElse(null));
        model.addAttribute("titulo", historia.getTitulo());
        model.addAttribute("currentPage", "historias");
        return "detalleHistoria";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("historia", historiaRepo.findById(id).orElseThrow());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("sprints", sprintRepo.findAll());
        model.addAttribute("titulo", "Editar Historia");
        model.addAttribute("currentPage", "historias");
        return "formHistoria";
    }
}