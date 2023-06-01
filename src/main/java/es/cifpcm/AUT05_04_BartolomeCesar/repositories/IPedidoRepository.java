package es.cifpcm.AUT05_04_BartolomeCesar.repositories;

import es.cifpcm.AUT05_04_BartolomeCesar.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido,Integer> {
}
