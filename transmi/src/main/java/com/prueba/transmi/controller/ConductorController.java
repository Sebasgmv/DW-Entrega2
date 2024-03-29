package com.prueba.transmi.controller;

import com.prueba.transmi.model.Bus;
import com.prueba.transmi.model.Conductor;
import com.prueba.transmi.repository.ConductorRepository;
import com.prueba.transmi.service.CoordiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/conductor")
public class ConductorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CoordiService coordiService;

    @GetMapping("/list")
    public String listarConductores(Model model) {
        List<Conductor> conductores = coordiService.listarConductores();
        model.addAttribute("conductores", conductores);
        return "conductor-list";
    }

    @GetMapping("/edit-form/{id}")
    public String formularioEditarPersona(Model model, @PathVariable Long id) {
        Conductor c = coordiService.recuperarConductor(id);
        model.addAttribute("conductor", c);
        return "conductor-edit";
    }

    @GetMapping("/search")
    public String listPersons(@RequestParam(required = false) String searchText, Model model) {
        List<Conductor> conductores;
        if (searchText == null || searchText.trim().equals("")) {
            log.info("No hay texto de búsqueda. Retornando todo");
            conductores = coordiService.listarConductores();
        } else {
            log.info("Buscando personas cuyo apellido comienza con {}", searchText);
            conductores = coordiService.buscarPorNombre(searchText);
        }
        model.addAttribute("conductores", conductores);
        return "conductor-search";
    }

    @PostMapping(value = "/save")
    public String guardarConductor(Conductor conductor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "conductor-edit";
        }
        coordiService.guardarConductor(conductor);
        return "redirect:/conductor/list";
    }

    @GetMapping("/view/{idConductor}")
    String verConductor(Model model, @PathVariable("idConductor") Long id) {
        Conductor conductor = coordiService.recuperarConductor(id);
        model.addAttribute("conductor", conductor);
        return "conductor-view";
    }

    @GetMapping(value = "/delete/{id}")
    public String borrarConductor( @PathVariable Long id) {
        coordiService.borrarConductor(id);
        return "redirect:/conductor/list";
    }

    @GetMapping("/create")
    public String crearConductor(Model model) {
        Conductor c = new Conductor();
        model.addAttribute("conductor", c);
        return "conductor-create";
    }

    @GetMapping("/bus")
    public String listarBuses(Model model) {
        List<Bus> buses = coordiService.listarBuses();
        model.addAttribute("buses", buses);
        return "bus-list";
    }


    @Autowired
    ConductorRepository conductorRepository;

}
