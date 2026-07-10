package com.gdl.pokecardstore.controller;

import com.gdl.pokecardstore.entity.DireccionEntity;
import com.gdl.pokecardstore.service.IDireccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/direccion")
@CrossOrigin(origins = "*")
public class DireccionController {

    private final IDireccionService direccionService;

    public DireccionController(IDireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @PostMapping("/usuario/{idUsuario}")
    @Operation(
        summary = "Agregar dirección a un usuario",
        description = "Crea o actualiza la dirección asociada al usuario especificado."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Dirección agregada correctamente",
        content = @Content(schema = @Schema(implementation = DireccionEntity.class))
    )
    public DireccionEntity agregarDireccion(
            @PathVariable Long idUsuario,
            @RequestBody DireccionEntity direccion) {

        return direccionService.agregarDireccion(idUsuario, direccion);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(
        summary = "Agregar dirección a un usuario", 
        description = "Crea o actualiza la dirección asociada al usuario especificado."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Dirección agregada correctamente", 
        content = @Content(schema = @Schema(implementation = DireccionEntity.class))
    )
    public DireccionEntity obtenerDireccion(@PathVariable Long idUsuario) {
        return direccionService.obtenerDireccion(idUsuario);
    }
}
