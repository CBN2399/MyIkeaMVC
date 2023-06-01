package es.cifpcm.AUT05_04_BartolomeCesar.repositories;

import es.cifpcm.AUT05_04_BartolomeCesar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
