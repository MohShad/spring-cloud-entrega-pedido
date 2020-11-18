package br.com.entregapedido.entregaservice.repository;

import br.com.entregapedido.entregaservice.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    Optional<Entrega> findById(Long id);

    List<Entrega> findAll();
}
