package br.com.entregapedido.netflixzuulapigatewayserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    @Autowired
    public WebSecurity(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.cliente.register.path")).permitAll()
                .antMatchers(environment.getProperty("api.cliente.getByCpf.path")).permitAll()
                .antMatchers(environment.getProperty("api.cliente.actuator.path")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.produto.register.path")).permitAll()
                .antMatchers(environment.getProperty("api.produto.getProdutoByNcm.path")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.produto.addStockQuantity.path")).permitAll()
                .antMatchers(environment.getProperty("api.produto.actuator.path")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("api.pedido.register.path")).permitAll()
                .antMatchers(environment.getProperty("api.pedido.getPedidoById.path")).permitAll()
                .antMatchers(environment.getProperty("api.pedido.actuator.path")).permitAll()
                .antMatchers(environment.getProperty("api.entrega.getAll.path")).permitAll()
                .antMatchers(environment.getProperty("api.entrega.actuator.path")).permitAll()
                .antMatchers(environment.getProperty("api.zuul.actuator.path")).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthorizationFilter(authenticationManager(), environment));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
