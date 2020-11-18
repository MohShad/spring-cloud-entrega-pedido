package br.com.entregapedido.pedidoservice.dto;


public class ProdutoResponsePedidoDTO {


    private Long id;
    private String nome;
    private Double preco;
    private String ncm;
    private Integer quantidade;

    public ProdutoResponsePedidoDTO() {
    }

    public ProdutoResponsePedidoDTO(Long id, String nome, Double preco, String ncm, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.ncm = ncm;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
