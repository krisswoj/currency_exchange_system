package pl.krzysiek.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.CurrencyTrans;

@Repository
public interface ICurrencyTransRepository extends CrudRepository<CurrencyTrans, Integer> {
}
