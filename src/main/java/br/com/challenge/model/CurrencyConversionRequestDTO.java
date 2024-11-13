package br.com.challenge.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionRequestDTO {

    @NotNull(message = "Necessário informar a sigla da moeda no campo 'from'.")
    @Pattern(regexp = "^[A-Z]{3}$", message = "A moeda do campo 'from' deve ter exatamente 3 letras maiúsculas.")
    @QueryParam("from")
    private String from;

    @NotNull(message = "Necessário informar a sigla da moeda no campo 'to'.")
    @Pattern(regexp = "^[A-Z]{3}$", message = "A moeda do campo 'to' deve ter exatamente 3 letras maiúsculas.")
    @QueryParam("to")
    private String to;
}
