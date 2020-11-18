package br.com.entregapedido.entregaservice.service;

import br.com.entregapedido.entregaservice.config.ConfigRabbitMQ;
import br.com.entregapedido.entregaservice.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class MessageListenerServiceImpl implements MessageListenerService {

    private static final Logger logger = LoggerFactory.getLogger(MessageListenerServiceImpl.class);
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private EntregaService entregaService;

    @RabbitListener(queues = ConfigRabbitMQ.DEFAULT_PARSING_QUEUE)
    public void receiveMessage(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MessageDTO messageDTO = mapper.readValue(message, MessageDTO.class);
            logger.info("RabbitMQ - Capturando pedido na fila de Entrega - numeroPedido: " + messageDTO.getNumeroPedido());
            entregaService.saveEntrega(messageDTO);
            latch.countDown();
        } catch (Exception e) {
            logger.error("Erro ao salvar na tabela de Entrega!");
            e.printStackTrace();
        }
        logger.info("RabbitMQ - Received message, Pedido: {}", message.toString());
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
