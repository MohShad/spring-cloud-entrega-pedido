package br.com.entregapedido.clienteservice.controller;


import br.com.entregapedido.clienteservice.dto.ClienteRequestDTO;
import br.com.entregapedido.clienteservice.dto.ClienteResponseDTO;
import br.com.entregapedido.clienteservice.service.ClienteService;
import br.com.entregapedido.clienteservice.repository.ClienteRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ClienteControllerTest.class);

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteService clienteService;

    @Test
    public void registerClienteTest() {

        logger.info("Início do test unitário do registerClienteTest");

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ClienteRequestDTO clienteRequestDTOMock = new ClienteRequestDTO(
                "Mohammad Shadnik",
                "03423423423",
                "Av Jorge Casoni",
                "Rua Maranhão",
                "86010-250",
                "Londrina",
                "PR",
                "mohammad.shadnik@gmail.com"
        );
        when(clienteRepository.existsByCpf("03423423423")).thenReturn(false);
        when(clienteService.salvarCliente(clienteRequestDTOMock)).thenReturn(1L);
        ResponseEntity<?> responseEntity = clienteController.registerCliente(clienteRequestDTOMock);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void getClienteByCpfTest() {

        logger.info("Início do test unitário do getClienteByCpf");
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(
                1L,
                "Mohammad Shadnik",
                "03423423423",
                "Av Jorge Casoni",
                "Rua Maranhão",
                "86010-250",
                "Londrina",
                "PR",
                "mohammad.shadnik@gmail.com"
        );
        when(clienteRepository.existsByCpf("03423423423")).thenReturn(true);
        when(clienteService.getClienteByCpf("03423423423")).thenReturn(clienteResponseDTO);
        ResponseEntity<ClienteResponseDTO> responseEntity = clienteController.getClienteByCpf("03423423423");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertEquals("mohammad.shadnik@gmail.com", responseEntity.getBody().getEmail());
    }
}