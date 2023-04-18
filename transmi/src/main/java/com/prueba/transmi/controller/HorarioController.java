package com.prueba.transmi.controller;

import com.prueba.transmi.model.Bus;
import com.prueba.transmi.model.Estacion;
import com.prueba.transmi.model.Horario;
import com.prueba.transmi.model.Ruta;
import com.prueba.transmi.repository.BusRepository;
import com.prueba.transmi.repository.HorarioRepository;
import com.prueba.transmi.repository.RutaRepository;
import com.prueba.transmi.service.AdminService;
import com.prueba.transmi.service.CoordiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/horario")
public class HorarioController {
    @Autowired
    HorarioRepository horarioRepository;
    Logger log = LoggerFactory.getLogger(getClass());
    private AdminService adminService;

    public HorarioController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/list")
    @CrossOrigin("http://localhost:4201/")
    public List<Horario> findAll() {
        List<Horario> horarios = adminService.listarHorario();
        return horarios;
    }

    @CrossOrigin("http://localhost:4201/")
    @GetMapping("/view/{idHorario}")
    public Horario recuperarHorario(Model model, @PathVariable("idHorario") Long id) {
        return adminService.recuperarHorario(id);
    }
    @CrossOrigin("http://localhost:4201/")
    @PostMapping("")
    public Horario crearHorario(@Valid @RequestBody Horario horario) {
        return adminService.crearHorario(horario);
    }

    @CrossOrigin("http://localhost:4201/")
    @PutMapping("")
    public Horario editarHorario(@Valid @RequestBody Horario horario) {
        return adminService.updateHorario(horario);
    }
    @CrossOrigin("http://localhost:4201/")
    @DeleteMapping("/delete/{id}")
    public void eliminarHorario(@PathVariable Long id) {
        adminService.borrarHorario(id);
    }

    @GetMapping("/view2/{idRuta}")
    @CrossOrigin("http://localhost:4201/")
    public Horario obtenerHorariosPorId(@PathVariable Long idRuta) {
        Horario horario = adminService.getHorarioPorRutaId(idRuta);
        return horario;
    }
}
