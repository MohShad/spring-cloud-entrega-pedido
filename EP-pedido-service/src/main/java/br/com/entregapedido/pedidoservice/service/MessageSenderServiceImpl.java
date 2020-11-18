package br.com.entregapedido.pedidoservice.service;

import br.com.entregapedido.pedidoservice.model.Pedido;
import br.com.entregapedido.pedidoservice.model.PedidoStatus;
import br.com.entregapedido.pedidoservice.repository.PedidoRepository;
import br.com.entregapedido.pedidoservice.config.ConfigRabbitMQ;
import br.com.entregapedido.pedidoservice.dto.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageSenderServiceImpl implements MessageSenderService {

    private static final Logger logger = LoggerFactory.getLogger(MessageSenderServiceImpl.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    private final RabbitTemplate rabbitTemplate;

    public MessageSenderServiceImpl(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MessageDTO messageDTO) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String message = mapper.writeValueAsString(messageDTO);
            rabbitTemplate.convertAndSend(ConfigRabbitMQ.EXCHANGE_NAME, ConfigRabbitMQ.ROUTING_KEY, message);
            List<Pedido> listPedido = pedidoRepository.findByNumeroPedido(messageDTO.getNumeroPedido());
            for (Pedido pedido : listPedido) {
                pedido.setStatus(PedidoStatus.ENVIADO);
                pedidoRepository.save(pedido);
            }
            logger.info("RabbitMQ - Pedido enviado para a fila de Entrega.");
        } catch (Exception e) {
            logger.error("RabbitMQ - Erro ao enviar para a fila de Entrega!");
            e.printStackTrace();
        }
    }
}