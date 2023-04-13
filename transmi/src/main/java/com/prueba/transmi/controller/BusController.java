package com.prueba.transmi.controller;

import com.prueba.transmi.model.Bus;
import com.prueba.transmi.repository.BusRepository;
import com.prueba.transmi.service.CoordiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    Logger log = LoggerFactory.getLogger(getClass());


    private CoordiService coordiService;

    public BusController(CoordiService coordiService) {
        this.coordiService = coordiService;
    }

    @GetMapping("/list")
    @CrossOrigin("http://localhost:4201/")
    public List<Bus> findAll() {
        List<Bus> buses = coordiService.listarBuses();
        return buses;
    }


    @Autowired
    BusRepository busRepository;
}
