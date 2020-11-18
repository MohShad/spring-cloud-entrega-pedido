package br.com.entregapedido.clienteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EntityScan("br.com.entregapedido.clienteservice.model")
@EnableJpaRepositories("br.com.entregapedido.clienteservice.repository")
@SpringBootApplication
public class ClienteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteServiceApplication.class, args);
	}

}
