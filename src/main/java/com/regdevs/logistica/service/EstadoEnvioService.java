package com.regdevs.logistica.service;

import com.regdevs.logistica.model.EstadoEnvio;

import java.util.List;
import java.util.Optional;

public interface EstadoEnvioService {
    List<EstadoEnvio> listarTodos();
    Optional<EstadoEnvio> buscarPorId(Long id);
    EstadoEnvio guardar(EstadoEnvio estado);
    void eliminar(Long id);
}
