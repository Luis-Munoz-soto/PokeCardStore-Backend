package com.gdl.pokecardstore.service;

import com.gdl.pokecardstore.entity.DireccionEntity;

public interface IDireccionService {

    DireccionEntity agregarDireccion(Long idUsuario, DireccionEntity direccion);

    DireccionEntity obtenerDireccion(Long idUsuario);
}
