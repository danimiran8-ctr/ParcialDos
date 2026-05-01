package com.parcialdos.Controlador;

import com.parcialdos.Repositoriros.ProyectoRepositorio;
import com.parcialdos.Repositoriros.TareaRepositorio;
import com.parcialdos.Repositoriros.SprintRepositorio;
import com.parcialdos.Repositoriros.DefectoRepositorio;
import com.parcialdos.Repositoriros.HistoriaUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControlador {

    @Autowired
    private ProyectoRepositorio proyectoRepo;

    @Autowired
    private TareaRepositorio tareaRepo;

    @Autowired
    private SprintRepositorio sprintRepo;

    @Autowired
    private DefectoRepositorio defectoRepo;

    @Autowired
    private HistoriaUsuarioRepositorio historiaRepo;

    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Estadísticas generales
        model.addAttribute("totalProyectos", proyectoRepo.count());
        model.addAttribute("proyectosActivos", proyectoRepo.countByEstado("EN_PROGRESO"));
        model.addAttribute("proyectosCompletados", proyectoRepo.countByEstado("COMPLETADO"));
        model.addAttribute("totalTareas", tareaRepo.count());
        model.addAttribute("tareasDone", tareaRepo.countByProyectoIdAndEstado(null, "DONE"));
        model.addAttribute("totalDefectos", defectoRepo.count());
        model.addAttribute("defectosCriticos", defectoRepo.countByProyectoIdAndSeveridad(null, "CRITICO"));
        model.addAttribute("totalHistorias", historiaRepo.count());
        // Listas para mostrar en dashboard
        model.addAttribute("proyectos", proyectoRepo.findAll());
        return "dashboard";
    }
}