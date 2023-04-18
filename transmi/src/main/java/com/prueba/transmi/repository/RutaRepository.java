package com.prueba.transmi.repository;

import com.prueba.transmi.model.Estacion;
import com.prueba.transmi.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    @Query("SELECT r.estaciones FROM Ruta r WHERE r.id = :rutaId")
    List<Estacion> findEstacionesByRutaId(@Param("rutaId") Long rutaId);
}
