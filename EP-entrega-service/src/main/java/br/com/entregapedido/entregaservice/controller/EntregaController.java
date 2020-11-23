package br.com.entregapedido.entregaservice.controller;

import br.com.entregapedido.entregaservice.ApiResponseDTO;
import br.com.entregapedido.entregaservice.dto.EntregaResponseDTO;
import br.com.entregapedido.entregaservice.model.Entrega;
import br.com.entregapedido.entregaservice.service.EntregaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

    private static final Logger logger = LoggerFactory.getLogger(EntregaController.class);

    @Autowired
    private EntregaService entregaService;

    @ApiOperation(value = "Buscar a lista das entregas", produces = "application/json")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 200, message = "OK.")
    })
    @GetMapping("/getAll")
    public @ResponseBody
    ResponseEntity<Object> getAll() {
        List<Entrega> listEntregas = entregaService.getAllEntregas();
        try {
            List<EntregaResponseDTO> listEntregaResponseDTO = new ArrayList<>();
            for (Entrega ent : listEntregas) {
                EntregaResponseDTO entregaResponseDTO = new EntregaResponseDTO();
                entregaResponseDTO.setId(ent.getId());
                entregaResponseDTO.setCreateAt(ent.getCreatedAt());
                entregaResponseDTO.setNumeroPedido(ent.getNumeroPedido());
                entregaResponseDTO.setNumeroEntrega(ent.getNumeroEntrega());
                entregaResponseDTO.setValorTotal(ent.getValorTotal());
                entregaResponseDTO.setQuantidadeProduto(ent.getQuantidadeProduto());
                entregaResponseDTO.setEnderecoEntrega(ent.getEnderecoEntrega());
                entregaResponseDTO.setClienteId(ent.getCliente().getId().intValue());
                entregaResponseDTO.setProdutoId(ent.getProduto().getId().intValue());
                entregaResponseDTO.setPedidoId(ent.getPedido().getId().intValue());
                listEntregaResponseDTO.add(entregaResponseDTO);
            }
            return new ResponseEntity<Object>(listEntregaResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(new ApiResponseDTO(false, "Internal error: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
