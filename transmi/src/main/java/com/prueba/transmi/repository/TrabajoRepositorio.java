package com.prueba.transmi.repository;

import com.prueba.transmi.model.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajoRepositorio extends JpaRepository<Trabajo, Long> {
}
