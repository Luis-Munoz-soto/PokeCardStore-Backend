package com.gdl.pokecardstore.dto;

import java.math.BigDecimal;

public class ProductoDTO {

    private Long id;       
    private String nombre;
    private String img;
    private BigDecimal precio;
    private Integer stock;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombre, String img, BigDecimal precio, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.img = img;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

