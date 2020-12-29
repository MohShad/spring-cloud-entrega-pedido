package br.com.entregapedido.pedidoservice.service;

import br.com.entregapedido.pedidoservice.model.Cliente;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cliente-service", fallbackFactory = ClienteServiceFallbackFactory.class)
public interface ClienteServiceFeign {

    @RequestMapping(value = "/cliente/getById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Cliente getById(@PathVariable("id") Long id);

}

@Component
class ClienteServiceFallbackFactory implements FallbackFactory<ClienteServiceFeign> {

    @Override
    public ClienteServiceFeign create(Throwable cause) {
        return new ClienteServiceFallback(cause);
    }
}

class ClienteServiceFallback implements ClienteServiceFeign {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Throwable cause;

    public ClienteServiceFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public Cliente getById(Long id) {

        if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
            logger.error("404 error took place when getById(cliente) was called with clieteId: " + id + ". Error message: "
                    + cause.getLocalizedMessage());
        } else {
            logger.error("Other error took place: " + cause.getLocalizedMessage());
        }

        return new Cliente();
    }
}
