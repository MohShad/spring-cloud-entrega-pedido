package br.com.entregapedido.entregaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("br.com.entregapedido.entregaservice.model")
@EnableJpaRepositories("br.com.entregapedido.entregaservice.repository")
public class EntregaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregaServiceApplication.class, args);
	}

}
