package com.prueba.transmi.controller;

import com.prueba.transmi.model.Estacion;
import com.prueba.transmi.repository.EstacionRepository;
import com.prueba.transmi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estacion")
public class EstacionController {
    @Autowired
    EstacionRepository estacionRepository;
    Logger log = LoggerFactory.getLogger(getClass());
    private AdminService adminService;

    public EstacionController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/list")
    @CrossOrigin("http://localhost:4201/")
    public List<Estacion> findAll() {
        List<Estacion> estaciones = adminService.listarEstacion();
        return estaciones;
    }

    @CrossOrigin("http://localhost:4201/")
    @GetMapping("/view/{idEstacion}")
    public Estacion recuperarEstacion(Model model, @PathVariable("idEstacion") Long id) {
        return adminService.recuperarEstacion(id);
    }
    @CrossOrigin("http://localhost:4201/")
    @PostMapping("")
    public Estacion crearEstacion(@Valid @RequestBody Estacion estacion) {
        return adminService.crearEstacion(estacion);
    }

    @CrossOrigin("http://localhost:4201/")
    @PutMapping("")
    public Estacion editarEstacion(@Valid @RequestBody Estacion estacion) {
        return adminService.updateEstacion(estacion);
    }
    @CrossOrigin("http://localhost:4201/")
    @DeleteMapping("/delete/{id}")
    public void eliminarEstacion(@PathVariable Long id) {
        adminService.borrarEstacion(id);
    }
}
