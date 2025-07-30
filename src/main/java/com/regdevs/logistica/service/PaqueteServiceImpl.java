package com.regdevs.logistica.service;

import com.regdevs.logistica.model.Paquete;
import com.regdevs.logistica.model.Ruta;
import com.regdevs.logistica.repository.PaqueteRepository;
import com.regdevs.logistica.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaqueteServiceImpl implements PaqueteService {

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Override
    public List<Paquete> listarTodos() {
        return paqueteRepository.findAll();
    }

    @Override
    public Optional<Paquete> buscarPorId(Long id) {
        return paqueteRepository.findById(id);
    }

    @Override
    public Paquete guardar(Paquete paquete) {
        return paqueteRepository.save(paquete);
    }

    @Override
    public void eliminar(Long id) {
        paqueteRepository.deleteById(id);
    }
}
