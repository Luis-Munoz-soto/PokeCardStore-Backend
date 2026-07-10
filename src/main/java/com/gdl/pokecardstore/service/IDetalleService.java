package com.gdl.pokecardstore.service;

import java.util.List;

import com.gdl.pokecardstore.dto.DetalleDTO;

public interface IDetalleService {

    DetalleDTO createDetalle(DetalleDTO detalleDTO);

    List<DetalleDTO> getDetallesByVenta(Long idVenta);

    List<DetalleDTO> getDetallesByUsuario(Long idUsuario);
}
