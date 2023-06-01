package es.cifpcm.AUT05_04_BartolomeCesar.controllers;


import es.cifpcm.AUT05_04_BartolomeCesar.models.Producto;
import es.cifpcm.AUT05_04_BartolomeCesar.models.User;
import es.cifpcm.AUT05_04_BartolomeCesar.services.ProductServices;
import es.cifpcm.AUT05_04_BartolomeCesar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class CarritoController {

    @Autowired
    UserService userSer;

    @Autowired
    ProductServices productSer;


    @GetMapping("/carrito")
    public String carrito(Model model, Principal principal){

        String userEmail = principal.getName();
        User currentUser = userSer.getUser(userEmail);
        List<Producto> productoList= currentUser.getCarrito();
        model.addAttribute("lista",productoList);
        float price = 0;
        for(Producto p : productoList){
            price += p.getProduct_price();
        }
        model.addAttribute("total",price);
        return "User/carrito";
    }

    @GetMapping("/carrito/{id}")
    public String carritoAdd(Model model, Principal principal, @PathVariable("id") Integer id){

        String userEmail = principal.getName();
        User currentUser = userSer.getUser(userEmail);
        Producto product = productSer.getProducto(id);
        List<Producto> productoList= currentUser.getCarrito();
        productoList.add(product);
        currentUser.setCarrito(productoList);
        userSer.saveUser(currentUser);
        model.addAttribute("lista",productoList);
        float price = 0;
        for(Producto p : productoList){
            price += p.getProduct_price();
        }
        model.addAttribute("total",price);
        return "User/carrito";
    }

    @GetMapping("/carrito/borrar/{id}")
    public String carritoDel(Model model, Principal principal, @PathVariable("id") Integer id){

        String userEmail = principal.getName();
        User currentUser = userSer.getUser(userEmail);
        Producto product = productSer.getProducto(id);
        List<Producto> productoList= currentUser.getCarrito();

        boolean correct = productoList.remove(product);
        if(correct){
            currentUser.setCarrito(productoList);
            userSer.saveUser(currentUser);
        }

        model.addAttribute("lista",productoList);
        float price = 0;
        for(Producto p : productoList){
            price += p.getProduct_price();
        }

        model.addAttribute("total",price);
        return "User/carrito";
    }
}
