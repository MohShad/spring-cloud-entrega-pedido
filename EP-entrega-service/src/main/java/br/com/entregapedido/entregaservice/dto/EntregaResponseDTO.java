package br.com.entregapedido.entregaservice.dto;

import java.util.Date;

public class EntregaResponseDTO {

    private Long id;
    private Date createAt;
    private String enderecoEntrega;
    private String numeroEntrega;
    private String numeroPedido;
    private Integer quantidadeProduto;
    private Double valorTotal;
    private Integer clienteId;
    private Integer pedidoId;
    private Integer produtoId;

    public EntregaResponseDTO() {
    }

    public EntregaResponseDTO(Long id, Date createAt, String enderecoEntrega, String numeroEntrega, String numeroPedido, Integer quantidadeProduto, Double valorTotal, Integer clienteId, Integer pedidoId, Integer produtoId) {
        this.id = id;
        this.createAt = createAt;
        this.enderecoEntrega = enderecoEntrega;
        this.numeroEntrega = numeroEntrega;
        this.numeroPedido = numeroPedido;
        this.quantidadeProduto = quantidadeProduto;
        this.valorTotal = valorTotal;
        this.clienteId = clienteId;
        this.pedidoId = pedidoId;
        this.produtoId = produtoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(String numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Integer quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }
}