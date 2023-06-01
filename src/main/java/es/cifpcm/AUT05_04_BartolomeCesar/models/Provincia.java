package es.cifpcm.AUT05_04_BartolomeCesar.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name ="provincias")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_provincia;

    private String nombre;

    @JsonBackReference
    @OneToMany(mappedBy = "provincia",
            cascade = CascadeType.ALL)
    private List<Municipio> municipioList;

    //Getters y setters

    public Integer getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(Integer id_provincia) {
        this.id_provincia = id_provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
