package br.com.entregapedido.produtoservice.dto;


import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "produto")
public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private Double preco;
    private Integer quantidadeEstoque;
    private String ncm;

    public ProdutoResponseDTO() {
    }

    public ProdutoResponseDTO(Long id, String nome, Double preco, Integer quantidadeEstoque, String ncm) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.ncm = ncm;
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

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }
}