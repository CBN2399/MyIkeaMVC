package es.cifpcm.AUT05_04_BartolomeCesar.controllers;

import es.cifpcm.AUT05_04_BartolomeCesar.Interfaces.IMunicipioService;
import es.cifpcm.AUT05_04_BartolomeCesar.Interfaces.IProductoService;
import es.cifpcm.AUT05_04_BartolomeCesar.models.Municipio;
import es.cifpcm.AUT05_04_BartolomeCesar.models.Producto;
import es.cifpcm.AUT05_04_BartolomeCesar.models.Provincia;
import es.cifpcm.AUT05_04_BartolomeCesar.models.User;
import es.cifpcm.AUT05_04_BartolomeCesar.services.ProvinciaService;
import es.cifpcm.AUT05_04_BartolomeCesar.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
public class ProductoController {

    public static String PATH_IMAGE = System.getProperty("user.dir") + "/src/main/resources/static/Images/";
    @Autowired
    IProductoService productSer;

    @Autowired
    ProvinciaService provinSer;

    @Autowired
    UserService userSer;

    @Autowired
    IMunicipioService munipSer;


    @GetMapping("/producto")
    public String listaProductos(Model model){
        List<Producto> productoList = productSer.getProductoList();
        model.addAttribute("lista",productoList);
        return "/Producto/list";
    }

    @GetMapping("/producto/{id}")
    public String getProducto(Model model, @PathVariable("id") Integer id){

        Producto producto = productSer.getProducto(id);
        model.addAttribute("producto",producto);
        return "Producto/details";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/producto/create")
    public String create(Model model){

        List<Municipio> municipioList = munipSer.getMunicipioList();
        model.addAttribute("munip",municipioList);

        List<Provincia> provinciaList = provinSer.getProvinciaList();
        model.addAttribute("provincia",provinciaList);

        model.addAttribute("producto",new Producto());
        return "/Producto/create";
    }

    @PostMapping("producto/create")
    public String create(@Valid @ModelAttribute("producto") Producto producto, BindingResult br, MultipartFile image,Model model) throws IOException {

        if(br.hasErrors()){

            List<Municipio> municipioList = munipSer.getMunicipioList();
            model.addAttribute("munip",municipioList);
            List<Provincia> provinciaList = provinSer.getProvinciaList();
            model.addAttribute("provincia",provinciaList);

            return "/Producto/create";
        }

        String name = image.getOriginalFilename();
        producto.setProduct_picture(name);
        StringBuilder img = new StringBuilder();
        Path ruta = Paths.get(PATH_IMAGE, name);
        img.append(name);
        Files.write(ruta,image.getBytes());


        productSer.addProducto(producto);

        return "redirect:/producto";
    }


}
