package br.com.entregapedido.entregaservice.repository;

import br.com.entregapedido.entregaservice.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findById(Long id);

    Produto findByNcm(String id);

    Boolean existsByNcm(String ncm);
}
