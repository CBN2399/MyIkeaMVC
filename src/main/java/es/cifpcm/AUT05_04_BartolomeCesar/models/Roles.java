package es.cifpcm.AUT05_04_BartolomeCesar.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roles_id;

    private String rolename;

    @ManyToMany(mappedBy = "rolesList")
    private List<User> userlist;

    public Roles(){}

    public Roles(String rolename) {
        this.rolename = rolename;
    }

    public Integer getRoles_id() {
        return roles_id;
    }

    public void setRoles_id(Integer roles_id) {
        this.roles_id = roles_id;
    }

    public String getRolename() {
        return rolename;
    }
}
