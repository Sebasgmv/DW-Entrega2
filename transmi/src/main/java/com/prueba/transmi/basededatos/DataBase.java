package com.prueba.transmi.basededatos;

import com.prueba.transmi.model.Bus;
import com.prueba.transmi.model.Conductor;
import com.prueba.transmi.model.Horario;
import com.prueba.transmi.model.Ruta;
import com.prueba.transmi.repository.BusRepository;
import com.prueba.transmi.repository.ConductorRepository;
import com.prueba.transmi.repository.HorarioRepository;
import com.prueba.transmi.repository.RutaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class DataBase implements ApplicationRunner {
    private Map<Integer, Conductor> dataConductores = new HashMap<>();

//    public DataBase(){
//        dataConductores.put(1, new Conductor("Dario", "123", "300", "Calle 1"));
//        dataConductores.put(2, new Conductor("Dario2", "123", "300", "Calle 1"));
//        dataConductores.put(3, new Conductor("Dario3", "123", "300", "Calle 1"));
//    }
    Conductor findById(int id){
        return dataConductores.get(id);
    }
    Collection<Conductor> findAll(){
        return dataConductores.values();
    }

//     busRepository;
    @Autowired
    ConductorRepository conductorRepository;
    @Autowired
    BusRepository busRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    RutaRepositorio rutaRepositorio;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        conductorRepository.save(new Conductor("Dario", "123", "300", "Calle 1"));
        conductorRepository.save(new Conductor("Dario2", "234", "400", "Calle 2"));

        busRepository.save(new Bus("HBS636", "2002"));
        busRepository.save(new Bus("HBS222", "1956"));

        horarioRepository.save(new Horario());
        horarioRepository.save(new Horario());

        rutaRepositorio.save(new Ruta())
    }
}
