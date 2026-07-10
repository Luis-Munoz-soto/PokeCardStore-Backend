package com.gdl.pokecardstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gdl.pokecardstore.dto.ProductoDTO;
import com.gdl.pokecardstore.service.IProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("/Producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

       
    @GetMapping("/")
        @Operation(
        summary = "Obtener todos los productos",
        description = "Retorna una lista con todos los productos disponibles en la tienda."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de productos obtenida correctamente",
        content = @Content(schema = @Schema(implementation = ProductoDTO.class))
    )
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        return ResponseEntity.ok(productoService.getAllProductos());
    }
}
