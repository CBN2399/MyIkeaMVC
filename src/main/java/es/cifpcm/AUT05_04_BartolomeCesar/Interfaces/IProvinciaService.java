package es.cifpcm.AUT05_04_BartolomeCesar.Interfaces;

import es.cifpcm.AUT05_04_BartolomeCesar.models.Provincia;

import java.util.List;

public interface IProvinciaService {

    public List<Provincia> getProvinciaList();

    public Provincia getProvincia(Integer id);
}
