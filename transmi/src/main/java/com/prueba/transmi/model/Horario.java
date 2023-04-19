package com.prueba.transmi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String dias;
    private String HoraInicio;
    private String HoraFin;

   /* @ManyToMany
    @JoinTable(name = "horario_rutas",
            joinColumns = @JoinColumn(name = "horario_id"),
            inverseJoinColumns = @JoinColumn(name = "rutas_id"))
    private List<Ruta> rutas = new ArrayList<>();*/

    @JsonIgnore
    @OneToMany(mappedBy = "horario", orphanRemoval = true)
    private List<Ruta> rutas = new ArrayList<>();

    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }

    /*public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        this.rutas = rutas;
    }
*/

/*    @OneToMany(mappedBy = "horario")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Trabajo> trabajos = new ArrayList<>();*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Horario() {
    }

    public Horario(String horaInicio, String horaFin) {
        Date date = new Date();
        HoraInicio = horaInicio;
        HoraFin = horaFin;
        dias = date.toString();
    }

    public Horario(String dias, String horaInicio, String horaFin) {
        this.dias = dias;
        HoraInicio = horaInicio;
        HoraFin = horaFin;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(String horaFin) {
        HoraFin = horaFin;
    }

    public boolean addRuta(Ruta r) {
        return rutas.add(r);
    }
}
