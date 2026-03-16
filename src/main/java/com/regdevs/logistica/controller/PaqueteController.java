package com.regdevs.logistica.controller;

import com.regdevs.logistica.model.EstadoEnum;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayInputStream;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private RutaService rutaService;

    @Autowired
    private EstadoEnvioService estadoEnvioService;

    @Autowired
    private com.regdevs.logistica.service.ReporteService reporteService;

    @Autowired
    private com.regdevs.logistica.service.QrCodeService qrCodeService;

    @GetMapping("/paquetes")
    public String listarPaquetes(Model model) {
        model.addAttribute("paquetes", paqueteService.listarTodosDTO());
        return "paquetes";
    }

    @GetMapping("/paquetes/reporte")
    public ResponseEntity<InputStreamResource> descargarReporte() {
        java.util.List<com.regdevs.logistica.dto.PaqueteDTO> paquetes = paqueteService.listarTodosDTO();
        ByteArrayInputStream bis = reporteService.generarReportePaquetes(paquetes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=reporte-paquetes.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType((org.springframework.http.MediaType) MediaType.APPLICATION_PDF)
                .body(new InputStreamResource((java.io.InputStream) bis));
    }

    @GetMapping("/paquetes/qr/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> obtenerQrPaquete(@PathVariable("id") Long id) {
        byte[] qr = qrCodeService.generarQrPaquete(String.valueOf(id));
        if (qr == null) return ResponseEntity.internalServerError().build();

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qr);
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
        com.regdevs.logistica.dto.PaqueteDTO paqueteDTO = paqueteService.buscarPorIdDTO(id);
        if (paqueteDTO != null) {
            model.addAttribute("paquete", paqueteDTO);
            model.addAttribute("historial", estadoEnvioService.buscarPorPaqueteIdOrdenado(id));
            model.addAttribute("estadosDisponibles", EstadoEnum.values());
            return "detalle-paquete";
        } else {
            return "redirect:/paquetes";
        }
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
