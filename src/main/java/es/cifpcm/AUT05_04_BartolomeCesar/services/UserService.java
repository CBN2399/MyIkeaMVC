package es.cifpcm.AUT05_04_BartolomeCesar.services;

import es.cifpcm.AUT05_04_BartolomeCesar.models.Pedido;
import es.cifpcm.AUT05_04_BartolomeCesar.models.Producto;
import es.cifpcm.AUT05_04_BartolomeCesar.models.Roles;
import es.cifpcm.AUT05_04_BartolomeCesar.models.User;
import es.cifpcm.AUT05_04_BartolomeCesar.repositories.IRolesRepository;
import es.cifpcm.AUT05_04_BartolomeCesar.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    IUserRepository userRe;

    @Autowired
    IRolesRepository roleRe;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuarioSelected = userRe.findByEmail(username);

        if(usuarioSelected == null){
            throw new UsernameNotFoundException("No se ha encontrado un usuario con ese email...");
        }
        UserDetails user = new org.springframework.security.core.userdetails.User(usuarioSelected.getEmail(),
                usuarioSelected.getPassword(),mapRolesToAuthorities(usuarioSelected.getRolesList()));
        return user;
    }

    public User getUser(String email){
        return userRe.findByEmail(email);
    }
    public void saveUser(User user){
        userRe.save(user);
    }
    public List<User> getUserList(){
        return userRe.findAll();
    }
    public void deleteUser(Integer id){
        userRe.deleteById(id);
    }
    public void createUser(User user){

        PasswordEncoder encoder = passwordEncoder();
        if(userRe.findByEmail("Admin2@Ikea.com") == null)
        {
            List<Pedido> pedidoList = new ArrayList<>();
            List<Producto> productoList = new ArrayList<>();
            User Admin = new User("Admin", "Admin@Ikea.com", encoder.encode("admin"),
                    List.of(roleRe.findByRolename("ROLE_ADMIN")),productoList,pedidoList);
            userRe.save(Admin);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRolesList(List.of(roleRe.findByRolename("ROLE_USER")));
        userRe.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles)
    {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                .collect(Collectors.toList());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
