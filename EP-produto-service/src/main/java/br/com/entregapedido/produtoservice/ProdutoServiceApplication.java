package br.com.entregapedido.produtoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("br.com.entregapedido.produtoservice.model")
@EnableJpaRepositories("br.com.entregapedido.produtoservice.repository")
public class ProdutoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoServiceApplication.class, args);
	}

//	@Bean
//	public Sampler defaultSampler() {
//		return Sampler.ALWAYS_SAMPLE;
//	}

}
