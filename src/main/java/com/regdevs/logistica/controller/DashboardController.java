package com.regdevs.logistica.controller;

import com.regdevs.logistica.model.EstadoEnum;
import com.regdevs.logistica.service.PaqueteService;
import com.regdevs.logistica.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private RutaService rutaService;

    @GetMapping("/")
    public String dashboard(Model model) {
        // Estadísticas generales
        model.addAttribute("totalPaquetes", paqueteService.contarTodos());
        model.addAttribute("totalRutas", rutaService.contarTodas());

        // Estadísticas por estado
        Map<String, Long> statsPorEstado = new HashMap<>();
        for (EstadoEnum estado : EstadoEnum.values()) {
            statsPorEstado.put(estado.name(), paqueteService.contarPorEstado(estado));
        }
        model.addAttribute("stats", statsPorEstado);
        
        // Para usar las descripciones en el frontend
        model.addAttribute("estados", EstadoEnum.values());

        return "index";
    }
}
