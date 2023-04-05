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
    @CrossOrigin("http://localhost:4201/")
    public String listarConductores(Model model) {
        List<Conductor> conductores = coordiService.listarConductores();
        model.addAttribute("conductores", conductores);
        return "conductor-list";
    }

    @GetMapping("/edit-form/{id}")
    @CrossOrigin("http://localhost:4201/")
    public String formularioEditarPersona(Model model, @PathVariable Long id) {
        Conductor c = coordiService.recuperarConductor(id);
        model.addAttribute("conductor", c);
        return "conductor-edit";
    }

    @GetMapping("/search")
    @CrossOrigin("http://localhost:4201/")
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
    @CrossOrigin("http://localhost:4201/")
    public String guardarConductor(Conductor conductor, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "conductor-edit";
        }
        coordiService.guardarConductor(conductor);
        return "redirect:/conductor/list";
    }

//    @GetMapping("/view/{idConductor}")
//    @CrossOrigin("http://localhost:4201/")
//    String verConductor(Model model, @PathVariable("idConductor") Long id) {
//        Conductor conductor = coordiService.recuperarConductor(id);
//        model.addAttribute("conductor", conductor);
//        return "conductor-view";
//    }
    @CrossOrigin("http://localhost:4201/")
    @GetMapping("/view/{idConductor}")
    public Condu    ctor recuperarConductor(Model model, @PathVariable("idConductor") Long id) {
        return coordiService.recuperarConductor(id);
    }

//    @CrossOrigin("http://localhost:4201/")
//    @GetMapping("/{idConductor}")
//    public ResponseEntity<Conductor> recuperarConductor(@PathVariable("idConductor") Long id) {
//        // https://www.baeldung.com/spring-response-entity
//        return ResponseEntity.status(HttpStatus.OK).body(coordiService.recuperarConductor(id));
//    }

    @GetMapping(value = "/delete/{id}")
    @CrossOrigin("http://localhost:4201/")
    public String borrarConductor( @PathVariable Long id) {
        coordiService.borrarConductor(id);
        return "redirect:/conductor/list";
    }
    @GetMapping("/create")
    @CrossOrigin("http://localhost:4201/")
    public String crearConductor(Model model) {
        Conductor c = new Conductor();
        model.addAttribute("conductor", c);
        return "conductor-create";
    }

    @GetMapping("/bus")
    @CrossOrigin("http://localhost:4201/")
    public String listarBuses(Model model) {
        List<Bus> buses = coordiService.listarBuses();
        model.addAttribute("buses", buses);
        return "bus-list";
    }


    @Autowired
    ConductorRepository conductorRepository;

}
