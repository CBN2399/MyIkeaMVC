package es.cifpcm.AUT05_04_BartolomeCesar.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name="productoffer")
public class Producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    @NotEmpty(message = "El nombre del producto es obligatorio")
    private  String product_name;


    @Min(value = 1, message ="No puede haber un precio negativo")
    private float product_price;

    private String product_picture;

    @ManyToOne
    @JoinColumn(name = "id_municipio")
    @NotNull(message = "Seleccciona un municipio.")
    private Municipio municipio;
    @Min(value = 1, message = "No puede haber stock negativo")
    private Integer product_stock;

    @ManyToMany(mappedBy = "productoList")
    private List<Pedido> pedidoList;

    @ManyToMany(mappedBy = "carrito")
    private List<User> userList;

    public Producto(){

    }

    public Producto(String product_name, float product_price, String product_picture, Municipio mun, Integer product_stock) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_picture = product_picture;
        this.municipio = mun;
        this.product_stock = product_stock;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public String getProduct_picture() {
        return product_picture;
    }

    public void setProduct_picture(String product_picture) {
        this.product_picture = product_picture;
    }

    public Municipio getMunicipio() {return municipio;}

    public void setMunicipio(Municipio municipio) {this.municipio = municipio;}

    public Integer getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(Integer product_stock) {
        this.product_stock = product_stock;
    }
}
