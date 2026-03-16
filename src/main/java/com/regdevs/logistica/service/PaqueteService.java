package com.regdevs.logistica.service;

import com.regdevs.logistica.model.Paquete;

import java.util.List;
import java.util.Optional;

public interface PaqueteService {
    List<Paquete> listarTodos();
    Optional<Paquete> buscarPorId(Long id);
    Paquete guardar(Paquete paquete);
    void eliminar(Long id);
    long contarTodos();
    long contarPorEstado(com.regdevs.logistica.model.EstadoEnum estado);
    java.util.List<com.regdevs.logistica.dto.PaqueteDTO> listarTodosDTO();
    com.regdevs.logistica.dto.PaqueteDTO buscarPorIdDTO(Long id);
}
