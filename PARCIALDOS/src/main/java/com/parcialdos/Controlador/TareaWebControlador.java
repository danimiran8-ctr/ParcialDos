package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Tarea;
import com.parcialdos.Repositoriros.TareaRepositorio;
import com.parcialdos.Repositoriros.ProyectoRepositorio;
import com.parcialdos.Repositoriros.SprintRepositorio;
import com.parcialdos.Repositoriros.HistoriaUsuarioRepositorio;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tareas")
public class TareaWebControlador {

    @Autowired private TareaRepositorio tareaRepo;
    @Autowired private ProyectoRepositorio proyectoRepo;
    @Autowired private SprintRepositorio sprintRepo;
    @Autowired private HistoriaUsuarioRepositorio historiaRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tareas", tareaRepo.findAll());
        model.addAttribute("titulo", "Tareas");
        model.addAttribute("currentPage", "tareas");
        return "verTarea";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("sprints", sprintRepo.findAll());
        model.addAttribute("historias", historiaRepo.findAll());
        model.addAttribute("titulo", "Nueva Tarea");
        model.addAttribute("currentPage", "tareas");
        return "formTarea";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable String id, Model model) {
        Tarea tarea = tareaRepo.findById(id).orElseThrow();
        model.addAttribute("tarea", tarea);
        model.addAttribute("proyecto", proyectoRepo.findById(tarea.getProyectoId()).orElse(null));
        model.addAttribute("titulo", tarea.getTitulo());
        model.addAttribute("currentPage", "tareas");
        return "detalleTarea";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("tarea", tareaRepo.findById(id).orElseThrow());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("sprints", sprintRepo.findAll());
        model.addAttribute("historias", historiaRepo.findAll());
        model.addAttribute("titulo", "Editar Tarea");
        model.addAttribute("currentPage", "tareas");
        return "formTarea";
    }

    @GetMapping("/kanban")
    public String kanban(Model model) {
        List<Tarea> todas = tareaRepo.findAll();

        List<Tarea> todo       = new ArrayList<>();
        List<Tarea> enProgreso = new ArrayList<>();
        List<Tarea> review     = new ArrayList<>();
        List<Tarea> testing    = new ArrayList<>();
        List<Tarea> done       = new ArrayList<>();

        for (Tarea t : todas) {
            // ✅ CORRECCIÓN: trim() elimina espacios, toUpperCase() normaliza mayúsculas
            String estado = t.getEstado() != null ? t.getEstado().trim().toUpperCase() : "";

            System.out.println(">>> Tarea: " + t.getTitulo() + " | Estado: [" + estado + "]");

            if      (estado.equals("TODO"))        todo.add(t);
            else if (estado.equals("EN_PROGRESO")) enProgreso.add(t);
            else if (estado.equals("CODE_REVIEW")) review.add(t);
            else if (estado.equals("TESTING"))     testing.add(t);
            else if (estado.equals("DONE"))        done.add(t);
        }

        model.addAttribute("tareasTodo",        todo);
        model.addAttribute("tareasEnProgreso",  enProgreso);
        model.addAttribute("tareasReview",      review);
        model.addAttribute("tareasTesting",     testing);
        model.addAttribute("tareasDone",        done);
        model.addAttribute("titulo",            "Tablero Kanban");
        model.addAttribute("currentPage",       "kanban");
        return "kanban";
    }
}