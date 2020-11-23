package br.com.entregapedido.pedidoservice.controller;

import br.com.entregapedido.pedidoservice.ApiResponseDTO;
import br.com.entregapedido.pedidoservice.dto.PedidoRequestDTO;
import br.com.entregapedido.pedidoservice.dto.PedidoResponseDTO;
import br.com.entregapedido.pedidoservice.dto.PedidoResponseSaveDTO;
import br.com.entregapedido.pedidoservice.model.Cliente;
import br.com.entregapedido.pedidoservice.model.Pedido;
import br.com.entregapedido.pedidoservice.model.Produto;
import br.com.entregapedido.pedidoservice.repository.ClienteRepository;
import br.com.entregapedido.pedidoservice.repository.PedidoRepository;
import br.com.entregapedido.pedidoservice.repository.ProdutoRepository;
import br.com.entregapedido.pedidoservice.service.PedidoService;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @ApiOperation(value = "Cadastro pedido", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 200, message = "OK.")
    })
    @PostMapping
    public ResponseEntity<?> registerPedido(
            @ApiParam(value = "Obejto pedido para criar pedido em banco de dados.", required = true)
            @Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {

        try {
            for (int i = 0; pedidoRequestDTO.getProduto().size() > i; i++) {
                Optional<Produto> produto = produtoRepository.findById(pedidoRequestDTO.getProduto().get(i).getId());
                Produto pr = produto.get();
                if (!produto.isPresent()) {
                    return new ResponseEntity(new ApiResponseDTO(false, "Produto com id " + pedidoRequestDTO.getProduto().get(i).getId() + " não encontrado!"),
                            HttpStatus.BAD_REQUEST);
                }
                if (pedidoRequestDTO.getProduto().get(i).getQuantidade() <= 0) {
                    return new ResponseEntity(new ApiResponseDTO(false, "Quantidade deve ser maior que 0."),
                            HttpStatus.BAD_REQUEST);
                }
                if (pedidoRequestDTO.getProduto().get(i).getQuantidade() > pr.getQuantidadeEstoque()) {
                    return new ResponseEntity(new ApiResponseDTO(false, "Não temos estoque suficiente! Existem " + pr.getQuantidadeEstoque() + " produto(s) em estoque. Id produto: " + pedidoRequestDTO.getProduto().get(i).getId()),
                            HttpStatus.BAD_REQUEST);
                }
            }
            Optional<Cliente> cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId());
            if (!cliente.isPresent()) {
                return new ResponseEntity(new ApiResponseDTO(false, "Cliente não encontrado!"),
                        HttpStatus.BAD_REQUEST);
            }

            String numeroPedido = pedidoService.salvarPedido(pedidoRequestDTO);

            return new ResponseEntity(new PedidoResponseSaveDTO(true, "Pedido registrado com sucesso.", numeroPedido),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Buscar um pedido por numeroPedido", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 200, message = "OK.")
    })
    @GetMapping("/{numeroPedido}")
    public ResponseEntity<PedidoResponseDTO> getPedidoById(@Valid @PathVariable("numeroPedido") String numeroPedido) {

        try {
            List<Pedido> pedido = pedidoRepository.findByNumeroPedido(numeroPedido);
            if (pedido.isEmpty()) {
                return new ResponseEntity(new ApiResponseDTO(false, "Pedido não encontrado."),
                        HttpStatus.BAD_REQUEST);
            }

            PedidoResponseDTO pedidoResponseDTO = pedidoService.getPedidoByNumeroPedido(numeroPedido);

            return new ResponseEntity<PedidoResponseDTO>(pedidoResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
