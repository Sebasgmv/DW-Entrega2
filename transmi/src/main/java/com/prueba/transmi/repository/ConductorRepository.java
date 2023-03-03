package com.prueba.transmi.repository;

import com.prueba.transmi.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {
//    List<Conductor> findAllByLastName(String text);
//
//    List<Conductor> findAllByLastNameStartingWith(String text);
//
//    List<Conductor> findAllByLastNameStartingWithIgnoreCase(String text);

    // https://www.baeldung.com/spring-data-jpa-query
    @Query("SELECT p FROM Conductor p WHERE p.nombre LIKE concat(:text, '%')")
    List<Conductor> findPersonsByLastNameStartingWith(@Param("text") String text);

//    @Query("SELECT p FROM Conductor p WHERE LOWER(p.cedula) LIKE LOWER(concat(:text,'%'))")
//    List<Conductor> findPersonsByLastNameStartingWithCaseInsensitive(@Param("text") String text);

}
