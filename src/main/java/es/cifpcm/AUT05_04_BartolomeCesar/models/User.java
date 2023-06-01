package es.cifpcm.AUT05_04_BartolomeCesar.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @NotBlank(message = "Minimo ha de tener 4 caracteres")
    private String username;
    @NotEmpty(message = "El campo es obligatorio ")
    @Email
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "roles_id")})
    private List<Roles> rolesList;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_products",
            joinColumns = { @JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")})
    private List<Producto> carrito;


    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL)
    private List<Pedido> pedidoList;
    public User(){};

    public User(String username, String email, String password,List<Roles> roles,List<Producto> prList,List<Pedido> pedList) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.rolesList = roles;
        this.carrito = prList;
        this.pedidoList = pedList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    public List<Producto> getCarrito() {return carrito;}

    public void setCarrito(List<Producto> carrito) {this.carrito = carrito;}

    public List<Pedido> getPedidoList() {return pedidoList;}

    public void setPedidoList(List<Pedido> pedidoList) {this.pedidoList = pedidoList;}
}
