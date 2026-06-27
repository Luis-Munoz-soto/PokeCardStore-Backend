package com.gdl.pokecardstore.service;

import com.gdl.pokecardstore.dto.LoginRequest;
import com.gdl.pokecardstore.dto.LoginResponse;
import com.gdl.pokecardstore.dto.RegisterRequest;
import com.gdl.pokecardstore.entity.UsuarioEntity;
import com.gdl.pokecardstore.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public void register(RegisterRequest request) {

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(request.getEmail());
        usuario.setContraseña(passwordEncoder.encode(request.getContraseña()));
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setRut(request.getRut());

        usuario.setDireccion(null);

        usuarioRepository.save(usuario);
    }

    public LoginResponse login(LoginRequest request) {

        UsuarioEntity usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getContraseña(), usuario.getContraseña())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generateToken(usuario.getEmail());

        return new LoginResponse(
                token,
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getRut());
    }

    public UsuarioEntity findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UsuarioEntity update(Long id, UsuarioEntity request) {
        UsuarioEntity usuario = findById(id);

        if (request.getNombre() != null && !request.getNombre().isBlank()) {
            usuario.setNombre(request.getNombre());
        }

        if (request.getApellido() != null && !request.getApellido().isBlank()) {
            usuario.setApellido(request.getApellido());
        }

        if (request.getContraseña() != null && !request.getContraseña().isEmpty()) {
            usuario.setContraseña(passwordEncoder.encode(request.getContraseña()));
        }

        // No actualizar email ni RUT, se mantienen como están
        return usuarioRepository.save(usuario);
    }
    public void delete(Long id) {
        UsuarioEntity usuario = findById(id);
        usuarioRepository.delete(usuario);
    }
}
