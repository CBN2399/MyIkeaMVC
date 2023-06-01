package es.cifpcm.AUT05_04_BartolomeCesar.Interfaces;

import es.cifpcm.AUT05_04_BartolomeCesar.models.Pedido;

import java.util.List;

public interface IPedidoService {

    public List<Pedido> getPedidoList();

    public Pedido getPedido(Integer id);

    public void addPedido(Pedido pedido);
}
