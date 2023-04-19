package com.prueba.transmi.service;

import com.prueba.transmi.model.*;
import com.prueba.transmi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private EstacionRepository estacionRepository;

    @Autowired
    private TrabajoRepositorio trabajoRepositorio;

    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    public List<Horario> listarHorario() {
        return horarioRepository.findAll();
    }
    public Horario recuperarHorario(Long id) {
        return horarioRepository.findById(id).orElseThrow();
    }

    public void guardarHorario(Horario horario) {
        horarioRepository.save(horario);
    }
    public Horario crearHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public Horario updateHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    public void borrarHorario(long id) {
        rutaRepository.deleteById(id);
    }
    public Horario getHorarioPorRutaId(Long rutaId) {
        return rutaRepository.findHorarioByRutaId(rutaId);
    }

    public List<Ruta> listarRuta() {
        return rutaRepository.findAll();
    }
    public Ruta recuperarRuta(Long id) {
        return rutaRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Ruta crearRuta(Ruta ruta) {
        rutaRepository.save(ruta);
        for (Estacion estacion: ruta.getEstaciones()){
            Estacion estacion2 = estacionRepository.findById(estacion.getId()).orElseThrow();
            estacion2.addRuta(ruta);
            estacionRepository.save(estacion);
        }
        Horario horario = horarioRepository.findById(ruta.getHorario().getId()).orElseThrow();
        horario.addRuta(ruta);
        horarioRepository.save(horario);
        return rutaRepository.save(ruta);
    }
    @Transactional
    public Ruta updateRuta(Ruta ruta) {
        for (Estacion estacion: ruta.getEstaciones()){
            List<Ruta> rutas = Arrays.asList(ruta);
            Estacion estacion1 = recuperarEstacion(estacion.getId());
            estacion1.setRutas(rutas);
            estacionRepository.save(estacion1);
        }
        return rutaRepository.save(ruta);
    }

    public void borrarRuta(long id) {
        rutaRepository.deleteById(id);
    }

    public List<Trabajo> listarTrabajo() {
        return trabajoRepositorio.findAll();
    }

    public List<Bus> obtenerBusesPorIdConductor(Long idConductor) {
        List<Bus> buses = new ArrayList<>();
        List<Trabajo> trabajos = trabajoRepositorio.findByConductorId(idConductor);
        for (Trabajo trabajo : trabajos) {
            Bus bus = trabajo.getBus();
            if (bus != null) {
                buses.add(bus);
            }
        }
        return buses;
    }

    public List<Estacion> getEstacionesPorRutaId(Long rutaId) {
        return rutaRepository.findEstacionesByRutaId(rutaId);
    }

    public List<Estacion> listarEstacion() {
        return estacionRepository.findAll();
    }
    public Estacion recuperarEstacion(Long id) {
        return estacionRepository.findById(id).orElseThrow();
    }

    public void guardarEstacion(Estacion estacion) {
        estacionRepository.save(estacion);
    }
    public Estacion crearEstacion(Estacion estacion) {
        return estacionRepository.save(estacion);
    }

    public Estacion updateEstacion(Estacion estacion) {
        return estacionRepository.save(estacion);
    }

    public void borrarEstacion(long id) {
        estacionRepository.deleteById(id);
    }


    /*public List<Estacion> buscarPorNombre(String textoBusqueda) {
        return personRepository.findAllByLastNameStartingWith(textoBusqueda);
        return personRepository.findAllByLastNameStartingWithIgnoreCase(textoBusqueda);
        return personRepository.findPersonsByLastNameStartingWithCaseInsensitive(textoBusqueda);
        return estacionRepository.findPersonsByLastNameStartingWith(textoBusqueda);
    }*/

}
