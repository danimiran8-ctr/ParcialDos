package com.parcialdos.Controlador;

import com.parcialdos.Entidades.Proyecto;
import com.parcialdos.Repositoriros.ProyectoRepositorio;
import com.parcialdos.Repositoriros.SprintRepositorio;
import com.parcialdos.Repositoriros.TareaRepositorio;
import com.parcialdos.Repositoriros.HistoriaUsuarioRepositorio;
import com.parcialdos.Repositoriros.DefectoRepositorio;
import com.parcialdos.Repositoriros.RequisitoRepositorio;
import com.parcialdos.Repositoriros.CasoUsoRepositorio;
import com.parcialdos.Repositoriros.AdrRepositorio;
import com.parcialdos.Repositoriros.StackTecnologicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proyectos")
public class ProyectoWebControlador {

    @Autowired private ProyectoRepositorio proyectoRepo;
    @Autowired private SprintRepositorio sprintRepo;
    @Autowired private TareaRepositorio tareaRepo;
    @Autowired private HistoriaUsuarioRepositorio historiaRepo;
    @Autowired private DefectoRepositorio defectoRepo;
    @Autowired private RequisitoRepositorio requisitoRepo;
    @Autowired private CasoUsoRepositorio casoUsoRepo;
    @Autowired private AdrRepositorio adrRepo;
    @Autowired private StackTecnologicoRepositorio stackRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("titulo", "Proyectos");
        model.addAttribute("currentPage", "proyectos");
        return "verProyecto";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("titulo", "Nuevo Proyecto");
        model.addAttribute("currentPage", "proyectos");
        return "formProyecto";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable String id, Model model) {
        Proyecto proyecto = proyectoRepo.findById(id).orElseThrow();
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("sprints", sprintRepo.findByProyectoId(id));
        model.addAttribute("tareas", tareaRepo.findByProyectoId(id));
        model.addAttribute("historias", historiaRepo.findByProyectoId(id));
        model.addAttribute("defectos", defectoRepo.findByProyectoId(id));
        model.addAttribute("requisitos", requisitoRepo.findByProyectoId(id));
        model.addAttribute("casosUso", casoUsoRepo.findByProyectoId(id));
        model.addAttribute("adrs", adrRepo.findByProyectoId(id));
        model.addAttribute("stacks", stackRepo.findByProyectoId(id));
        model.addAttribute("titulo", proyecto.getNombre());
        model.addAttribute("currentPage", "proyectos");
        return "detalleProyecto";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("proyecto", proyectoRepo.findById(id).orElseThrow());
        model.addAttribute("titulo", "Editar Proyecto");
        model.addAttribute("currentPage", "proyectos");
        return "formProyecto";
    }
}