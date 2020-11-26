package br.com.entregapedido.pedidoservice.service;

import br.com.entregapedido.pedidoservice.model.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "produto-service")
public interface ProdutoServiceFeign {

    @RequestMapping(value = "/produto/getById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Produto getById(@PathVariable("id") Long id);

}
