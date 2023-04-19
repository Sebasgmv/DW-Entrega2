package com.prueba.transmi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
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

//    @JsonIgnore
//    @ManyToMany(mappedBy = "rutas")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private List<Estacion> estaciones = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "horario_id")
    private Horario horario;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "ruta_estaciones",
            joinColumns = @JoinColumn(name = "ruta_id"),
            inverseJoinColumns = @JoinColumn(name = "estaciones_id"))
    private List<Estacion> estaciones = new ArrayList<>();

    public List<Estacion> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<Estacion> estaciones) {
        this.estaciones = estaciones;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Ruta(List<Estacion> estaciones, Horario horario) {
        this.estaciones = estaciones;
        this.horario = horario;
    }
    public Ruta(Ruta ruta) {
        this.estaciones = ruta.getEstaciones();
        this.horario = ruta.getHorario();
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

    public boolean addEstacion(Estacion e) {
        return estaciones.add(e);
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "id=" + id +
                ", horario=" + horario +
                ", estaciones=" + estaciones +
                '}';
    }
}
