package com.gdl.pokecardstore.service.impl;

import com.gdl.pokecardstore.dto.DetalleDTO;
import com.gdl.pokecardstore.entity.DetalleEntity;
import com.gdl.pokecardstore.entity.VentaEntity;
import com.gdl.pokecardstore.repository.IDetalleRepository;
import com.gdl.pokecardstore.repository.IProductoRepository;
import com.gdl.pokecardstore.service.IDetalleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleServiceImpl implements IDetalleService {

    private final IDetalleRepository detalleRepository;
    private final IProductoRepository productoRepository;

    public DetalleServiceImpl(IDetalleRepository detalleRepository,
            IProductoRepository productoRepository) {
        this.detalleRepository = detalleRepository;
        this.productoRepository = productoRepository;
    }
    @Override
    public DetalleDTO createDetalle(DetalleDTO detalleDTO) {
        VentaEntity venta = new VentaEntity();
        venta.setIdVenta(detalleDTO.getIdVenta()); // solo referencia

        DetalleEntity entity = new DetalleEntity();
        entity.setVenta(venta);
        entity.setIdCarta(detalleDTO.getIdProducto());
        entity.setCantidad(detalleDTO.getCantidad());
        entity.setPrecio(detalleDTO.getPrecio());

        DetalleEntity saved = detalleRepository.save(entity);
        detalleDTO.setIdDetalle(saved.getIdDetalle());
        return detalleDTO;
    }

    @Override
    public List<DetalleDTO> getDetallesByUsuario(Long idUsuario) {
        return detalleRepository.findByVenta_Usuario_IdUsuario(idUsuario)
                .stream()
                .map(entity -> {
                    // Obtener la carta asociada
                    var carta = productoRepository.findById(entity.getIdCarta()).orElse(null);

                    DetalleDTO dto = new DetalleDTO();
                    dto.setIdDetalle(entity.getIdDetalle());
                    dto.setIdVenta(entity.getVenta().getIdVenta());
                    dto.setIdProducto(entity.getIdCarta());
                    dto.setCantidad(entity.getCantidad());
                    dto.setPrecio(entity.getPrecio());

                    if (carta != null) {
                        dto.setImagenProducto(carta.getImg());
                        dto.setNombreProducto(carta.getNombre());
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<DetalleDTO> getDetallesByVenta(Long idVenta) {
        return detalleRepository.findByVenta_IdVenta(idVenta)
                .stream()
                .map(entity -> {
                    var carta = productoRepository.findById(entity.getIdCarta()).orElse(null);

                    DetalleDTO dto = new DetalleDTO();
                    dto.setIdDetalle(entity.getIdDetalle());
                    dto.setIdVenta(entity.getVenta().getIdVenta());
                    dto.setIdProducto(entity.getIdCarta());
                    dto.setCantidad(entity.getCantidad());
                    dto.setPrecio(entity.getPrecio());

                    if (carta != null) {
                        dto.setImagenProducto(carta.getImg());
                        dto.setNombreProducto(carta.getNombre());
                    }

                    return dto;
                })
                .collect(Collectors.toList());
    }

}
