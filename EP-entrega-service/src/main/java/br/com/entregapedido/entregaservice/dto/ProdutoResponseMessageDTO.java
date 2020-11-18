package br.com.entregapedido.entregaservice.dto;

public class ProdutoResponseMessageDTO {

    private Long id;
    private Integer qauantidade;
    private Double precoUnitario;
    private Double valorTotal;

    public ProdutoResponseMessageDTO() {
    }

    public ProdutoResponseMessageDTO(Long id, Integer qauantidade, Double precoUnitario, Double valorTotal) {
        this.id = id;
        this.qauantidade = qauantidade;
        this.precoUnitario = precoUnitario;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQauantidade() {
        return qauantidade;
    }

    public void setQauantidade(Integer qauantidade) {
        this.qauantidade = qauantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
