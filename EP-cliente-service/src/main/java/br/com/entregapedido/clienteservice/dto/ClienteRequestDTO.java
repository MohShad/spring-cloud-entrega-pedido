package br.com.entregapedido.clienteservice.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteRequestDTO {

    @Size(max = 100)
    @NotNull
    private String nome;

    @Size(max = 11)
    @NotNull
    private String cpf;

    @Size(max = 100)
    @NotNull
    private String endereco;

    @Size(max = 100)
    @NotNull
    private String enderecoEntrega;

    @Size(max = 10)
    @NotNull
    private String cep;

    @Size(max = 50)
    @NotNull
    private String cidade;

    @Size(max = 50)
    @NotNull
    private String estado;

    @Size(max = 100)
    @NotNull
    private String email;

    public ClienteRequestDTO() {
    }

    public ClienteRequestDTO(@Size(max = 100) @NotNull String nome, @Size(max = 11) @NotNull String cpf, @Size(max = 100) @NotNull String endereco, @Size(max = 100) @NotNull String enderecoEntrega, @Size(max = 10) @NotNull String cep, @Size(max = 50) @NotNull String cidade, @Size(max = 50) @NotNull String estado, @Size(max = 100) @NotNull String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.enderecoEntrega = enderecoEntrega;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.email = email;
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