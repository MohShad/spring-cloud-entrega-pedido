package br.com.entregapedido.pedidoservice.dto;


import java.util.List;

public class MessageDTO {

    private Long id;
    private String numeroPedido;
    private Double valorTotal;
    private String enderecoEntrega;
    private Long cliente_id;
    private List<ProdutoResponseMessageDTO> produto;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String numeroPedido, Double valorTotal, String enderecoEntrega, Long cliente_id, List<ProdutoResponseMessageDTO> produto) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.valorTotal = valorTotal;
        this.enderecoEntrega = enderecoEntrega;
        this.cliente_id = cliente_id;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public List<ProdutoResponseMessageDTO> getProduto() {
        return produto;
    }

    public void setProduto(List<ProdutoResponseMessageDTO> produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id=" + id +
                ", numeroPedido='" + numeroPedido + '\'' +
                ", valorTotal=" + valorTotal +
                ", enderecoEntrega='" + enderecoEntrega + '\'' +
                ", cliente_id=" + cliente_id +
                ", produto=" + produto +
                '}';
    }
}