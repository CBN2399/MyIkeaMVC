package es.cifpcm.AUT05_04_BartolomeCesar.security;
import es.cifpcm.AUT05_04_BartolomeCesar.models.Roles;
import es.cifpcm.AUT05_04_BartolomeCesar.repositories.IRolesRepository;
import es.cifpcm.AUT05_04_BartolomeCesar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthConfiguration {


    @Autowired
    UserService userServ;
    @Autowired
    IRolesRepository rolRe;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers("/","/register").permitAll()
                .anyRequest().authenticated()
        );

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        http.userDetailsService(userServ);

        http.exceptionHandling().accessDeniedPage("/denied");

        return http.build();
    }

    @Bean
    public void seeder(){

        if(rolRe.findByRolename("ROLE_USER") == null){
            Roles user = new Roles("ROLE_USER");
            rolRe.save(user);
        }

        if(rolRe.findByRolename("ROLE_ADMIN") == null){
            Roles admin = new Roles("ROLE_ADMIN");
            rolRe.save(admin);
        }
    }
}
