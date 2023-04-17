package com.prueba.transmi.controller;

import com.prueba.transmi.model.Bus;
import com.prueba.transmi.model.Conductor;
import com.prueba.transmi.model.Ruta;
import com.prueba.transmi.model.Trabajo;
import com.prueba.transmi.repository.RutaRepository;
import com.prueba.transmi.repository.TrabajoRepositorio;
import com.prueba.transmi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabajo")
public class TrabajoController {
    @Autowired
    TrabajoRepositorio trabajoRepositorio;
    Logger log = LoggerFactory.getLogger(getClass());
    private AdminService adminService;

    public TrabajoController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/list")
    @CrossOrigin("http://localhost:4201/")
    public List<Trabajo> findAll() {
        List<Trabajo> trabajos = adminService.listarTrabajo();
        return trabajos;
    }

    @GetMapping("/view/{idConductor}")
    @CrossOrigin("http://localhost:4201/")
    public List<Bus> obtenerBusesPorIdConductor(@PathVariable Long idConductor) {
        List<Bus> buses = adminService.obtenerBusesPorIdConductor(idConductor);
        return buses;
    }
}
