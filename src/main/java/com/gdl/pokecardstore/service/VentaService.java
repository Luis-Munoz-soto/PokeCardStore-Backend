package com.gdl.pokecardstore.service;

import java.util.List;

import com.gdl.pokecardstore.dto.VentaDTO;

public interface VentaService {

    VentaDTO crearVenta(VentaDTO ventaDTO);
    VentaDTO obtenerVentaPorId(Long idVenta);
    List<VentaDTO> obtenerVentas();
    void eliminarVenta(Long idVenta);
    List<VentaDTO> getVentasByUsuario(Long idUsuario);
}

