package pl.krzysiek.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.Currency;

import java.util.Collection;
import java.util.List;

@Repository
public interface ICurrencyRepository extends CrudRepository<Currency, Integer> {

    @Query(nativeQuery = true, value = "select * from cantor.currency where currency.pair_name = ?1 order by ID limit ?2")
    List<Currency> lastCurrencyPairRates(String currenyPair, Integer amountRecords);

    @Query(nativeQuery = true, value = "select * from cantor.currency where currency.pair_name = ?1 order by ID limit 1")
    Currency lastSingleCurrencyPairRates(String currencyPair);



}
