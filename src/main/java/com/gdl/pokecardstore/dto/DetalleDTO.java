package com.gdl.pokecardstore.dto;

public class DetalleDTO {
    private Long idDetalle;
    private Long idVenta;
    private Long idProducto;
    private Integer cantidad;
    private Double precio;
    private String img;
    private String nombre;

    public DetalleDTO() {
    }

    public DetalleDTO(Long idDetalle, Long idVenta, Long idProducto, Integer cantidad, Double precio, String img, String nombre) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.img = img;
        this.cantidad = cantidad;
        this.precio = precio;
        this.nombre = nombre;

    }

    public String getImagenProducto() {
        return img;
    }

    public void setImagenProducto(String img) {
        this.img = img;
    }

    public String getNombreProducto() {
        return nombre;
    }

    public void setNombreProducto(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "DetalleVentaDTO{" +
                "idDetalle=" + idDetalle +
                ", idVenta=" + idVenta +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }


}
