package br.com.entregapedido.produtoservice.controller;

import br.com.entregapedido.produtoservice.repository.ProdutoRepository;
import br.com.entregapedido.produtoservice.ApiResponseDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoRequestDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoRequestEstoqueDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoResponseDTO;
import br.com.entregapedido.produtoservice.dto.ProdutoResponseSaveDTO;
import br.com.entregapedido.produtoservice.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/produto")
public class ProdutoController {

    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation(value = "Cadastro do produto", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 200, message = "OK.")
    })
    @PostMapping
    public ResponseEntity<?> registerProduto(@ApiParam(value = "Obejto produto para criar produto em banco de dados.", required = true)@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {

        try {
            if (produtoRepository.existsByNcm(produtoRequestDTO.getNcm())) {
                return new ResponseEntity(new ApiResponseDTO(false, "Existe produto registrado com NCM: " + produtoRequestDTO.getNcm() + "."),
                        HttpStatus.BAD_REQUEST);
            }
            Long id = produtoService.salvarProduto(produtoRequestDTO);

            return new ResponseEntity(new ProdutoResponseSaveDTO(true, "Produto registrado com sucesso.", id),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Buscar um produto por NCM", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 200, message = "OK.")
    })
    @GetMapping("/{ncm}")
    public ResponseEntity<ProdutoResponseDTO> getProdutoByNcm(@Valid @PathVariable("ncm") String ncm) {

        try {
            if (!produtoRepository.existsByNcm(ncm)) {
                return new ResponseEntity(new ApiResponseDTO(false, "Produto não encontrado."),
                        HttpStatus.BAD_REQUEST);
            }
            ProdutoResponseDTO produtoResponseDTO = produtoService.getProdutoByNcm(ncm);

            return new ResponseEntity<ProdutoResponseDTO>(produtoResponseDTO, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Adicionar mais produto no estoque", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 200, message = "Qauntidade produto adicionado com sucesso.")
    })
    @PostMapping("/addStockQuantity")
    public ResponseEntity<?> aumentarQuantidadeEstoque(@Valid @RequestBody ProdutoRequestEstoqueDTO produtoRequestEstoqueDTO) {

        try {
            if (!produtoRepository.existsByNcm(produtoRequestEstoqueDTO.getNcm())) {
                return new ResponseEntity(new ApiResponseDTO(false, "Produto não encontrado."),
                        HttpStatus.BAD_REQUEST);
            }
            if (produtoRequestEstoqueDTO.getQuantidadeEstoque() <= 0) {
                return new ResponseEntity(new ApiResponseDTO(false, "Quantidade deve ser maior que 0."),
                        HttpStatus.BAD_REQUEST);
            }
            Long id = produtoService.increaseStockQuantity(produtoRequestEstoqueDTO);

            return new ResponseEntity(new ProdutoResponseSaveDTO(true, "Quantidade estoque do produto alterado com sucesso.", id),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

