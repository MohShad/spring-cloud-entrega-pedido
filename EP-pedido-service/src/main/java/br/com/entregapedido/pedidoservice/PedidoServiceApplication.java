package br.com.entregapedido.pedidoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("br.com.entregapedido.pedidoservice.model")
@EnableJpaRepositories("br.com.entregapedido.pedidoservice.repository")
public class PedidoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoServiceApplication.class, args);
	}

}
