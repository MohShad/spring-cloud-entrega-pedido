package br.com.entregapedido.produtoservice.service;

import br.com.entregapedido.produtoservice.dto.ProdutoRequestDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoRequestEstoqueDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoResponseDTO;

public interface ProdutoService {

    Long salvarProduto(ProdutoRequestDTO produtoRequestDTO);

    ProdutoResponseDTO getProdutoByNcm(String ncm);

    Long increaseStockQuantity(ProdutoRequestEstoqueDTO produtoRequestEstoqueDTO);
}
