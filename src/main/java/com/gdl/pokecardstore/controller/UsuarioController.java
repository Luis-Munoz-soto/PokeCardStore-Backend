package com.gdl.pokecardstore.controller;

import com.gdl.pokecardstore.entity.UsuarioEntity;
import com.gdl.pokecardstore.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
       @Operation(
        summary = "Obtener un usuario por ID",
        description = "Retorna los datos completos del usuario asociado al ID proporcionado."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Usuario encontrado",
        content = @Content(schema = @Schema(implementation = UsuarioEntity.class))
    )
    public UsuarioEntity getById(@PathVariable Long id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar usuario", 
        description = "Actualiza los datos de un usuario existente según el ID indicado."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Usuario actualizado correctamente", 
        content = @Content(schema = @Schema(implementation = UsuarioEntity.class))
    )
    public UsuarioEntity update(
            @PathVariable Long id,
            @RequestBody UsuarioEntity request) {
        return usuarioService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar usuario", 
        description = "Elimina un usuario por su ID."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Usuario eliminado correctamente"
    )
    public String delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return "Usuario eliminado correctamente";
    }
}
