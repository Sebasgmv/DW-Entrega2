package com.prueba.transmi.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String placa;
    private String modelo;

    @OneToMany(mappedBy = "bus")
    private List<Trabajo> trabajos = new ArrayList<>();

    public Bus() {
    }

    public Bus(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    public boolean addTrabajos(Trabajo trabajo) {
        return trabajos.add(trabajo);
    }
}
