package com.regdevs.logistica.config;

import com.regdevs.logistica.model.Usuario;
import com.regdevs.logistica.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            usuarioRepository.save(new Usuario("admin", passwordEncoder.encode("admin123"), "ADMIN"));
            usuarioRepository.save(new Usuario("operario", passwordEncoder.encode("operario123"), "OPERARIO"));
            System.out.println("Usuarios por defecto creados: admin/admin123 y operario/operario123");
        }
    }
}
