package br.com.entregapedido.pedidoservice.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "cliente")
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String enderecoEntrega;
    private String cep;
    private String cidade;
    private String estado;
    private String email;

    public ClienteResponseDTO() {
    }

    public ClienteResponseDTO(Long id, String nome, String cpf, String endereco, String enderecoEntrega, String cep, String cidade, String estado, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.enderecoEntrega = enderecoEntrega;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.email = email;
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
}