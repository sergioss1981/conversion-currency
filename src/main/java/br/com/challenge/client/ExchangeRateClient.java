package br.com.challenge.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "exchange-rate-api")
public interface ExchangeRateClient {

    @GET
    @Path("/v6/{api-key-exchange-rate}/pair/{from}/{to}")
    JsonObject getExchangeRate(@PathParam("api-key-exchange-rate") String apiKeyExchangeRate,
                               @PathParam("from") String fromCurrency,
                               @PathParam("to") String toCurrency);
}
