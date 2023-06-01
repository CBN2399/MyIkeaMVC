package es.cifpcm.AUT05_04_BartolomeCesar.Interfaces;

import es.cifpcm.AUT05_04_BartolomeCesar.models.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> getProductoList();

    public Producto getProducto(Integer id);

    public void addProducto(Producto producto);
}
