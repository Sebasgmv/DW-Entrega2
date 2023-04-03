package com.prueba.transmi.repository;

import com.prueba.transmi.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepositorio extends JpaRepository<Ruta, Long> {
}
