package com.prueba.transmi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estacion {
    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = true)
    private String nombre;

    @JsonIgnore
    @ManyToMany(mappedBy = "estaciones")
    private List<Ruta> rutas = new ArrayList<>();

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }
//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "estacion_rutas",
//            joinColumns = @JoinColumn(name = "estacion_id"),
//            inverseJoinColumns = @JoinColumn(name = "rutas_id"))
//    private List<Ruta> rutas = new ArrayList<>();

    /*@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;*/



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estacion() {
    }

    public Estacion(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean addRuta(Ruta r) {
        return rutas.add(r);
    }
/*public Ruta getRuta() {
        return ruta;
    }
    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }*/
}
