package br.com.challenge.service;

import br.com.challenge.client.ExchangeRateClient;
import br.com.challenge.exception.ExternalApiException;
import br.com.challenge.model.ConversionRateDTO;
import br.com.challenge.model.CurrencyConversionRequestDTO;
import br.com.challenge.repository.ConversionRateRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
class ExchangeServiceTest {

    @Inject
    ExchangeService exchangeService;

    @InjectMock
    @RestClient
    ExchangeRateClient exchangeRateClient;

    @Mock
    ConversionRateRepository repository;

    private CurrencyConversionRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new CurrencyConversionRequestDTO();
        requestDTO.setFrom("BRL");
        requestDTO.setTo("USD");
    }

    @Test
    void testChange_Success() {
        double rate = 0.1734;

        JsonObject jsonResponse = createMockResponse(rate);
        when(exchangeRateClient.getExchangeRate(anyString(), anyString(), anyString())).thenReturn(jsonResponse);

        ConversionRateDTO result = exchangeService.change(requestDTO);

        assertNotNull(result);
        assertEquals(requestDTO.getFrom(), result.getFromCurrency());
        assertEquals(requestDTO.getTo(), result.getToCurrency());
        assertEquals(rate, result.getRate());
    }

    private JsonObject createMockResponse(double rate) {
        return Json.createObjectBuilder()
                .add("conversion_rate", rate)
                .build();
    }

    /**
     * Verifica como o método change trata com requestDTO nulo ou campos nulos em from e to.
     * Isso garante que exceções de NullPointerException não aconteçam de maneira inesperada.
     */
    @Test
    void testChange_NullRequestDTO() {
        requestDTO = null;

        assertThrows(NullPointerException.class, () -> {
            exchangeService.change(requestDTO);
        });
    }

    /**
     * Verifica se um código de moeda não corresponder ao formato padrão, lancando a excecao ExternalApiException
     */
    @Test
    void testChange_InvalidCurrencyCode() {
        requestDTO.setFrom("BR");
        requestDTO.setTo("USD");

        assertThrows(ExternalApiException.class, () -> exchangeService.change(requestDTO));
    }
}

