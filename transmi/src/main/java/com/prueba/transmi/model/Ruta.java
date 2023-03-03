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

    private String codigo;
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
}
