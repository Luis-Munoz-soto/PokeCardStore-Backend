package com.gdl.pokecardstore.controller;

import com.gdl.pokecardstore.dto.*;
import com.gdl.pokecardstore.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Registrar usuario",
            description = "Crea un nuevo usuario dentro del sistema."
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos necesarios para registrar al usuario",
            required = true,
            content = @Content(schema = @Schema(implementation = RegisterRequest.class))
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuario registrado exitosamente",
            content = @Content(schema = @Schema(implementation = String.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos o faltantes"
    )
    public String register(@RequestBody RegisterRequest request) {
        usuarioService.register(request);
        return "Usuario registrado con éxito";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Iniciar sesión",
            description = "Valida credenciales y retorna un token JWT.",
            security = @SecurityRequirement(name = "BearerAuth")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Login exitoso",
            content = @Content(schema = @Schema(implementation = LoginResponse.class))
    )
    @ApiResponse(
            responseCode = "401",
            description = "Credenciales incorrectas"
    )
    public LoginResponse login(@RequestBody LoginRequest request) {
        return usuarioService.login(request);
    }
}
