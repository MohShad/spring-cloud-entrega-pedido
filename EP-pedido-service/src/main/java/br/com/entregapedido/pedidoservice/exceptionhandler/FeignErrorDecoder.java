package br.com.entregapedido.pedidoservice.exceptionhandler;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String key, Response response) {
        switch (response.status()) {
            case 400:
                break;
            case 404: {
                if (key.contains("getById")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Object are not found.");
                }
                break;
            }
            default:
                return new Exception(response.reason());
        }
        return null;
    }

}
