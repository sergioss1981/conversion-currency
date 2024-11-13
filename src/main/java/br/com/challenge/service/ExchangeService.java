package br.com.challenge.service;

import br.com.challenge.model.ConversionRateDTO;
import br.com.challenge.model.CurrencyConversionRequestDTO;
import br.com.challenge.model.OptionalCurrencyConversionRequestDTO;

import java.util.List;

public interface ExchangeService {
    ConversionRateDTO change(CurrencyConversionRequestDTO request);

    List<ConversionRateDTO> getFilteredRates(OptionalCurrencyConversionRequestDTO request);

}
