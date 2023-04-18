package com.prueba.transmi.basededatos;

import com.prueba.transmi.model.*;
import com.prueba.transmi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataBase implements ApplicationRunner {
//    private Map<Integer, Conductor> dataConductores = new HashMap<>();

//    public DataBase(){
//        dataConductores.put(1, new Conductor("Dario", "123", "300", "Calle 1"));
//        dataConductores.put(2, new Conductor("Dario2", "123", "300", "Calle 1"));
//        dataConductores.put(3, new Conductor("Dario3", "123", "300", "Calle 1"));
//    }
//    Conductor findById(int id){
//        return dataConductores.get(id);
//    }
//    Collection<Conductor> findAll(){
//        return dataConductores.values();
//    }

//     busRepository;
    @Autowired
    ConductorRepository conductorRepository;
    @Autowired
    BusRepository busRepository;

    @Autowired
    HorarioRepository horarioRepository;

    @Autowired
    RutaRepository rutaRepository;

    @Autowired
    EstacionRepository estacionRepository;
    @Autowired
    private TrabajoRepositorio trabajoRepositorio;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Conductor conductor = new Conductor("Dario", "123", "300", "Calle 1");
        Conductor conductor1 = new Conductor("Dario2", "234", "400", "Calle 2");
        Conductor conductor2 = new Conductor("Sebas", "456", "400", "Calle 2");
        Conductor conductor3 = new Conductor("Vale", "789", "400", "Calle 2");

        Bus bus = new Bus("HBS636", "2002");
        Bus bus1 = new Bus("AAA111", "1956");
        Bus bus2 = new Bus("BBB222", "2000");

        Horario horario = new Horario("lunes, martes, miercoles","7:00 AM", "7:00 PM");
        Horario horario1 = new Horario("sabado, domingo","5:00 AM", "5:00 PM");
        Horario horario2 = new Horario("festivos","5:00 AM", "5:00 PM");

        Estacion estacion = new Estacion("Calle 45");
        Estacion estacion1 = new Estacion("Calle 39");
        Estacion estacion2 = new Estacion("Pepe Sierra");

        List estaciones = Arrays.asList(estacion);
        List estaciones1 = Arrays.asList(estacion1, estacion2);
        List estaciones2 = Arrays.asList(estacion2);


        Ruta ruta = new Ruta(estaciones, horario);
//        ruta.setEstaciones(estaciones1);
        Ruta ruta1 = new Ruta(estaciones1, horario1);
        Ruta ruta2 = new Ruta(estaciones1, horario1);

        List<Ruta> rutas = Arrays.asList(ruta);
        List<Ruta> rutas1 = Arrays.asList(ruta1);
        List<Ruta> rutas2 = Arrays.asList(ruta2);

        estacion.setRutas(rutas);
        estacion1.setRutas(rutas1);
        estacion2.setRutas(rutas2);

        Trabajo trabajo = new Trabajo();
        Trabajo trabajo1 = new Trabajo();
        Trabajo trabajo2 = new Trabajo();

        trabajo.setConductor(conductor);
        trabajo.setBus(bus);
//        trabajo.setHorario(horario);
        trabajo.setRuta(ruta);

        trabajo1.setConductor(conductor1);
        trabajo1.setBus(bus1);
        trabajo1.setRuta(ruta1);

        trabajo2.setConductor(conductor);
        trabajo2.setBus(bus2);
        trabajo2.setRuta(ruta2);

//        List<Trabajo> trabajos = Arrays.asList(trabajo);
//
////        conductor.addTrabajos(trabajo);
//        bus.addTrabajos(trabajo);
////        horario.addTrabajos(trabajos);
//        ruta.setTrabajos(trabajos);
//
//        conductorRepository.save(conductor);
//        conductorRepository.save(conductor1);
//
//        busRepository.save(bus);
//        busRepository.save(bus1);
//
//        horarioRepository.save(horario);
//        horarioRepository.save(horario1);
//
//        estacionRepositorio.save(estacion);
//        estacionRepositorio.save(estacion1);
//
//        rutaRepositorio.save(ruta);
//        rutaRepositorio.save(ruta1);

//        trabajoRepositorio.save(trabajo);
//        trabajoRepositorio.save(trabajo1);

        List<Trabajo> trabajoList = new ArrayList<>();

        trabajoList.add(trabajo);
        trabajoList.add(trabajo1);
        trabajoList.add(trabajo2);

        trabajoRepositorio.saveAll(trabajoList);

    }
}
