package com.gdl.pokecardstore.service.impl;

import com.gdl.pokecardstore.entity.DireccionEntity;
import com.gdl.pokecardstore.entity.UsuarioEntity;
import com.gdl.pokecardstore.repository.IDireccionRepository;
import com.gdl.pokecardstore.repository.UsuarioRepository;
import com.gdl.pokecardstore.service.IDireccionService;

import org.springframework.stereotype.Service;

@Service
public class DireccionServiceImpl implements IDireccionService {

    private final IDireccionRepository direccionRepository;
    private final UsuarioRepository usuarioRepository;

    public DireccionServiceImpl(IDireccionRepository direccionRepository,
            UsuarioRepository usuarioRepository) {
        this.direccionRepository = direccionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public DireccionEntity agregarDireccion(Long idUsuario, DireccionEntity direccion) {

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        DireccionEntity nuevaDireccion = direccionRepository.save(direccion);

        usuario.setDireccion(nuevaDireccion);
        usuarioRepository.save(usuario);

        return nuevaDireccion;
    }

    @Override
    public DireccionEntity obtenerDireccion(Long idUsuario) {

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return usuario.getDireccion();
    }
}
