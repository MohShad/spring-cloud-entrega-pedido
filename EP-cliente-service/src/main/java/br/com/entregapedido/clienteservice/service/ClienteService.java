package br.com.entregapedido.clienteservice.service;

import br.com.entregapedido.clienteservice.dto.ClienteRequestDTO;
import br.com.entregapedido.clienteservice.dto.ClienteResponseDTO;

public interface ClienteService {

    Long salvarCliente(ClienteRequestDTO clienteRequestDTO);

    ClienteResponseDTO getClienteByCpf(String cpf);
}
