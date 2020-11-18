package br.com.entregapedido.pedidoservice.dto;

import br.com.entregapedido.pedidoservice.model.PedidoStatus;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "pedido")
public class PedidoResponseDTO {

    private Long id;
    private String dataPedido;
    private String dataEntrega;
    private String descricao;
    private String numeroPedido;
    private Double valorTotal;
    private PedidoStatus status;
    private ClienteResponseDTO cliente;
    private List<ProdutoResponsePedidoDTO> produto;

    public PedidoResponseDTO() {
    }

    public PedidoResponseDTO(Long id, String dataPedido, String dataEntrega, String descricao, String numeroPedido, Double valorTotal, PedidoStatus status, ClienteResponseDTO cliente, List<ProdutoResponsePedidoDTO> produto) {
        this.id = id;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.descricao = descricao;
        this.numeroPedido = numeroPedido;
        this.valorTotal = valorTotal;
        this.status = status;
        this.cliente = cliente;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public ClienteResponseDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
    }

    public List<ProdutoResponsePedidoDTO> getProduto() {
        return produto;
    }

    public void setProduto(List<ProdutoResponsePedidoDTO> produto) {
        this.produto = produto;
    }
}