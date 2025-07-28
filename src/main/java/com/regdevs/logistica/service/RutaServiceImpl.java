package com.regdevs.logistica.service;

import com.regdevs.logistica.model.Ruta;
import com.regdevs.logistica.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RutaServiceImpl implements RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    @Override
    public List<Ruta> listarTodas() {
        return rutaRepository.findAll();
    }

    @Override
    public Optional<Ruta> buscarPorId(Long id) {
        return rutaRepository.findById(id);
    }

    @Override
    public Ruta guardar(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    @Override
    public void eliminar(Long id) {
        rutaRepository.deleteById(id);
    }
}
