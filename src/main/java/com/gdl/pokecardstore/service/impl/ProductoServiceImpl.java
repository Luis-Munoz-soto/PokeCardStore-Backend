package com.gdl.pokecardstore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gdl.pokecardstore.dto.ProductoDTO;
import com.gdl.pokecardstore.entity.ProductoEntity;
import com.gdl.pokecardstore.repository.IProductoRepository;
import com.gdl.pokecardstore.service.IProductoService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements IProductoService{

    private final IProductoRepository productoRepository;

    public ProductoServiceImpl(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
       
    }

    @Override
    public List<ProductoDTO> getAllProductos() {
      return productoRepository.findAll().stream().map(producto ->{
        ProductoDTO dto= new ProductoDTO();
        dto.setId(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setImg(producto.getImg());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        return dto;
      }).collect(Collectors.toList());
    }

    @Transactional
    public void descontarStock(Long idProducto, int cantidad) {
      System.out.println("🟢 Descontando producto " + idProducto + " cantidad " + cantidad);

      ProductoEntity producto = productoRepository.findById(idProducto)
          .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + idProducto));

      if (producto.getStock() < cantidad) {
        throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
      }

      System.out.println("🟢 Antes: stock = " + producto.getStock());
      producto.setStock(producto.getStock() - cantidad);
      System.out.println("🔵 Después: stock = " + producto.getStock());

      // 👇 FALTA ESTO
      productoRepository.save(producto);
    }

}
