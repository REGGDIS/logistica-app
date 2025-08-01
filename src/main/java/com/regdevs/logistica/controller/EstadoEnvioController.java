package com.regdevs.logistica.controller;

import com.regdevs.logistica.model.EstadoEnvio;
import com.regdevs.logistica.model.Paquete;
import com.regdevs.logistica.service.EstadoEnvioService;
import com.regdevs.logistica.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estados")
public class EstadoEnvioController {

    @Autowired
    private EstadoEnvioService estadoEnvioService;

    @Autowired
    private PaqueteService paqueteService;

    @PostMapping("/guardar")
    public String guardarEstado(@RequestParam("estado") String estado,
                                @RequestParam("fecha") String fecha,
                                @RequestParam("paqueteId") Long paqueteId) {

        // Lista de estados válidos
        List<String> estadosValidos = Arrays.asList(
                "En tránsito", "En bodega", "En reparto", "Entregado", "Rechazado"
        );

        if (!estadosValidos.contains(estado)) {
            // Redirigir o mostrar error si el estado no es válido
            return "redirect:/paquetes/detalle/" + paqueteId + "?error=estado_invalido";
        }

        Optional<Paquete> paqueteOpt = paqueteService.buscarPorId(paqueteId);

        if (paqueteOpt.isPresent()) {
            EstadoEnvio nuevoEstado = new EstadoEnvio();
            nuevoEstado.setEstado(estado);
            nuevoEstado.setFecha(LocalDate.parse(fecha));
            nuevoEstado.setPaquete(paqueteOpt.get());

            estadoEnvioService.guardar(nuevoEstado);
        }

        return "redirect:/paquetes/detalle/" + paqueteId;
    }
}
