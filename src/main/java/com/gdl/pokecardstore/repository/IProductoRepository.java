package com.gdl.pokecardstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gdl.pokecardstore.entity.ProductoEntity;

@Repository
public interface IProductoRepository extends JpaRepository<ProductoEntity,Long>{

}
