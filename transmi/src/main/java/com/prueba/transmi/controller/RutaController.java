package com.prueba.transmi.controller;

import com.prueba.transmi.model.Bus;
import com.prueba.transmi.model.Estacion;
import com.prueba.transmi.model.Ruta;
import com.prueba.transmi.repository.RutaRepository;
import com.prueba.transmi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ruta")
public class RutaController {
    @Autowired
    RutaRepository rutaRepository;
    Logger log = LoggerFactory.getLogger(getClass());
    private AdminService adminService;

    public RutaController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/list")
    @CrossOrigin("http://localhost:4201/")
    public List<Ruta> findAll() {
        List<Ruta> rutas = adminService.listarRuta();
        return rutas;
    }

    @CrossOrigin("http://localhost:4201/")
    @GetMapping("/view/{idRuta}")
    public Ruta recuperarRuta(Model model, @PathVariable("idRuta") Long id) {
        return adminService.recuperarRuta(id);
    }
    @CrossOrigin("http://localhost:4201/")
    @PostMapping("")
    public Ruta crearRuta(@Valid @RequestBody Ruta ruta) {
        log.info(ruta.toString());
        return adminService.crearRuta(ruta);
    }

    @CrossOrigin("http://localhost:4201/")
    @PutMapping("")
    public Ruta editarRuta(@Valid @RequestBody Ruta ruta) {
        return adminService.updateRuta(ruta);
    }
    @CrossOrigin("http://localhost:4201/")
    @DeleteMapping("/delete/{id}")
    public void eliminarRuta(@PathVariable Long id) {
        adminService.borrarRuta(id);
    }

    @GetMapping("/view2/{idRuta}")
    @CrossOrigin("http://localhost:4201/")
    public List<Estacion> obtenerEstacionesPorId(@PathVariable Long idRuta) {
        List<Estacion> estacion = adminService.getEstacionesPorRutaId(idRuta);
        return estacion;
    }

}
