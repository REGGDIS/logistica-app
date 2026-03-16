package com.regdevs.logistica.service;

import com.regdevs.logistica.model.Paquete;
import com.regdevs.logistica.repository.PaqueteRepository;
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
        if (id == null) return Optional.empty();
        return paqueteRepository.buscarPorIdConHistorial(id);
    }

    @Override
    public Paquete guardar(Paquete paquete) {
        return paqueteRepository.save(java.util.Objects.requireNonNull(paquete));
    }

    @Override
    public void eliminar(Long id) {
        if (id != null) {
            paqueteRepository.findById(id).ifPresent(paquete -> {
                paqueteRepository.delete(java.util.Objects.requireNonNull(paquete));
            });
        }
    }

    @Override
    public long contarTodos() {
        return paqueteRepository.count();
    }

    @Override
    public long contarPorEstado(com.regdevs.logistica.model.EstadoEnum estado) {
        return paqueteRepository.countByEstado(estado);
    }

    @Override
    public java.util.List<com.regdevs.logistica.dto.PaqueteDTO> listarTodosDTO() {
        return listarTodos().stream()
                .map(this::convertirADTO)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public com.regdevs.logistica.dto.PaqueteDTO buscarPorIdDTO(Long id) {
        return buscarPorId(id)
                .map(this::convertirADTO)
                .orElse(null);
    }

    private com.regdevs.logistica.dto.PaqueteDTO convertirADTO(Paquete paquete) {
        com.regdevs.logistica.dto.PaqueteDTO dto = new com.regdevs.logistica.dto.PaqueteDTO();
        dto.setId(paquete.getId());
        dto.setDescripcion(paquete.getDescripcion());
        dto.setDestinatario(paquete.getDestinatario());
        dto.setDireccionEntrega(paquete.getDireccionEntrega());
        dto.setEmailDestinatario(paquete.getEmailDestinatario());
        if (paquete.getRuta() != null) {
            dto.setRutaId(paquete.getRuta().getId());
            dto.setRutaNombre(paquete.getRuta().getOrigen() + " -> " + paquete.getRuta().getDestino());
        }
        if (paquete.getHistorial() != null && !paquete.getHistorial().isEmpty()) {
            dto.setUltimoEstado(paquete.getHistorial().get(0).getEstado());
        }
        return dto;
    }
}
