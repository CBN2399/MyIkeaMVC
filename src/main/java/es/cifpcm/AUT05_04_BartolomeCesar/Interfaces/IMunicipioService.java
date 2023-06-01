package es.cifpcm.AUT05_04_BartolomeCesar.Interfaces;

import es.cifpcm.AUT05_04_BartolomeCesar.models.Municipio;

import java.util.List;

public interface IMunicipioService {

    public List<Municipio> getMunicipioList();

    public Municipio getMunicipio(Integer id);
}
