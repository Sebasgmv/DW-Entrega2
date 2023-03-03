package com.prueba.transmi.repository;

import com.prueba.transmi.model.Bus;
import com.prueba.transmi.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    @Query("SELECT p FROM Bus p WHERE p.placa LIKE concat(:text, '%')")
    List<Bus> findBusByPlaca(@Param("text") String text);
}
