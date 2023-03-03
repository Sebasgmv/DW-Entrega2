package com.prueba.transmi.repository;

import com.prueba.transmi.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepositorio extends JpaRepository<Estacion, Long> {
}
