package com.regdevs.logistica.controller;

import com.regdevs.logistica.model.EstadoEnvio;
import com.regdevs.logistica.model.Paquete;
import com.regdevs.logistica.service.EstadoEnvioService;
import com.regdevs.logistica.service.PaqueteService;
import com.regdevs.logistica.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private RutaService rutaService;

    @Autowired
    private EstadoEnvioService estadoEnvioService;

    @GetMapping("/paquetes")
    public String listarPaquetes(Model model) {
        List<Paquete> listaPaquetes = paqueteService.listarTodos();
        model.addAttribute("paquetes", listaPaquetes);
        return "paquetes"; // Nombre del archivo .html en /templates
    }

    @GetMapping("/paquetes/nuevo")
    public String mostrarFormularioNuevoPaquete(Model model) {
        model.addAttribute("paquete", new Paquete());
        model.addAttribute("rutas", rutaService.listarTodas());
        return "formulario-paquete";
    }

    @GetMapping("/paquetes/editar/{id}")
    public String mostrarFormularioEditarPaquete(@PathVariable Long id, Model model) {
        Optional<Paquete> paqueteOptional = paqueteService.buscarPorId(id);
        if (paqueteOptional.isPresent()) {
            model.addAttribute("paquete", paqueteOptional.get());
            model.addAttribute("rutas", rutaService.listarTodas());
            return "formulario-paquete";
        } else {
            return "redirect:/paquetes"; // En caso de que no exista el ID
        }
    }

    @GetMapping("/paquetes/detalle/{id}")
    public String verDetallePaquete(@PathVariable Long id, Model model) {
        Optional<Paquete> paqueteOptional = paqueteService.buscarPorId(id);
        if (paqueteOptional.isPresent()) {
            Paquete paquete = paqueteOptional.get();
            model.addAttribute("paquete", paquete);
            // Obtiene historial ordenado por fecha descendente
            List<EstadoEnvio> historial = estadoEnvioService.buscarPorPaqueteIdOrdenado(paquete.getId());
            model.addAttribute("historial", historial);

            // Lista de estados disponibles
            List<String> estadosDisponibles = Arrays.asList(
                    "En tránsito", "En bodega", "En reparto", "Entregado", "Rechazado"
            );
            model.addAttribute("estadosDisponibles", estadosDisponibles);
            return "detalle-paquete";
        } else {
            return "redirect:/paquetes";
        }
    }

    @PostMapping("/paquetes/guardar")
    public String guardarPaquete(@ModelAttribute("paquete") Paquete paquete) {
        if (paquete.getRuta() != null && paquete.getRuta().getId() != null) {
            // Busca la Ruta completa desde la base de datos
            paquete.setRuta(rutaService.buscarPorId(paquete.getRuta().getId()).orElse(null));
        }
        paqueteService.guardar(paquete);
        return "redirect:/paquetes";
    }

    @PostMapping("/paquetes/actualizar")
    public String actualizarPaquete(@ModelAttribute("paquete") Paquete paquete) {
        if (paquete.getRuta() != null && paquete.getRuta().getId() != null) {
            // Busca la Ruta completa desde la base de datos
            paquete.setRuta(rutaService.buscarPorId(paquete.getRuta().getId()).orElse(null));
        }
        paqueteService.guardar(paquete); // El metodo guardar() actualiza si ya tiene ID
        return "redirect:/paquetes";
    }

    @PostMapping("/paquetes/eliminar/{id}")
    public String eliminarPaquete(@PathVariable Long id) {
        paqueteService.eliminar(id);
        return "redirect:/paquetes";
    }
}
