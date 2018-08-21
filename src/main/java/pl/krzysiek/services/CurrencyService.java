package pl.krzysiek.services;

import pl.krzysiek.domain.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> updateRateAllCurrency();
    List<Currency> getAllCurrencies();
}
