package br.com.entregapedido.entregaservice.service;

import br.com.entregapedido.entregaservice.model.Cliente;
import br.com.entregapedido.entregaservice.model.Entrega;
import br.com.entregapedido.entregaservice.model.Pedido;
import br.com.entregapedido.entregaservice.model.Produto;
import br.com.entregapedido.entregaservice.repository.ClienteRepository;
import br.com.entregapedido.entregaservice.repository.EntregaRepository;
import br.com.entregapedido.entregaservice.repository.PedidoRepository;
import br.com.entregapedido.entregaservice.repository.ProdutoRepository;
import br.com.entregapedido.entregaservice.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EntregaServiceImpl implements EntregaService {

    private static final Logger logger = LoggerFactory.getLogger(EntregaServiceImpl.class);

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public MessageDTO saveEntrega(MessageDTO messageDTO) {
        try {
            String numeroEntrega = UUID.randomUUID().toString();
            for (int i = 0; messageDTO.getProduto().size() > i; i++) {
                Entrega entrega = new Entrega();

                Date createdAt = new Date();
                entrega.setCreatedAt(createdAt);
                entrega.setEnderecoEntrega(messageDTO.getEnderecoEntrega());
                entrega.setNumeroPedido(messageDTO.getNumeroPedido());
                entrega.setValorTotal(messageDTO.getProduto().get(i).getValorTotal());
                entrega.setNumeroEntrega(numeroEntrega);
                Optional<Cliente> cliente = clienteRepository.findById(messageDTO.getCliente_id());
                entrega.setCliente(cliente.get());
                Optional<Produto> produto = produtoRepository.findById(messageDTO.getProduto().get(i).getId());
                entrega.setQuantidadeProduto(messageDTO.getProduto().get(i).getQauantidade());
                entrega.setProduto(produto.get());
                Optional<Pedido> pedido = pedidoRepository.findById(messageDTO.getId());
                entrega.setPedido(pedido.get());

                entregaRepository.save(entrega);
            }

            return messageDTO;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Entrega> getAllEntregas() {
        return entregaRepository.findAll();
    }
}