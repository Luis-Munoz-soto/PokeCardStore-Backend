package com.gdl.pokecardstore.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdl.pokecardstore.dto.DetalleDTO;
import com.gdl.pokecardstore.dto.VentaDTO;
import com.gdl.pokecardstore.entity.UsuarioEntity;
import com.gdl.pokecardstore.entity.VentaEntity;
import com.gdl.pokecardstore.repository.UsuarioRepository;
import com.gdl.pokecardstore.repository.VentaRepository;
import com.gdl.pokecardstore.service.IDetalleService;
import com.gdl.pokecardstore.service.IProductoService;
import com.gdl.pokecardstore.service.VentaService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServiceImpl implements VentaService {


    private  VentaRepository ventaRepository;
    private  UsuarioRepository usuarioRepository;
private IProductoService productoService;
    private  IDetalleService detalleService;

 @Autowired
public VentaServiceImpl(VentaRepository ventaRepository,
                        UsuarioRepository usuarioRepository,
                        IProductoService productoService,
                        IDetalleService detalleService) {
    this.ventaRepository = ventaRepository;
    this.usuarioRepository = usuarioRepository;
    this.productoService = productoService;
    this.detalleService = detalleService;
}


    // LISTAR TODAS
    @Override
    public List<VentaDTO> obtenerVentas() {
        return ventaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ELIMINAR
    @Override
    public void eliminarVenta(Long idVenta) {
        ventaRepository.deleteById(idVenta);
    }

    // OBTENER POR ID
    @Override
    public VentaDTO obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    // CREAR VENTA
    @Transactional
    @Override
    public VentaDTO crearVenta(VentaDTO dto) {

        UsuarioEntity usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        VentaEntity venta = new VentaEntity();
        venta.setUsuario(usuario);
        venta.setTotal(dto.getTotal());
        venta.setFechaCreacion(LocalDateTime.now());

        VentaEntity nuevaVenta = ventaRepository.save(venta);
        if (dto.getDetalles() != null) {
            for (DetalleDTO detalle : dto.getDetalles()) {
                productoService.descontarStock(detalle.getIdProducto(), detalle.getCantidad());

                // Asignamos la venta recién creada al detalle
                detalle.setIdVenta(nuevaVenta.getIdVenta());
                detalleService.createDetalle(detalle);
            }
        }
        return convertToDTO(nuevaVenta);
    }
    
    @Override
    public List<VentaDTO> getVentasByUsuario(Long idUsuario) {
        return ventaRepository.findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // CONVERTIR ENTITY A DTO
    private VentaDTO convertToDTO(VentaEntity v) {

        return new VentaDTO(
                v.getIdVenta(),
                v.getUsuario().getIdUsuario(),
                v.getTotal(),
                v.getFechaCreacion(), null
        );
    }
}
