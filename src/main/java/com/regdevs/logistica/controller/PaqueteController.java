package com.regdevs.logistica.controller;

import com.regdevs.logistica.model.Paquete;
import com.regdevs.logistica.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @GetMapping("/paquetes")
    public String listarPaquetes(Model model) {
        List<Paquete> listaPaquetes = paqueteService.listarTodos();
        model.addAttribute("paquetes", listaPaquetes);
        return "paquetes"; // Nombre del archivo .html en /templates
    }

    @GetMapping("/paquetes/nuevo")
    public String mostrarFormularioNuevoPaquete(Model model) {
        model.addAttribute("paquete", new Paquete());
        return "formulario-paquete";
    }

    @GetMapping("/paquetes/editar/{id}")
    public String mostrarFormularioEditarPaquete(@PathVariable Long id, Model model) {
        Optional<Paquete> paqueteOptional = paqueteService.buscarPorId(id);
        if (paqueteOptional.isPresent()) {
            model.addAttribute("paquete", paqueteOptional.get());
            return "formulario-paquete";
        } else {
            return "redirect:/paquetes"; // En caso de que no exista el ID
        }
    }

    @PostMapping("/paquetes/guardar")
    public String guardarPaquete(@ModelAttribute("paquete") Paquete paquete) {
        paqueteService.guardar(paquete);
        return "redirect:/paquetes";
    }

    @PostMapping("/paquetes/actualizar")
    public String actualizarPAquete(@ModelAttribute("paquete") Paquete paquete) {
        paqueteService.guardar(paquete); // El metodo guardar() actualiza si ya tiene ID
        return "redirect:/paquetes";
    }
}
