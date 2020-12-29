package br.com.entregapedido.pedidoservice.service;

import br.com.entregapedido.pedidoservice.model.Produto;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "produto-service", fallbackFactory = ProdutoServiceFallbackFactory.class)
public interface ProdutoServiceFeign {

    @RequestMapping(value = "/produto/getById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Produto getById(@PathVariable("id") Long id);

}

@Component
class ProdutoServiceFallbackFactory implements FallbackFactory<ProdutoServiceFeign> {

    @Override
    public ProdutoServiceFeign create(Throwable cause) {
        return new ProdutoServiceFallback(cause);
    }
}

class ProdutoServiceFallback implements ProdutoServiceFeign {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Throwable cause;

    public ProdutoServiceFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public Produto getById(Long id) {

        if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
            logger.error("404 error took place when getById(produto) was called with produtoId: " + id + ". Error message: "
                    + cause.getLocalizedMessage());
        } else {
            logger.error("Other error took place: " + cause.getLocalizedMessage());
        }

        return new Produto();
    }
}

