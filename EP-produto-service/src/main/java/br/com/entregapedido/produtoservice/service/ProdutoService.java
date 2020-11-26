package br.com.entregapedido.produtoservice.service;

import br.com.entregapedido.produtoservice.dto.ProdutoRequestDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoRequestEstoqueDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoResponseDTO;
import br.com.entregapedido.produtoservice.model.Produto;

public interface ProdutoService {

    Long salvarProduto(ProdutoRequestDTO produtoRequestDTO);

    ProdutoResponseDTO getProdutoByNcm(String ncm);

    Long increaseStockQuantity(ProdutoRequestEstoqueDTO produtoRequestEstoqueDTO);

    Produto getProdutoById(Long id);
}
