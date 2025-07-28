package com.regdevs.logistica.service;

import com.regdevs.logistica.model.Paquete;

import java.util.List;
import java.util.Optional;

public interface PaqueteService {
    List<Paquete> listarTodos();
    Optional<Paquete> buscarPorId(Long id);
    Paquete guardar(Paquete paquete);
    void eliminar(Long id);
}
