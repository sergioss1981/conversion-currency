package br.com.challenge.exception;

import br.com.challenge.model.ErrorResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class ExternalApiExceptionMapper implements ExceptionMapper<ExternalApiException> {

    @Override
    public Response toResponse(ExternalApiException exception) {
        log.error("Erro ao integrar com API externa: {}",  exception.getMessage());
        return Response.status(exception.getStatusCode())
                .entity(new ErrorResponse(exception.getStatusCode(), exception.getMessage()))
                .build();
    }

}

