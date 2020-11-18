package br.com.entregapedido.entregaservice.service;

import br.com.entregapedido.entregaservice.model.Entrega;
import br.com.entregapedido.entregaservice.dto.MessageDTO;

import java.util.List;

public interface EntregaService {

    MessageDTO saveEntrega(MessageDTO messageDTO);

    List<Entrega> getAllEntregas();
}
