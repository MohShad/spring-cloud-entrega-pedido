package br.com.entregapedido.produtoservice.service;

import br.com.entregapedido.produtoservice.model.Produto;
import br.com.entregapedido.produtoservice.repository.ProdutoRepository;
import br.com.entregapedido.produtoservice.dto.ProdutoRequestDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoRequestEstoqueDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoResponseDTO;
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

public class ProdutoServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoServiceImplTest.class);

    @Mock
    private ProdutoRequestDTO produtoRequestDTOMock;

    @Mock
    private Produto produtoMock;

    @Mock
    private ProdutoRequestEstoqueDTO produtoRequestEstoqueDTOMock;

    @Mock
    private ProdutoRepository produtoRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(ProdutoServiceImplTest.class);
    }

    @Before
    public void init() {

        produtoRequestDTOMock = new ProdutoRequestDTO(
                "Produto 01",
                25.50D,
                48,
                "345678"
        );

        produtoMock = new Produto(
                "Produto 01",
                25.50D,
                48,
                "345678",
                new Date(),
                new Date()
        );
        produtoMock.setId(15L);

        produtoRequestEstoqueDTOMock = new ProdutoRequestEstoqueDTO(
                "345678",
                60
        );

        when(produtoRepository.findByNcm(produtoRequestDTOMock.getNcm())).thenReturn(produtoMock);
    }

    @Test
    public void salvarProdutoTest() {

        try {
            logger.info("Início do test unitário do salvarProdutoTest");

            Produto pr = new Produto();
            pr.setNome(produtoRequestDTOMock.getNome());
            pr.setPreco(produtoRequestDTOMock.getPreco());
            pr.setNcm(produtoRequestDTOMock.getNcm());
            pr.setQuantidadeEstoque(produtoRequestDTOMock.getQuantidadeEstoque());

            produtoRepository.save(pr);

            assertNotNull(pr);
            assertEquals("345678", pr.getNcm());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @Test
    public void getProdutoByNcmTest() {

        logger.info("Início do test unitário do getProdutoByNcmTest");

        String ncm = "345678";
        Produto produto = produtoRepository.findByNcm(ncm);
        ProdutoResponseDTO produtoResponseDTOMock = new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidadeEstoque(), produto.getNcm());
        assertNotNull(produtoResponseDTOMock);
        assertEquals("345678", produtoResponseDTOMock.getNcm());
    }

    @Test
    public void increaseStockQuantityTest() {
        try {
            logger.info("Início do test unitário do increaseStockQuantityTest");

            Produto produto = produtoRepository.findByNcm(produtoRequestEstoqueDTOMock.getNcm());
            int novaQuantidade = produto.getQuantidadeEstoque() + produtoRequestEstoqueDTOMock.getQuantidadeEstoque();
            produto.setQuantidadeEstoque(novaQuantidade);
            produtoRepository.save(produto);

            assertNotNull(produto);
            assertEquals((Integer) 108, produto.getQuantidadeEstoque());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}