package br.com.challenge;

import br.com.challenge.exception.ExternalApiException;
import br.com.challenge.model.ConversionRateDTO;
import br.com.challenge.model.CurrencyConversionRequestDTO;
import br.com.challenge.model.OptionalCurrencyConversionRequestDTO;
import br.com.challenge.service.ExchangeService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/convert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
@Tag(name = "Currency Conversion", description = "Operações relacionadas à conversão de moedas.")
public class CurrencyConverterResource {

    @Inject
    ExchangeService exchangeService;

    @GET
    @Path("/rate")
    @Operation(summary = "Converte uma moeda para outra",
            description = "Este endpoint converte uma moeda de origem para uma moeda de destino com base na taxa de câmbio atual.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Conversão bem-sucedida",
                    content = @Content(
                            schema = @Schema(implementation = ConversionRateDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Resposta Sucesso",
                                            value = "{\"fromCurrency\": \"USD\", \"toCurrency\": \"BRL\", \"rate\": 5.12}"
                                    )
                            }
                    )),
            @APIResponse(responseCode = "400", description = "Requisição inválida, verifique os parâmetros"),
            @APIResponse(responseCode = "500", description = "Erro no servidor ao processar a conversão")
    })
    public Response getConversionRate(@BeanParam @Valid CurrencyConversionRequestDTO request) {
        try{
            log.info("Par de moedas recebido para conversão: from={}, to={}", request.getFrom(), request.getTo());
            ConversionRateDTO conversionRateDTO = exchangeService.change(request);
            return Response.ok(conversionRateDTO).build();
        } catch (ExternalApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ExternalApiException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Erro ao integrar com a API externa: " + e.getMessage());
        }
    }

    @GET
    @Path("/rates")
    @Operation(summary = "Filtra taxas de conversão de moedas por período",
            description = "Retorna uma lista de taxas de conversão para um par de moedas especificado dentro de um período de tempo opcional.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Filtragem bem-sucedida",
                    content = @Content(
                            schema = @Schema(implementation = ConversionRateDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Resposta Sucesso",
                                            value = "[{\"fromCurrency\": \"USD\", \"toCurrency\": \"BRL\", \"rate\": 5.10, \"timestamp\": \"2024-01-01 12:00:00\"},"
                                                    + "{\"fromCurrency\": \"USD\", \"toCurrency\": \"BRL\", \"rate\": 5.12, \"timestamp\": \"2024-01-02 12:00:00\"}]"
                                    )
                            }
                    )),
            @APIResponse(responseCode = "400", description = "Requisição inválida, verifique os parâmetros"),
            @APIResponse(responseCode = "500", description = "Erro ao obter taxas de conversão filtradas")
    })
    public Response getConversionRates(@BeanParam @Valid OptionalCurrencyConversionRequestDTO request) {

        try{
            log.info("Dados recebidos para filtrar Par de moedas e periodo: from={}, to={}", request.getFrom(), request.getTo());
            List<ConversionRateDTO> conversionRatesDTO = exchangeService.getFilteredRates(request);
            return Response.ok(conversionRatesDTO).build();

        } catch (ExternalApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ExternalApiException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Erro ao obter taxas de conversão filtradas: " + e.getMessage());
        }
    }
}

