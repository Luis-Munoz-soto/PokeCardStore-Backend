package com.gdl.pokecardstore.repository;

import com.gdl.pokecardstore.entity.DetalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDetalleRepository extends JpaRepository<DetalleEntity, Long> {
    
    List<DetalleEntity> findByVenta_Usuario_IdUsuario(Long idUsuario);

    List<DetalleEntity> findByVenta_IdVenta(Long idVenta);

}
