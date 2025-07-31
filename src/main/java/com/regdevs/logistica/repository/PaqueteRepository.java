package com.regdevs.logistica.repository;

import com.regdevs.logistica.model.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaqueteRepository extends JpaRepository<Paquete, Long> {

    @Query("SELECT p FROM Paquete p LEFT JOIN FETCH p.historial WHERE p.id = :id")
    Optional<Paquete> buscarPorIdConHistorial(@Param("id") Long id);
}
