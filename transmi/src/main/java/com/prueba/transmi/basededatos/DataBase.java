package com.prueba.transmi.basededatos;

import com.prueba.transmi.model.Conductor;
import com.prueba.transmi.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    ConductorRepository conductorRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        conductorRepository.save(new Conductor("Dario", "123", "300", "Calle 1"));
        conductorRepository.save(new Conductor("Dario2", "234", "400", "Calle 2"));

    }
}
