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
public class OptionalCurrencyConversionRequestDTO {

    @Pattern(regexp = "^[A-Z]{3}$", message = "A moeda do campo 'from' deve ter exatamente 3 letras maiúsculas.")
    @QueryParam("from")
    private String from;

    @Pattern(regexp = "^[A-Z]{3}$", message = "A moeda do campo 'to' deve ter exatamente 3 letras maiúsculas.")
    @QueryParam("to")
    private String to;
}
