package es.cifpcm.AUT05_04_BartolomeCesar.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pedido_id;

    private Date fecha;

    private float total;

    @ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "pedido_producto",
            joinColumns = { @JoinColumn(name = "pedido_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")})
    private List<Producto> productoList;

    @ManyToOne
    private User user;

    public Pedido(){}
    public Pedido(float total, List<Producto> productoList, User user) {
        this.fecha = new Date();
        this.total = total;
        this.productoList = productoList;
        this.user = user;
    }

    public Integer getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(Integer pedido_id) {
        this.pedido_id = pedido_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
