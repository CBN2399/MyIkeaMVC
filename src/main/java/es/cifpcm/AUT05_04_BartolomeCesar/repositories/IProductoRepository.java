package es.cifpcm.AUT05_04_BartolomeCesar.repositories;

import es.cifpcm.AUT05_04_BartolomeCesar.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto,Integer> {
}
