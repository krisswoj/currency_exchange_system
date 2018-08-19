package pl.krzysiek.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.CurrencyRate;

@Repository
public interface ICurrencyRepository extends CrudRepository<CurrencyRate, Integer> {
}
