package es.cifpcm.AUT05_04_BartolomeCesar.controllers;

import es.cifpcm.AUT05_04_BartolomeCesar.models.Pedido;
import es.cifpcm.AUT05_04_BartolomeCesar.models.Producto;
import es.cifpcm.AUT05_04_BartolomeCesar.models.User;
import es.cifpcm.AUT05_04_BartolomeCesar.services.PedidoService;
import es.cifpcm.AUT05_04_BartolomeCesar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    UserService userSer;

    @Autowired
    PedidoService pedSer;

    @GetMapping("pedidos")
    public String getPedidos(Model model, Principal principal){

        String userEmail = principal.getName();
        User currentUser = userSer.getUser(userEmail);
        List<Pedido> pedLi = currentUser.getPedidoList();
        model.addAttribute("lista",pedLi);
        return "Pedido/list";
    }
    @GetMapping("pedido/add")
    public String addPedido(Model model, Principal principal){

        String userEmail = principal.getName();
        User currentUser = userSer.getUser(userEmail);
        List<Producto> proLi = currentUser.getCarrito();
        List<Producto> productoList = new ArrayList<>();
        float price = 0;
        for(Producto p : proLi){
            price += p.getProduct_price();
            productoList.add(p);
        }
        Pedido pedido = new Pedido(price,productoList,currentUser);
        List<Pedido> pedidoList = currentUser.getPedidoList();
        pedidoList.add(pedido);
        proLi.clear();
        userSer.saveUser(currentUser);
        return "redirect:/pedidos";
    }

    @GetMapping("/pedido/{id}")
    public String details(Model model, @PathVariable("id") Integer id){

        Pedido pedido = pedSer.getPedido(id);
        model.addAttribute("pedido",pedido);
        return "Pedido/details";
    }
}
