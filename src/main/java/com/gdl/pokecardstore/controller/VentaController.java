package com.gdl.pokecardstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdl.pokecardstore.dto.VentaDTO;
import com.gdl.pokecardstore.service.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/venta")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/{id}")
        @Operation(
        summary = "Obtener venta por ID",
        description = "Devuelve la información completa de una venta según su ID."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Venta encontrada",
        content = @Content(schema = @Schema(implementation = VentaDTO.class))
    )
    public VentaDTO obtenerVentaPorId(@PathVariable Long id) {
        return ventaService.obtenerVentaPorId(id);
    }

    @PostMapping
    @Operation(
        summary = "Crear venta", 
        description = "Registra una nueva venta en el sistema."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Venta creada exitosamente", 
        content = @Content(schema = @Schema(implementation = VentaDTO.class))
    )
    public VentaDTO crearVenta(@RequestBody VentaDTO ventaDTO) {
        return ventaService.crearVenta(ventaDTO);
    }

    @GetMapping
    @Operation(
        summary = "Obtener todas las ventas", 
        description = "Devuelve una lista de todas las ventas registradas."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Listado de ventas obtenido correctamente", 
        content = @Content(schema = @Schema(implementation = VentaDTO.class))
    )
    public java.util.List<VentaDTO> obtenerVentas() {
        return ventaService.obtenerVentas();
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar una venta", 
        description = "Elimina una venta del sistema por ID."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Venta eliminada correctamente"
    )
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return "Venta eliminada correctamente";
    }
        
    @GetMapping("/usuario/{idUsuario}")
    @Operation(
        summary = "Obtener ventas por usuario", 
        description = "Lista todas las ventas asociadas a un usuario por su ID."
    )
    @ApiResponse(
        responseCode = "200", 
        description = "Ventas obtenidas correctamente", 
        content = @Content(schema = @Schema(implementation = VentaDTO.class))
    )
    public ResponseEntity<List<VentaDTO>> getVentasByUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(ventaService.getVentasByUsuario(idUsuario));
    }


}

