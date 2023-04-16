package com.prueba.transmi.controller;

import com.prueba.transmi.model.Bus;
import com.prueba.transmi.model.Conductor;
import com.prueba.transmi.repository.BusRepository;
import com.prueba.transmi.service.CoordiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    BusRepository busRepository;
    Logger log = LoggerFactory.getLogger(getClass());
    private CoordiService coordiService;

    public BusController(CoordiService coordiService) {
        this.coordiService = coordiService;
    }

    @GetMapping("/list")
    @CrossOrigin("http://localhost:4201/")
    public List<Bus> findAll() {
        List<Bus> buses = coordiService.listarBuses();
        return buses;
    }

    @CrossOrigin("http://localhost:4201/")
    @GetMapping("/view/{idBus}")
    public Bus recuperarBus(Model model, @PathVariable("idBus") Long id) {
        return coordiService.recuperarBus(id);
    }
    @CrossOrigin("http://localhost:4201/")
    @PostMapping("")
    public Bus crearBus(@Valid @RequestBody Bus bus) {
        return coordiService.crearBus(bus);
    }

    @CrossOrigin("http://localhost:4201/")
    @PutMapping("")
    public Bus editarBus(@Valid @RequestBody Bus bus) {
        return coordiService.updateBus(bus);
    }
    @CrossOrigin("http://localhost:4201/")
    @DeleteMapping("/delete/{id}")
    public void eliminarBus(@PathVariable Long id) {
        coordiService.borrarBus(id);
    }
}
