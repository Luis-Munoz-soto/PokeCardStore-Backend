package com.gdl.pokecardstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gdl.pokecardstore.entity.VentaEntity;

@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Long> {
    List<VentaEntity> findByUsuario_IdUsuario(Long idUsuario);
}
