package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Sprint;
import com.parcialdos.Repositoriros.SprintRepositorio;
import com.parcialdos.Repositoriros.ProyectoRepositorio;
import com.parcialdos.Repositoriros.HistoriaUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sprints")
public class SprintWebControlador {

    @Autowired private SprintRepositorio sprintRepo;
    @Autowired private ProyectoRepositorio proyectoRepo;
    @Autowired private HistoriaUsuarioRepositorio historiaRepo;

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("sprint", new Sprint());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("titulo", "Nuevo Sprint");
        model.addAttribute("currentPage", "sprints");
        return "formSprint";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable String id, Model model) {
        Sprint sprint = sprintRepo.findById(id).orElseThrow();
        model.addAttribute("sprint", sprint);
        model.addAttribute("historias", historiaRepo.findBySprintId(id));
        model.addAttribute("proyecto", proyectoRepo.findById(sprint.getProyectoId()).orElse(null));
        model.addAttribute("titulo", "Sprint " + sprint.getNumero());
        model.addAttribute("currentPage", "sprints");
        return "detalleSprint";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("sprint", sprintRepo.findById(id).orElseThrow());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("titulo", "Editar Sprint");
        model.addAttribute("currentPage", "sprints");
        return "formSprint";
    }
    
    
    @GetMapping
    public String listar(Model model) {

        var sprints = sprintRepo.findAll();

        long activos = sprints.stream()
                .filter(s -> "ACTIVO".equals(s.getEstado()))
                .count();

        long completados = sprints.stream()
                .filter(s -> "COMPLETADO".equals(s.getEstado()))
                .count();

        long planificados = sprints.stream()
                .filter(s -> "PLANIFICADO".equals(s.getEstado()))
                .count();

        model.addAttribute("sprints", sprints);
        model.addAttribute("activos", activos);
        model.addAttribute("completados", completados);
        model.addAttribute("planificados", planificados);

        model.addAttribute("titulo", "Sprints");
        model.addAttribute("currentPage", "sprints");

        return "verSprint";
    }
 
}