package com.gdl.pokecardstore.controller;

import com.gdl.pokecardstore.dto.DetalleDTO;
import com.gdl.pokecardstore.service.IDetalleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/detalle-venta")
public class DetalleController {

    private final IDetalleService detalleService;

    public DetalleController(IDetalleService detalleService) {
        this.detalleService = detalleService;
    }

    @PostMapping
     @Operation(
            summary = "Crear detalle de venta",
            description = "Crea un nuevo detalle asociado a una venta."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Detalle creado correctamente",
            content = @Content(schema = @Schema(implementation = DetalleDTO.class))
    )
    public ResponseEntity<DetalleDTO> createDetalle(@RequestBody DetalleDTO detalleDTO) {
        DetalleDTO created = detalleService.createDetalle(detalleDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/venta/{idVenta}")
    @Operation(
        summary = "Obtener detalles por venta", 
        description = "Devuelve todos los detalles asociados a un ID de venta."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Lista de detalles obtenida correctamente", 
        content = @Content(schema = @Schema(implementation = DetalleDTO.class))
    )
    public ResponseEntity<List<DetalleDTO>> getDetallesByVenta(@PathVariable Long idVenta) {
        List<DetalleDTO> detalles = detalleService.getDetallesByVenta(idVenta);
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/usuario/{idUsuario}")
    @Operation(
        summary = "Obtener detalles por usuario", 
        description = "Devuelve todos los detalles de venta asociados a un usuario."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Lista de detalles obtenida correctamente", 
        content = @Content(schema = @Schema(implementation = DetalleDTO.class))
    )
    public ResponseEntity<List<DetalleDTO>> getDetallesByUsuario(@PathVariable Long idUsuario) {
        List<DetalleDTO> detalles = detalleService.getDetallesByUsuario(idUsuario);
        return ResponseEntity.ok(detalles);
    }
}
