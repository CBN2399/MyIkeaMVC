package es.cifpcm.AUT05_04_BartolomeCesar.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="municipios")
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_municipio;

    @ManyToOne
    @JoinColumn(name = "id_provincia")
    private Provincia provincia;

    private Integer cod_municipio;

    private Integer DC;

    private String nombre;

    @JsonBackReference
    @OneToMany(mappedBy = "municipio",
            cascade = CascadeType.ALL)
    private List<Producto> productoList;

    //Getters y Setters

    public Integer getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Integer id_municipio) {
        this.id_municipio = id_municipio;
    }

    public Provincia getProvincia() {return provincia;}

    public void setProvincia(Provincia provincia) {this.provincia = provincia;}

    public Integer getCod_municipio() {
        return cod_municipio;
    }

    public void setCod_municipio(Integer cod_municipio) {
        this.cod_municipio = cod_municipio;
    }

    public Integer getDC() {
        return DC;
    }

    public void setDC(Integer DC) {
        this.DC = DC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Producto> getProductoList() {return productoList;}

    public void setProductoList(List<Producto> productoList) {this.productoList = productoList;}
}
