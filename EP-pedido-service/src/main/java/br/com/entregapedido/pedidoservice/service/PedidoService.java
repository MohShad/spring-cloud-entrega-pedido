package br.com.entregapedido.pedidoservice.service;

import br.com.entregapedido.pedidoservice.dto.PedidoRequestDTO;
import br.com.entregapedido.pedidoservice.dto.PedidoResponseDTO;

public interface PedidoService {

    String salvarPedido(PedidoRequestDTO pedidoRequestDTO);

    PedidoResponseDTO getPedidoByNumeroPedido(String numeroPedido);
}
