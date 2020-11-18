package br.com.entregapedido.pedidoservice.dto;


public class PedidoResponseSaveDTO {

    private Boolean success;
    private String message;
    private String numeroPedido;

    public PedidoResponseSaveDTO(Boolean success, String message, String numeroPedido) {
        this.success = success;
        this.message = message;
        this.numeroPedido = numeroPedido;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
}
