package br.com.entregapedido.clienteservice.config;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() throws IOException, XmlPullParserException {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.entregapedido.clienteservice.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.informacoesApi().build())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET());
        return docket;
    }

    private ApiInfoBuilder informacoesApi() throws IOException, XmlPullParserException {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));

        apiInfoBuilder.title("Cliente Microservice");
        apiInfoBuilder.description("Cliente Microservice - Resgistrar cliente e buscar cliente.");
        apiInfoBuilder.version(model.getVersion());
        apiInfoBuilder.license("Licença - PRIVADO");
        apiInfoBuilder.contact(this.contato());

        return apiInfoBuilder;
    }

    private Contact contato() {

        return new Contact(
                "Mohammad Shadnik",
                "",
                "mohammad.shadnik@gmail.com");
    }

    private List<ResponseMessage> responseMessageForGET() {
        List<ResponseMessage> responseMessages = new ArrayList<>();

        responseMessages.add(new ResponseMessageBuilder()
                .code(500)
                .message("Erro inesperado")
                .responseModel(new ModelRef("string"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(403)
                .message("Você não tem permissão para acessar este link")
                .build());

        return responseMessages;
    }
}