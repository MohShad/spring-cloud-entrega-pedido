package br.com.entregapedido.pedidoservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class PedidoRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    private String descricao;

    @JsonProperty
    private Long clienteId;

    @JsonProperty
    private List<ProdutoQuantidadeRequestDTO> produto;

    public PedidoRequestDTO() {
    }

    public PedidoRequestDTO(String descricao, Long clienteId, List<ProdutoQuantidadeRequestDTO> produto) {
        this.descricao = descricao;
        this.clienteId = clienteId;
        this.produto = produto;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public List<ProdutoQuantidadeRequestDTO> getProduto() {
        return produto;
    }

    public void setProduto(List<ProdutoQuantidadeRequestDTO> produto) {
        this.produto = produto;
    }
}