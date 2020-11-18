package br.com.entregapedido.clienteservice.dto;

public class ClienteResponseSaveDTO {

    private Boolean success;
    private String message;
    private Long id;

    public ClienteResponseSaveDTO(Boolean success, String message, Long id) {
        this.success = success;
        this.message = message;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
