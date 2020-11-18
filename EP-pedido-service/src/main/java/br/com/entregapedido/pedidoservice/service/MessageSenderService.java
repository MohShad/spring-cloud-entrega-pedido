package br.com.entregapedido.pedidoservice.service;

        import br.com.entregapedido.pedidoservice.dto.MessageDTO;
        import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessageSenderService {

    void sendMessage(MessageDTO messageDTO) throws JsonProcessingException;
}
