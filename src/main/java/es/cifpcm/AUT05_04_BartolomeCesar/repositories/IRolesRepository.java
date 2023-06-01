package es.cifpcm.AUT05_04_BartolomeCesar.repositories;

import es.cifpcm.AUT05_04_BartolomeCesar.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolesRepository extends JpaRepository<Roles,Integer> {
    Roles findByRolename(String name);
}
