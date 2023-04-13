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

    @OneToMany(mappedBy = "ruta", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "estacion_id")
    private List<Estacion> estaciones = new ArrayList<>();

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

    public Ruta(List<Estacion> estaciones) {
        this.estaciones = estaciones;
    }

    public List<Estacion> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<Estacion> estacions) {
        this.estaciones = estacions;
    }

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }
}
