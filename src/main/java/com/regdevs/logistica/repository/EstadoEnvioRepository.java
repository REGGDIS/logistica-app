package com.regdevs.logistica.repository;

import com.regdevs.logistica.model.EstadoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoEnvioRepository extends JpaRepository<EstadoEnvio, Long> {
    List<EstadoEnvio> findByPaqueteIdOrderByFechaDesc(Long paqueteId);
}
