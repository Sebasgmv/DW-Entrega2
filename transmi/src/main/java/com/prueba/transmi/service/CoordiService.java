package com.prueba.transmi.service;

import com.prueba.transmi.model.Bus;
import com.prueba.transmi.model.Conductor;
import com.prueba.transmi.repository.BusRepository;
import com.prueba.transmi.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordiService {

    @Autowired
    private ConductorRepository conductorRepository;

    @Autowired
    private BusRepository busRepository;

    public List<Conductor> listarConductores() {
        return conductorRepository.findAll();
    }

    public List<Bus> listarBuses() {
        return busRepository.findAll();
    }

    public Conductor recuperarConductor(Long id) {
        return conductorRepository.findById(id).orElseThrow();
    }

    public void guardarConductor(Conductor conductor) {
        conductorRepository.save(conductor);
    }

    public Conductor editarConductor(Conductor conductor) {
        return conductorRepository.save(conductor);
    }

    public void borrarConductor(long id) {
        conductorRepository.deleteById(id);
    }

    public List<Conductor> buscarPorNombre(String textoBusqueda) {
        // return personRepository.findAllByLastNameStartingWith(textoBusqueda);
        // return personRepository.findAllByLastNameStartingWithIgnoreCase(textoBusqueda);
        // return personRepository.findPersonsByLastNameStartingWithCaseInsensitive(textoBusqueda);
        return conductorRepository.findPersonsByLastNameStartingWith(textoBusqueda);
    }

}
