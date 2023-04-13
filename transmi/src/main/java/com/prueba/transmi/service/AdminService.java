package com.prueba.transmi.service;

import com.prueba.transmi.model.Bus;
import com.prueba.transmi.model.Conductor;
import com.prueba.transmi.model.Estacion;
import com.prueba.transmi.repository.ConductorRepository;
import com.prueba.transmi.repository.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private EstacionRepository estacionRepository;

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
