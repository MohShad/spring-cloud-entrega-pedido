package br.com.entregapedido.pedidoservice.controller;

import br.com.entregapedido.pedidoservice.model.Cliente;
import br.com.entregapedido.pedidoservice.model.Pedido;
import br.com.entregapedido.pedidoservice.model.PedidoStatus;
import br.com.entregapedido.pedidoservice.model.Produto;
import br.com.entregapedido.pedidoservice.repository.ClienteRepository;
import br.com.entregapedido.pedidoservice.repository.ProdutoRepository;
import br.com.entregapedido.pedidoservice.dto.*;
import br.com.entregapedido.pedidoservice.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static br.com.entregapedido.pedidoservice.model.PedidoStatus.ABERTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(PedidoControllerTest.class);

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private PedidoService pedidoService;

    @Mock
    private Produto produtoMock01;

    @Mock
    private Produto produtoMock02;

    @Mock
    private ProdutoResponsePedidoDTO produtoMock03;

    @Mock
    private ProdutoResponsePedidoDTO produtoMock04;

    @Test
    public void registerPedidoTest() {

        logger.info("Início do test unitário do registerPedidoTest");
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        List<ProdutoQuantidadeRequestDTO> listProdutoQuantidadeRequestDTO = new ArrayList<>();

        produtoMock01 = new Produto(
                "Produto 01",
                25.50D,
                48,
                "345678",
                new Date(),
                new Date()
        );
        produtoMock01.setId(15L);

        produtoMock02 = new Produto(
                "Produto 02",
                25.50D,
                58,
                "3456789",
                new Date(),
                new Date()
        );
        produtoMock02.setId(16L);

        ProdutoQuantidadeRequestDTO produtoQuantidadeRequestDTOMock01 = new ProdutoQuantidadeRequestDTO(
                1L,
                5
        );
        listProdutoQuantidadeRequestDTO.add(produtoQuantidadeRequestDTOMock01);

        ProdutoQuantidadeRequestDTO produtoQuantidadeRequestDTOMock02 = new ProdutoQuantidadeRequestDTO(
                2L,
                7
        );
        listProdutoQuantidadeRequestDTO.add(produtoQuantidadeRequestDTOMock02);

        PedidoRequestDTO pedidoRequestDTO = new PedidoRequestDTO(
                "Pedido mock",
                3L,
                listProdutoQuantidadeRequestDTO
        );

        Cliente clienteMock = new Cliente(
                "Mohammad Shadnik",
                "03423423423",
                new Date(),
                new Date(),
                "Av Jorge Casoni",
                "Rua Maranhão",
                "86010-250",
                "Londrina",
                "PR",
                "mohammad.shadnik@gmail.com"
        );
        clienteMock.setId(3L);

        when(produtoRepository.findById(pedidoRequestDTO.getProduto().get(0).getId())).thenReturn(Optional.ofNullable(produtoMock01));
        when(produtoRepository.findById(pedidoRequestDTO.getProduto().get(1).getId())).thenReturn(Optional.ofNullable(produtoMock02));
        when(clienteRepository.findById(pedidoRequestDTO.getClienteId())).thenReturn(Optional.ofNullable(clienteMock));
        ResponseEntity<?> responseEntity = pedidoController.registerPedido(pedidoRequestDTO);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void getPedidoByIdTest() {

        logger.info("Início do test unitário do getPedidoByIdTest");
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Cliente clienteMock = new Cliente(
                "Mohammad Shadnik",
                "03423423423",
                new Date(),
                new Date(),
                "Av Jorge Casoni",
                "Rua Maranhão",
                "86010-250",
                "Londrina",
                "PR",
                "mohammad.shadnik@gmail.com"
        );
        clienteMock.setId(3L);

        ClienteResponseDTO clienteResponseDTOMock = new ClienteResponseDTO(
                12L,
                "Mohammad Shadnik",
                "03423423423",
                "Av Jorge Casoni",
                "Rua Maranhão",
                "86010-250",
                "Londrina",
                "PR",
                "mohammad.shadnik@gmail.com"
        );

        produtoMock01 = new Produto(
                "Produto 01",
                25.50D,
                48,
                "345678",
                new Date(),
                new Date()
        );
        produtoMock01.setId(15L);

        produtoMock02 = new Produto(
                "Produto 01",
                25.50D,
                48,
                "345678",
                new Date(),
                new Date()
        );
        produtoMock02.setId(16L);

        List<ProdutoResponsePedidoDTO> listProdutoResponsePedidoDTO = new ArrayList<>();
        produtoMock03 = new ProdutoResponsePedidoDTO(
                15L,
                "Produto 01",
                140.00,
                "345678",
                12
        );
        listProdutoResponsePedidoDTO.add(produtoMock03);

        produtoMock04 = new ProdutoResponsePedidoDTO(
                16L,
                "Produto 01",
                150.00,
                "348678",
                12
        );
        produtoMock04.setId(16L);
        listProdutoResponsePedidoDTO.add(produtoMock04);

        List<Pedido> listPedido = new ArrayList<>();

        Pedido pedido01 = new Pedido(
                new Date(),
                new Date(),
                new Date(),
                "Descrição teste",
                123.00,
                23,
                ABERTO,
                "3c938d38-389f-433d-9622-16d31fd04d11",
                clienteMock,
                produtoMock01
        );

        listPedido.add(pedido01);
        Pedido pedido02 = new Pedido(
                new Date(),
                new Date(),
                new Date(),
                "Descrição teste",
                123.00,
                23,
                ABERTO,
                "3c938d38-389f-433d-9622-16d31fd04d11",
                clienteMock,
                produtoMock02
        );
        listPedido.add(pedido02);

        PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO(
                12L,
                "new Date()",
                "new Date()",
                "Descrição teste",
                "3c938d38-389f-433d-9622-16d31fd04d11",
                123.00,
                PedidoStatus.ENVIADO,
                clienteResponseDTOMock,
                listProdutoResponsePedidoDTO
        );

        when(pedidoService.getPedidoByNumeroPedido("3c938d38-389f-433d-9622-16d31fd04d11")).thenReturn(pedidoResponseDTO);
        PedidoResponseDTO responseEntity = pedidoService.getPedidoByNumeroPedido("3c938d38-389f-433d-9622-16d31fd04d11");

        assertThat(responseEntity.getCliente().getId()).isEqualTo(12);
        assertNotNull(responseEntity);
    }
}