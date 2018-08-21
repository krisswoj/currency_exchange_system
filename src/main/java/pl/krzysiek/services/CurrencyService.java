package pl.krzysiek.services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.krzysiek.domain.Currency;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface CurrencyService {

    List<Currency> updateRateAllCurrency() throws IOException;
    List<Currency> getAllCurrencies();
    List<List<Currency>> getLastTenRatesAllCurrency();

}
