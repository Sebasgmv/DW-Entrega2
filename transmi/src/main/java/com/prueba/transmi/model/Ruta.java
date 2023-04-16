package com.prueba.transmi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

/*    @OneToMany(mappedBy = "ruta", cascade = CascadeType.PERSIST)
    private List<Estacion> estaciones = new ArrayList<>();*/
    @JsonIgnore
    @OneToMany(mappedBy = "ruta")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Trabajo> trabajos = new ArrayList<>();

    @ManyToMany(mappedBy = "rutas", cascade = CascadeType.PERSIST)
    private List<Estacion> estaciones = new ArrayList<>();

    public List<Estacion> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<Estacion> estaciones) {
        this.estaciones = estaciones;
    }

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

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }
}
