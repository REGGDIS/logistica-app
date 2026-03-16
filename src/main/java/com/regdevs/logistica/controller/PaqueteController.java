package com.regdevs.logistica.controller;

import com.regdevs.logistica.model.EstadoEnum;
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

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

            // Lista de estados disponibles (usando el Enum)
            model.addAttribute("estadosDisponibles", EstadoEnum.values());
            return "detalle-paquete";
        } else {
            return "redirect:/paquetes";
        }
    }

    @GetMapping("/")
    public String redirigir() {
        return "redirect:/paquetes";
    }

    @PostMapping("/paquetes/guardar")
    public String guardarPaquete(@ModelAttribute("paquete") Paquete paquete, RedirectAttributes redirectAttributes) {
        if (paquete.getDescripcion() == null || paquete.getDescripcion().isBlank()) {
            redirectAttributes.addFlashAttribute("error", "La descripción es obligatoria.");
            return "redirect:/paquetes/nuevo";
        }

        if (paquete.getRuta() != null && paquete.getRuta().getId() != null) {
            paquete.setRuta(rutaService.buscarPorId(paquete.getRuta().getId()).orElse(null));
        }
        
        paqueteService.guardar(paquete);
        redirectAttributes.addFlashAttribute("success", "Paquete guardado correctamente.");
        return "redirect:/paquetes";
    }

    @PostMapping("/paquetes/eliminar/{id}")
    public String eliminarPaquete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        paqueteService.eliminar(id);
        redirectAttributes.addFlashAttribute("success", "Paquete eliminado correctamente.");
        return "redirect:/paquetes";
    }
}
