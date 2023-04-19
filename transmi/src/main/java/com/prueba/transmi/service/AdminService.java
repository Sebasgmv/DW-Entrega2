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


    public Ruta crearRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    @Transactional
    public void asociarHorario(Long idRuta, Long idHorario){
        Ruta ruta = rutaRepository.findById(idRuta).orElseThrow();
        Horario horario = horarioRepository.findById(idHorario).orElseThrow();

        ruta.setHorario(horario);
        horario.addRuta(ruta);

        rutaRepository.save(ruta);
        horarioRepository.save(horario);
    }

    @Transactional
    public void asociarEstacion(Long idRuta, Long idEstacion){
        Ruta ruta = rutaRepository.findById(idRuta).orElseThrow();
        Estacion estacion = estacionRepository.findById(idEstacion).orElseThrow();

        ruta.addEstacion(estacion);
        estacion.addRuta(ruta);

        rutaRepository.save(ruta);
        estacionRepository.save(estacion);
    }
    @Transactional
    public Ruta updateRuta(Ruta ruta) {
        rutaRepository.save(ruta);
        asociarHorario(ruta.getId(), ruta.getHorario().getId());
        for (Estacion estacion: ruta.getEstaciones()){
            asociarEstacion(ruta.getId(), estacion.getId());
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
