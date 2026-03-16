package com.regdevs.logistica.service;

import com.regdevs.logistica.model.EstadoEnvio;
import com.regdevs.logistica.repository.EstadoEnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoEnvioServiceImpl implements EstadoEnvioService {

    @Autowired
    private EstadoEnvioRepository estadoEnvioRepository;

    @Override
    public List<EstadoEnvio> listarTodos() {
        return estadoEnvioRepository.findAll();
    }

    @Override
    public Optional<EstadoEnvio> buscarPorId(Long id) {
        if (id == null) return Optional.empty();
        return estadoEnvioRepository.findById(id);
    }

    @Override
    public EstadoEnvio guardar(EstadoEnvio estado) {
        return estadoEnvioRepository.save(java.util.Objects.requireNonNull(estado));
    }

    @Override
    public void eliminar(Long id) {
        if (id != null) {
            estadoEnvioRepository.deleteById(id);
        }
    }

    @Override
    public List<EstadoEnvio> buscarPorPaqueteIdOrdenado(Long paqueteId) {
        return estadoEnvioRepository.findByPaqueteIdOrderByFechaDesc(paqueteId);
    }
}
