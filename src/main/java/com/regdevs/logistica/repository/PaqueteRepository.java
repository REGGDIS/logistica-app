package com.regdevs.logistica.repository;

import com.regdevs.logistica.model.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaqueteRepository extends JpaRepository<Paquete, Long> {
    // Consultas personalizadas
}
