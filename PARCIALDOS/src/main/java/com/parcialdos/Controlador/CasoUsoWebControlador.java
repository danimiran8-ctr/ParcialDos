package com.parcialdos.Controlador;

import com.parcialdos.Entidades.CasoUso;
import com.parcialdos.Repositoriros.CasoUsoRepositorio;
import com.parcialdos.Repositoriros.ProyectoRepositorio;
import com.parcialdos.Repositoriros.RequisitoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/casosuso")
public class CasoUsoWebControlador {

    @Autowired
    private CasoUsoRepositorio casoUsoRepo;

    @Autowired
    private ProyectoRepositorio proyectoRepo;

    @Autowired
    private RequisitoRepositorio requisitoRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("casosUso", casoUsoRepo.findAll());
        model.addAttribute("titulo", "Casos de Uso");
        model.addAttribute("currentPage", "casosuso");
        return "verCasoUso";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("casoUso", new CasoUso());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("requisitos", requisitoRepo.findAll());
        model.addAttribute("titulo", "Nuevo Caso de Uso");
        model.addAttribute("currentPage", "casosuso");
        return "formCasoUso";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable String id, Model model) {
        model.addAttribute("casoUso", casoUsoRepo.findById(id).orElseThrow());
        model.addAttribute("proyectos", proyectoRepo.findAll());
        model.addAttribute("requisitos", requisitoRepo.findAll());
        model.addAttribute("titulo", "Editar Caso de Uso");
        model.addAttribute("currentPage", "casosuso");
        return "formCasoUso";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable String id, Model model) {
        CasoUso casoUso = casoUsoRepo.findById(id).orElseThrow();
        model.addAttribute("casoUso", casoUso);
        model.addAttribute("proyecto", proyectoRepo.findById(
                casoUso.getProyectoId() != null ? casoUso.getProyectoId() : "").orElse(null));
        model.addAttribute("requisitos", requisitoRepo.findAll());
        model.addAttribute("titulo", casoUso.getNombre());
        model.addAttribute("currentPage", "casosuso");
        return "detalleCasoUso";
    }
}