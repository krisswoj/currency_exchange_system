package pl.krzysiek.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.Currency;

import java.util.Collection;

@Repository
public interface ICurrencyRepository extends CrudRepository<Currency, Integer> {

    @Query(nativeQuery = true, value = "select * from cantor.currency_rate where currency_rate.pair_name = 'USD_PLN' order by ID limit 10")
    Collection<Currency> lastTenUsdPlnRates();

    @Query(nativeQuery = true, value = "select * from cantor.currency_rate where currency_rate.pair_name = 'EUR_PLN' order by ID limit 10")
    Collection<Currency> lastTenEurPlnRates();

    @Query(nativeQuery = true, value = "select * from cantor.currency_rate where currency_rate.pair_name = 'GBP_PLN' order by ID limit 10")
    Collection<Currency> lastTenGbpPlnRates();

    @Query(nativeQuery = true, value = "select * from cantor.currency_rate where currency_rate.pair_name = 'CHF_PLN' order by ID limit 10")
    Collection<Currency> lastTenChfPlnRates();

    @Query(nativeQuery = true, value = "select * from cantor.currency_rate where currency_rate.pair_name = 'PLN_USD' order by ID limit 10")
    Collection<Currency> lastTenPlnUsdRates();

    @Query(nativeQuery = true, value = "select * from cantor.currency_rate where currency_rate.pair_name = 'PLN_EUR' order by ID limit 10")
    Collection<Currency> lastTenPlnEurRates();

    @Query(nativeQuery = true, value = "select * from cantor.currency_rate where currency_rate.pair_name = 'PLN_GBP' order by ID limit 10")
    Collection<Currency> lastTenPlnGbpRates();

    @Query(nativeQuery = true, value = "select * from cantor.currency_rate where currency_rate.pair_name = 'PLN_CHF' order by ID limit 10")
    Collection<Currency> lastTenPlnChfRates();



}
