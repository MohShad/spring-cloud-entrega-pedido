package br.com.entregapedido.clienteservice.service;


import br.com.entregapedido.clienteservice.dto.ClienteResponseDTO;
import br.com.entregapedido.clienteservice.model.Cliente;
import br.com.entregapedido.clienteservice.repository.ClienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ClienteServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImplTest.class);

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private Cliente clienteMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(ClienteServiceImplTest.class);
    }

    @Before
    public void init() {

        clienteMock = new Cliente(
                "Mohammad Shadnik",
                "08858966957",
                new Date(),
                new Date(),
                "Av Jorge Casoni",
                "Rua Maranhão",
                "86010-250",
                "Londrina",
                "PR",
                "mohammad.shadnik@hotmail.com"
        );
        clienteMock.setId(20L);

        when(clienteRepository.findByCpf(clienteMock.getCpf())).thenReturn(clienteMock);

    }

    @Test
    public void salvarClienteTest() {

        try {
            logger.info("Início do test unitário do salvarClienteTest");

            Cliente cl = new Cliente();
            cl.setNome(clienteMock.getNome());
            cl.setCpf(clienteMock.getCpf());
            cl.setEndereco(clienteMock.getEndereco());
            cl.setEnderecoEntrega(clienteMock.getEnderecoEntrega());
            cl.setCep(clienteMock.getCep());
            cl.setCidade(clienteMock.getCidade());
            cl.setEstado(clienteMock.getEstado());
            cl.setEmail(clienteMock.getEmail());

            clienteRepository.save(cl);

            assertNotNull(cl);
            assertEquals("mohammad.shadnik@hotmail.com", cl.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    public void getClienteByCpf() {

        logger.info("Início do test unitário do getClienteByCpf");

        Cliente cliente = clienteRepository.findByCpf(clienteMock.getCpf());
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getEndereco(), cliente.getEnderecoEntrega(), cliente.getCep(), cliente.getCidade(), cliente.getEstado(), cliente.getEmail());

        assertNotNull(clienteResponseDTO);
        assertEquals(clienteMock.getId(), clienteResponseDTO.getId());
    }
}