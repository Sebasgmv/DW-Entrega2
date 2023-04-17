package com.prueba.transmi.repository;

import com.prueba.transmi.model.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajoRepositorio extends JpaRepository<Trabajo, Long> {

    List<Trabajo> findByConductorId(Long idConductor);
}
