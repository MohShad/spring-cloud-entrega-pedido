package br.com.entregapedido.pedidoservice.service;

import br.com.entregapedido.pedidoservice.model.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cliente-service")
public interface ClienteServiceFeign {

    @RequestMapping(value = "/cliente/getById/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Cliente getById(@PathVariable("id") Long id);

}
