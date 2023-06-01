package es.cifpcm.AUT05_04_BartolomeCesar.services;

import es.cifpcm.AUT05_04_BartolomeCesar.Interfaces.IProductoService;
import es.cifpcm.AUT05_04_BartolomeCesar.models.Producto;
import es.cifpcm.AUT05_04_BartolomeCesar.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices implements IProductoService {

    @Autowired
    IProductoRepository pr;

    @Override
    public List<Producto> getProductoList() { return pr.findAll();}

    @Override
    public Producto getProducto(Integer id) {return pr.findById(id).orElse(null);}

    @Override
    public void addProducto(Producto producto) {
        pr.save(producto);
    }
}
