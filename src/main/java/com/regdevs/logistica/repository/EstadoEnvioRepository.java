package com.regdevs.logistica.repository;

import com.regdevs.logistica.model.EstadoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoEnvioRepository extends JpaRepository<EstadoEnvio, Long> {
}
