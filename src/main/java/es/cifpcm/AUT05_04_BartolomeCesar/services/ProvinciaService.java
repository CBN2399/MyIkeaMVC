package es.cifpcm.AUT05_04_BartolomeCesar.services;

import es.cifpcm.AUT05_04_BartolomeCesar.Interfaces.IProductoService;
import es.cifpcm.AUT05_04_BartolomeCesar.Interfaces.IProvinciaService;
import es.cifpcm.AUT05_04_BartolomeCesar.models.Provincia;
import es.cifpcm.AUT05_04_BartolomeCesar.repositories.IProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinciaService implements IProvinciaService {

    @Autowired
    IProvinciaRepository proRe;


    @Override
    public List<Provincia> getProvinciaList() {
        return proRe.findAll();
    }

    @Override
    public Provincia getProvincia(Integer id) {
        return proRe.findById(id).orElse(null);
    }
}
