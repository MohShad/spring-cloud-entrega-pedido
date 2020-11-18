package br.com.entregapedido.produtoservice.dto;

import java.io.Serializable;

public class ProdutoQuantidadeRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer quantidade;

    public ProdutoQuantidadeRequestDTO() {
    }

    public ProdutoQuantidadeRequestDTO(Long id, Integer quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}