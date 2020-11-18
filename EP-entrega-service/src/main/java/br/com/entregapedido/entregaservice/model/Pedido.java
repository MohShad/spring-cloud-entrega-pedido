package br.com.entregapedido.entregaservice.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pedido", schema = "entrega_pedido")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Basic
    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @Basic
    @Column(name = "data_entrega", nullable = false)
    private Date dataEntrega;

    @Basic
    @Column(name = "descricao")
    private String descricao;

    @Basic
    @Column(name = "valor_total")
    private Double valorTotal;

    @Basic
    @Column(name = "quantidade_produto")
    private Integer quantidadeProduto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PedidoStatus status;

    @Basic
    @GenericGenerator(name = "numero_pedido", strategy = "uuid2")
    private String numeroPedido;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Cliente.class)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Produto.class)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Pedido() {
    }

    public Pedido(Date createdAt, Date updatedAt, Date dataEntrega, String descricao, Double valorTotal, Integer quantidadeProduto, PedidoStatus status, String numeroPedido, Cliente cliente, Produto produto) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dataEntrega = dataEntrega;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.quantidadeProduto = quantidadeProduto;
        this.status = status;
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.produto = produto;
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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) &&
                Objects.equals(createdAt, pedido.createdAt) &&
                Objects.equals(updatedAt, pedido.updatedAt) &&
                Objects.equals(dataEntrega, pedido.dataEntrega) &&
                Objects.equals(descricao, pedido.descricao) &&
                Objects.equals(valorTotal, pedido.valorTotal) &&
                Objects.equals(quantidadeProduto, pedido.quantidadeProduto) &&
                status == pedido.status &&
                Objects.equals(numeroPedido, pedido.numeroPedido) &&
                Objects.equals(cliente, pedido.cliente) &&
                Objects.equals(produto, pedido.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, dataEntrega, descricao, valorTotal, quantidadeProduto, status, numeroPedido, cliente, produto);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", dataEntrega=" + dataEntrega +
                ", descricao='" + descricao + '\'' +
                ", valorTotal=" + valorTotal +
                ", quantidadeProduto=" + quantidadeProduto +
                ", status=" + status +
                ", numeroPedido='" + numeroPedido + '\'' +
                ", cliente=" + cliente +
                ", produto=" + produto +
                '}';
    }
}
