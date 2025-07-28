package com.regdevs.logistica.service;

import com.regdevs.logistica.model.Ruta;

import java.util.List;
import java.util.Optional;

public interface RutaService {
    List<Ruta> listarTodas();
    Optional<Ruta> buscarPorId(Long id);
    Ruta guardar(Ruta ruta);
    void eliminar(Long id);
}
