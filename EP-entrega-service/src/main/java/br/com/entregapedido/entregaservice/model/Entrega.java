package br.com.entregapedido.entregaservice.model;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "entrega", schema = "entrega_pedido")
public class Entrega implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Basic
    @Column(name = "numero_pedido")
    private String numeroPedido;

    @Basic
    @Column(name = "valor_total")
    private Double valorTotal;

    @Basic
    @Column(name = "quantidade_produto")
    private Integer quantidadeProduto;

    @Basic
    @Column(name = "endereco_entrega")
    private String enderecoEntrega;

    @Basic
    @GenericGenerator(name = "numero_entrega", strategy = "uuid2")
    private String numeroEntrega;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Cliente.class)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Produto.class)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Pedido.class)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Entrega() {
    }

    public Entrega(Date createdAt, String numeroPedido, Double valorTotal, Integer quantidadeProduto, String enderecoEntrega, String numeroEntrega, Cliente cliente, Produto produto, Pedido pedido) {
        this.createdAt = createdAt;
        this.numeroPedido = numeroPedido;
        this.valorTotal = valorTotal;
        this.quantidadeProduto = quantidadeProduto;
        this.enderecoEntrega = enderecoEntrega;
        this.numeroEntrega = numeroEntrega;
        this.cliente = cliente;
        this.produto = produto;
        this.pedido = pedido;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Integer quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrega entrega = (Entrega) o;
        return Objects.equals(id, entrega.id) &&
                Objects.equals(createdAt, entrega.createdAt) &&
                Objects.equals(numeroPedido, entrega.numeroPedido) &&
                Objects.equals(valorTotal, entrega.valorTotal) &&
                Objects.equals(quantidadeProduto, entrega.quantidadeProduto) &&
                Objects.equals(enderecoEntrega, entrega.enderecoEntrega) &&
                Objects.equals(numeroEntrega, entrega.numeroEntrega) &&
                Objects.equals(cliente, entrega.cliente) &&
                Objects.equals(produto, entrega.produto) &&
                Objects.equals(pedido, entrega.pedido);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createdAt, numeroPedido, valorTotal, quantidadeProduto, enderecoEntrega, numeroEntrega, cliente, produto, pedido);
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", numeroPedido='" + numeroPedido + '\'' +
                ", valorTotal=" + valorTotal +
                ", quantidadeProduto=" + quantidadeProduto +
                ", enderecoEntrega='" + enderecoEntrega + '\'' +
                ", numeroEntrega='" + numeroEntrega + '\'' +
                ", cliente=" + cliente +
                ", produto=" + produto +
                ", pedido=" + pedido +
                '}';
    }
}
