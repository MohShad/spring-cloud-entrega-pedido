package br.com.entregapedido.entregaservice.repository;

import br.com.entregapedido.entregaservice.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findById(Long id);

    List<Pedido> findByNumeroPedido(String numeroPedido);
}
