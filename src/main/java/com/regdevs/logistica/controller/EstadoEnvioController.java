package com.regdevs.logistica.controller;

import com.regdevs.logistica.model.EstadoEnum;
import com.regdevs.logistica.model.EstadoEnvio;
import com.regdevs.logistica.model.Paquete;
import com.regdevs.logistica.service.EstadoEnvioService;
import com.regdevs.logistica.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Controller
@RequestMapping("/estados")
public class EstadoEnvioController {

    @Autowired
    private EstadoEnvioService estadoEnvioService;

    @Autowired
    private PaqueteService paqueteService;

    @PostMapping("/guardar")
    public String guardarEstado(@RequestParam("estado") String estadoStr,
                                @RequestParam("fecha") String fechaStr,
                                @RequestParam("paqueteId") Long paqueteId,
                                RedirectAttributes redirectAttributes) {

        Optional<Paquete> paqueteOpt = paqueteService.buscarPorId(paqueteId);
        if (paqueteOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Paquete no encontrado.");
            return "redirect:/paquetes";
        }

        try {
            EstadoEnum estado = EstadoEnum.valueOf(estadoStr);
            LocalDate fecha = LocalDate.parse(fechaStr);

            EstadoEnvio nuevoEstado = new EstadoEnvio();
            nuevoEstado.setEstado(estado);
            nuevoEstado.setFecha(fecha);
            nuevoEstado.setPaquete(paqueteOpt.get());

            estadoEnvioService.guardar(nuevoEstado);
            redirectAttributes.addFlashAttribute("success", "Estado actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Estado de envío no válido.");
        } catch (DateTimeParseException e) {
            redirectAttributes.addFlashAttribute("error", "Fecha con formato inválido.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error inesperado: " + e.getMessage());
        }

        return "redirect:/paquetes/detalle/" + paqueteId;
    }
}
