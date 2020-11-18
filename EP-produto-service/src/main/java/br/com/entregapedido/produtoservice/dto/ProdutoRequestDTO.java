package br.com.entregapedido.produtoservice.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProdutoRequestDTO {

    @Size(max = 400)
    @NotNull
    private String nome;

    @NotNull
    private Double preco;

    @NotNull
    private Integer quantidadeEstoque;

    @NotNull
    private String ncm;

    public ProdutoRequestDTO() {
    }

    public ProdutoRequestDTO(@Size(max = 400) @NotNull String nome, @NotNull Double preco, @NotNull Integer quantidadeEstoque, @NotNull String ncm) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.ncm = ncm;
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