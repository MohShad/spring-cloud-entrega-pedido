package br.com.entregapedido.pedidoservice.service;

import br.com.entregapedido.pedidoservice.model.Cliente;
import br.com.entregapedido.pedidoservice.model.Pedido;
import br.com.entregapedido.pedidoservice.model.Produto;
import br.com.entregapedido.pedidoservice.repository.ClienteRepository;
import br.com.entregapedido.pedidoservice.repository.PedidoRepository;
import br.com.entregapedido.pedidoservice.repository.ProdutoRepository;
import br.com.entregapedido.pedidoservice.dto.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.*;

import static br.com.entregapedido.pedidoservice.model.PedidoStatus.ABERTO;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PedidoServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(PedidoServiceImplTest.class);
    SimpleDateFormat dateFormatData = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoRequestDTO pedidoRequestDTOMock;

    @Mock
    private ProdutoQuantidadeRequestDTO produtoQuantidadeRequestDTOMock01;

    @Mock
    private ProdutoQuantidadeRequestDTO produtoQuantidadeRequestDTOMock02;

    @Mock
    private Produto produtoMock01;

    @Mock
    private Produto produtoMock02;

    @Mock
    private Cliente clienteMock;

    @Mock
    private Pedido pedidoMock01;

    @Mock
    private Pedido pedidoMock02;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(PedidoServiceImplTest.class);
    }

    @Before
    public void init() {

        List<ProdutoQuantidadeRequestDTO> listProdutoQuantidadeRequestDTO = new ArrayList<>();
        produtoQuantidadeRequestDTOMock01 = new ProdutoQuantidadeRequestDTO(
                1L,
                10
        );
        listProdutoQuantidadeRequestDTO.add(produtoQuantidadeRequestDTOMock01);

        produtoQuantidadeRequestDTOMock02 = new ProdutoQuantidadeRequestDTO(
                2L,
                12
        );
        listProdutoQuantidadeRequestDTO.add(produtoQuantidadeRequestDTOMock02);

        pedidoRequestDTOMock = new PedidoRequestDTO(
                "Pedido Teste01",
                20L,
                listProdutoQuantidadeRequestDTO
        );

        produtoMock01 = new Produto(
                "Doce",
                50.50,
                30,
                "3242000",
                new Date(),
                new Date()
        );
        produtoMock01.setId(1L);

        produtoMock02 = new Produto(
                "Doce",
                40.50,
                50,
                "3242342",
                new Date(),
                new Date()
        );
        produtoMock02.setId(2L);

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
        clienteMock.setId(10L);

        pedidoMock01 = new Pedido(
                new Date(),
                new Date(),
                new Date(),
                "Pedido mock 01",
                350.0D,
                3,
                ABERTO,
                "5c807d13-6d6d-4b56-a734-0efb381eefc5",
                clienteMock,
                produtoMock01
        );
        pedidoMock02 = new Pedido(
                new Date(),
                new Date(),
                new Date(),
                "Pedido mock 02",
                150.0D,
                1,
                ABERTO,
                "5c807d13-6d6d-4b56-a734-0efb381eefc5",
                clienteMock,
                produtoMock02
        );

        when(produtoRepository.findById(pedidoRequestDTOMock.getProduto().get(0).getId())).thenReturn(Optional.of(produtoMock01));
        when(produtoRepository.findById(pedidoRequestDTOMock.getProduto().get(1).getId())).thenReturn(Optional.of(produtoMock02));
        when(clienteRepository.findById(pedidoRequestDTOMock.getClienteId())).thenReturn(Optional.of(clienteMock));

        when(pedidoRepository.findByNumeroPedido("5c807d13-6d6d-4b56-a734-0efb381eefc5")).thenReturn(Arrays.asList(pedidoMock01, pedidoMock02));

    }

    @Test
    public void salvarPedidoTest() {

        try {
            logger.info("Início do test unitário do salvarPedidoTest");

            String numeroPedido = UUID.randomUUID().toString();
            List<Pedido> listPedido = new ArrayList<>();
            for (int i = 0; pedidoRequestDTOMock.getProduto().size() > i; i++) {
                Optional<Produto> produto = produtoRepository.findById(pedidoRequestDTOMock.getProduto().get(i).getId());
                Optional<Cliente> cliente = clienteRepository.findById(pedidoRequestDTOMock.getClienteId());

                Produto pr = produto.get();
                Cliente cl = cliente.get();
                Pedido pedido = new Pedido();

                Date createdAt = new Date();
                pedido.setCreatedAt(createdAt);
                pedido.setDataEntrega(getDateSevenWorkDays(createdAt));
                pedido.setDescricao(pedidoRequestDTOMock.getDescricao());
                pedido.setNumeroPedido(numeroPedido);
                pedido.setStatus(ABERTO);
                Double valorTotal = pr.getPreco() * pedidoRequestDTOMock.getProduto().get(i).getQuantidade();
                pedido.setValorTotal(valorTotal);
                pedido.setQuantidadeProduto(pedidoRequestDTOMock.getProduto().get(i).getQuantidade());
                pedido.setCliente(cl);
                pedido.setProduto(pr);

                pr.setQuantidadeEstoque(pr.getQuantidadeEstoque() - pedidoRequestDTOMock.getProduto().get(i).getQuantidade());
                produtoRepository.save(pr);

                pedidoRepository.save(pedido);
                listPedido.add(pedido);
            }

            assertNotNull(listPedido.size());

            assertEquals("Mohammad Shadnik", listPedido.get(0).

                    getCliente().

                    getNome());

            assertEquals("3242000", listPedido.get(0).

                    getProduto().

                    getNcm());

            assertEquals((Integer) 38, listPedido.get(1).

                    getProduto().

                    getQuantidadeEstoque());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    public void getPedidoByNumeroPedidoTest() {

        try {

            logger.info("Início do test unitário do getPedidoByNumeroPedidoTest");
            String numeroPedido = "5c807d13-6d6d-4b56-a734-0efb381eefc5";

            List<Pedido> listPedidos = pedidoRepository.findByNumeroPedido(numeroPedido);
            List<ProdutoResponsePedidoDTO> listProdutoResponsePedidoDTO = new ArrayList<>();

            PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO();
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
            Double valorTotal = 0D;

            if (listPedidos != null) {
                for (Pedido pedido : listPedidos) {

                    ProdutoResponsePedidoDTO produtoResponsePedidoDTO = new ProdutoResponsePedidoDTO();
                    produtoResponsePedidoDTO.setId(pedido.getProduto().getId());
                    produtoResponsePedidoDTO.setNome(pedido.getProduto().getNome());
                    produtoResponsePedidoDTO.setNcm(pedido.getProduto().getNcm());
                    produtoResponsePedidoDTO.setPreco(pedido.getProduto().getPreco());
                    produtoResponsePedidoDTO.setQuantidade(pedido.getQuantidadeProduto());
                    valorTotal = valorTotal + pedido.getValorTotal();

                    listProdutoResponsePedidoDTO.add(produtoResponsePedidoDTO);
                }
            }
            clienteResponseDTO.setId(listPedidos.get(0).getCliente().getId());
            clienteResponseDTO.setNome(listPedidos.get(0).getCliente().getNome());
            clienteResponseDTO.setCpf(listPedidos.get(0).getCliente().getCpf());
            clienteResponseDTO.setEndereco(listPedidos.get(0).getCliente().getEndereco());
            clienteResponseDTO.setEnderecoEntrega(listPedidos.get(0).getCliente().getEnderecoEntrega());
            clienteResponseDTO.setCep(listPedidos.get(0).getCliente().getCep());
            clienteResponseDTO.setCidade(listPedidos.get(0).getCliente().getCidade());
            clienteResponseDTO.setEstado(listPedidos.get(0).getCliente().getEstado());
            clienteResponseDTO.setEmail(listPedidos.get(0).getCliente().getEmail());

            pedidoResponseDTO.setId(listPedidos.get(0).getId());
            pedidoResponseDTO.setDataPedido(dateFormatData.format(listPedidos.get(0).getCreatedAt()));
            pedidoResponseDTO.setDataEntrega(dateFormatData.format(listPedidos.get(0).getDataEntrega()));
            pedidoResponseDTO.setDescricao(listPedidos.get(0).getDescricao());
            pedidoResponseDTO.setNumeroPedido(listPedidos.get(0).getNumeroPedido());
            pedidoResponseDTO.setStatus(listPedidos.get(0).getStatus());
            pedidoResponseDTO.setValorTotal(valorTotal);

            pedidoResponseDTO.setCliente(clienteResponseDTO);
            pedidoResponseDTO.setProduto(listProdutoResponsePedidoDTO);

            assertNotNull(pedidoResponseDTO);
            assertEquals(2, pedidoResponseDTO.getProduto().size());
            assertEquals("08858966957", pedidoResponseDTO.getCliente().getCpf());
            assertEquals((Double) 40.5, pedidoResponseDTO.getProduto().get(1).getPreco());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    private Date getDateSevenWorkDays(Date dataPedido) {
        Calendar c = Calendar.getInstance();

        c.setTime(dataPedido);
        c.add(Calendar.DATE, 7);// 7 Work days
        Date currentDatePlusSeven = c.getTime();// convert calendar to date

        return currentDatePlusSeven;
    }

}