package com.gdl.pokecardstore.service;


import java.util.List;

import com.gdl.pokecardstore.dto.ProductoDTO;


public interface IProductoService  {


  List<ProductoDTO>getAllProductos();


  void descontarStock(Long idProducto, int cantidad);
       
}
