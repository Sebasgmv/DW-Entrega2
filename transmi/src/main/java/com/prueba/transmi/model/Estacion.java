package com.prueba.transmi.model;


import javax.persistence.*;

@Entity
public class Estacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
