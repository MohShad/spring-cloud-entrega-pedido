package br.com.entregapedido.pedidoservice;

import br.com.entregapedido.pedidoservice.exceptionhandler.FeignErrorDecoder;
import br.com.entregapedido.pedidoservice.service.ClienteServiceFeign;
import br.com.entregapedido.pedidoservice.service.ProdutoServiceFeign;
import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableFeignClients()
@EnableCircuitBreaker
@SpringBootApplication
@EntityScan("br.com.entregapedido.pedidoservice.model")
@EnableJpaRepositories("br.com.entregapedido.pedidoservice.repository")
public class PedidoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PedidoServiceApplication.class, args);
    }

    @Bean
    Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

}
