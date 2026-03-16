package com.regdevs.logistica.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

    @Autowired(required = false)
    private JavaMailSender mailSender;

    public void enviarNotificacionCambioEstado(String destinatario, String descripcionPaquete, String nuevoEstado) {
        String mensaje = String.format("Hola,\n\nTu paquete '%s' ha cambiado su estado a: %s.\n\nGracias por confiar en Logística App.", 
                descripcionPaquete, nuevoEstado);
        
        System.out.println("SIMULACIÓN DE ENVÍO DE EMAIL A: " + destinatario);
        System.out.println("ASUNTO: Cambio de Estado en tu Paquete");
        System.out.println("MENSAJE: " + mensaje);

        if (mailSender != null) {
            try {
                SimpleMailMessage email = new SimpleMailMessage();
                email.setTo(destinatario);
                email.setSubject("Cambio de Estado en tu Paquete");
                email.setText(mensaje);
                mailSender.send(email);
            } catch (Exception e) {
                System.err.println("Error enviando email: " + e.getMessage());
            }
        }
    }
}
