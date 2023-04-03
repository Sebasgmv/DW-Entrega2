package com.prueba.transmi.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "ruta")
    private List<Estacion> estacions = new ArrayList<>();

    @OneToMany(mappedBy = "ruta")
    private List<Trabajo> trabajos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ruta() {
    }

    public Ruta(List<Estacion> estacions) {
        this.estacions = estacions;
    }

    public List<Estacion> getEstacions() {
        return estacions;
    }

    public void setEstacions(List<Estacion> estacions) {
        this.estacions = estacions;
    }

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }
}
