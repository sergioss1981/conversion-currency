package br.com.challenge.service;

import br.com.challenge.client.ExchangeRateClient;
import br.com.challenge.exception.ExternalApiException;
import br.com.challenge.model.ConversionRate;
import br.com.challenge.model.ConversionRateDTO;
import br.com.challenge.model.CurrencyConversionRequestDTO;
import br.com.challenge.model.OptionalCurrencyConversionRequestDTO;
import br.com.challenge.repository.ConversionRateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ExchangeServiceImpl implements ExchangeService{

    @ConfigProperty(name = "api-key-exchange-rate")
    String apiKey;

    @Inject
    ConversionRateRepository repository;

    @Inject
    @RestClient
    ExchangeRateClient exchangeRateClient;

    @Transactional
    @Override
    public ConversionRateDTO change(CurrencyConversionRequestDTO request) {
        JsonObject response = fetchExchangeRate(request.getFrom(), request.getTo());
        double rate = response.getJsonNumber("conversion_rate").doubleValue();

        ConversionRate conversionRate = new ConversionRate(request.getFrom(), request.getTo(), rate, LocalDateTime.now());
        repository.persist(conversionRate);

        return ConversionRateDTO.builder()
                .fromCurrency(conversionRate.getFromCurrency())
                .toCurrency(conversionRate.getToCurrency())
                .rate(conversionRate.getRate())
                .timestamp(conversionRate.getTimestamp())
                .build();
    }

    private JsonObject fetchExchangeRate(String currencyFrom, String currencyTo) {
        try {
            JsonObject response = exchangeRateClient.getExchangeRate(apiKey, currencyFrom, currencyTo);

            if (response == null || !response.containsKey("conversion_rate")) {
                throw new ExternalApiException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Resposta inválida da API de taxa de câmbio.");
            }
            return response;

        } catch (ClientWebApplicationException e) {
            throw new ExternalApiException(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            throw new ExternalApiException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Erro ao comunicar com a API externa: " + e.getMessage());
        }
    }

    @Override
    public List<ConversionRateDTO> getFilteredRates(OptionalCurrencyConversionRequestDTO request) {
        List<ConversionRate> conversionRates = repository.findFilteredRates(request.getFrom(), request.getTo());

        return conversionRates.stream()
                .map(rate -> ConversionRateDTO.builder()
                        .fromCurrency(rate.getFromCurrency())
                        .toCurrency(rate.getToCurrency())
                        .rate(rate.getRate())
                        .timestamp(rate.getTimestamp())
                        .build())
                .collect(Collectors.toList());
    }
}
