package br.com.entregapedido.produtoservice.controller;


import br.com.entregapedido.produtoservice.repository.ProdutoRepository;
import br.com.entregapedido.produtoservice.dto.ProdutoRequestDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoRequestEstoqueDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoResponseDTO;
import br.com.entregapedido.produtoservice.service.ProdutoService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoControllerTest.class);

    @InjectMocks
    ProdutoController produtoController;

    @Mock
    ProdutoRepository produtoRepository;

    @Mock
    ProdutoService produtoService;

    @Test
    public void registerProdutoTest() {

        logger.info("Início do test unitário do registerProdutoTest");

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ProdutoRequestDTO produtoRequestDTOMock = new ProdutoRequestDTO(
                "Teste mock",
                25.50,
                20,
                "453434"
        );
        when(produtoService.salvarProduto(any(ProdutoRequestDTO.class))).thenReturn(1L);
        ResponseEntity<?> responseEntity = produtoController.registerProduto(produtoRequestDTOMock);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertNotNull(responseEntity.getBody());
    }

    @Test
    public void getProdutoByNcmTest() {

        logger.info("Início do test unitário do getProdutoByNcmTest");

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(
                5L,
                "Produto Teste",
                25.50,
                20,
                "234233"
        );
        when(produtoRepository.existsByNcm("234233")).thenReturn(true);
        when(produtoService.getProdutoByNcm("234233")).thenReturn(produtoResponseDTO);
        ResponseEntity<ProdutoResponseDTO> responseEntity = produtoController.getProdutoByNcm("234233");

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertEquals("234233", responseEntity.getBody().getNcm());
    }

    @Test
    public void aumentarQuantidadeEstoqueTest() {

        logger.info("Início do test unitário do aumentarQuantidadeEstoqueTest");

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ProdutoRequestEstoqueDTO produtoRequestEstoqueDTO = new ProdutoRequestEstoqueDTO(
                "234234",
                25
        );
        when(produtoRepository.existsByNcm(produtoRequestEstoqueDTO.getNcm())).thenReturn(true);
        when(produtoService.increaseStockQuantity(produtoRequestEstoqueDTO)).thenReturn(1L);
        ResponseEntity<?> responseEntity = produtoController.aumentarQuantidadeEstoque(produtoRequestEstoqueDTO);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertNotNull(responseEntity.getBody());
    }

}
