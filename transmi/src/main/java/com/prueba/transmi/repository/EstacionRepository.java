package com.prueba.transmi.repository;

import com.prueba.transmi.model.Estacion;
import com.prueba.transmi.model.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {


}
