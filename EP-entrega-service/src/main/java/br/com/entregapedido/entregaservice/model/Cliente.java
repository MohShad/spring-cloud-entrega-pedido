package br.com.entregapedido.entregaservice.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cliente",schema = "entrega_pedido")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "nome", nullable = false)
    private String nome;

    @Basic
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Basic
    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Basic
    @LastModifiedDate
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;

    @Basic
    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Basic
    @Column(name = "endereco_entrega", nullable = false)
    private String enderecoEntrega;

    @Basic
    @Column(name = "cep", nullable = false)
    private String cep;

    @Basic
    @Column(name = "cidade", nullable = false)
    private String cidade;

    @Basic
    @Column(name = "estado", nullable = false)
    private String estado;

    @Basic
    @Column(name = "email", nullable = false)
    private String email;


    public Cliente() {
    }

    public Cliente(String nome, String cpf, Date createdAt, Date updatedAt, String endereco, String enderecoEntrega, String cep, String cidade, String estado, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.endereco = endereco;
        this.enderecoEntrega = enderecoEntrega;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.email = email;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) &&
                Objects.equals(nome, cliente.nome) &&
                Objects.equals(cpf, cliente.cpf) &&
                Objects.equals(createdAt, cliente.createdAt) &&
                Objects.equals(updatedAt, cliente.updatedAt) &&
                Objects.equals(endereco, cliente.endereco) &&
                Objects.equals(enderecoEntrega, cliente.enderecoEntrega) &&
                Objects.equals(cep, cliente.cep) &&
                Objects.equals(cidade, cliente.cidade) &&
                Objects.equals(estado, cliente.estado) &&
                Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nome, cpf, createdAt, updatedAt, endereco, enderecoEntrega, cep, cidade, estado, email);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", endereco='" + endereco + '\'' +
                ", enderecoEntrega='" + enderecoEntrega + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}