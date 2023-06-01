package es.cifpcm.AUT05_04_BartolomeCesar.controllers;

import es.cifpcm.AUT05_04_BartolomeCesar.models.User;
import es.cifpcm.AUT05_04_BartolomeCesar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userSer;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/usuarios")
    public String users(Model model, Principal principal){

        List<User> userList = userSer.getUserList();
        String userEmail = principal.getName();
        User user = userSer.getUser(userEmail);
        userList.remove(user);
        model.addAttribute("lista",userList);
        return "User/list";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/usuario/borrar/{id}")
    public String userBorrar(Model model, Principal principal, @PathVariable("id") Integer id){

        userSer.deleteUser(id);
        String userEmail = principal.getName();
        User currentUser = userSer.getUser(userEmail);
        List<User> userList = userSer.getUserList();
        userList.remove(currentUser);
        model.addAttribute("lista",userList);
        return "User/list";
    }
}
