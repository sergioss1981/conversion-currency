package br.com.challenge;

import br.com.challenge.model.ConversionRateDTO;
import br.com.challenge.model.CurrencyConversionRequestDTO;
import br.com.challenge.service.ExchangeService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class CurrencyConverterResourceTest {

    @InjectMock
    ExchangeService exchangeService;

    @Test
    void testConvertRate_Success() {
        CurrencyConversionRequestDTO requestDTO = new CurrencyConversionRequestDTO();
        requestDTO.setFrom("BRL");
        requestDTO.setTo("USD");

        when(exchangeService.change(any())).thenReturn(ConversionRateDTO.builder().rate(1.0).toCurrency("USD").fromCurrency("BRL").build());

        given()
                .contentType("application/json")
                .queryParam("from", "BRL")
                .queryParam("to", "USD")
                .when()
                .get("/convert/rate")
                .then()
                .statusCode(200)
                .body("fromCurrency", equalTo("BRL"))
                .body("toCurrency", equalTo("USD"))
                .body("rate", equalTo(1.0F));
    }

}
