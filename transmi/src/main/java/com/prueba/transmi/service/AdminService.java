package com.prueba.transmi.service;

import com.prueba.transmi.model.*;
import com.prueba.transmi.repository.ConductorRepository;
import com.prueba.transmi.repository.EstacionRepository;
import com.prueba.transmi.repository.RutaRepository;
import com.prueba.transmi.repository.TrabajoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private EstacionRepository estacionRepository;

    @Autowired
    private TrabajoRepositorio trabajoRepositorio;

    @Autowired
    private RutaRepository rutaRepository;

    public List<Ruta> listarRuta() {
        return rutaRepository.findAll();
    }
    public Ruta recuperarRuta(Long id) {
        return rutaRepository.findById(id).orElseThrow();
    }

    public void guardarRuta(Ruta ruta) {
        rutaRepository.save(ruta);
    }
    public Ruta crearRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    public Ruta updateRuta(Ruta ruta) {
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
