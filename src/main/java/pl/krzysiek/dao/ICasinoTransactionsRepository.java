package pl.krzysiek.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.CasinoTransactions;

@Repository
public interface ICasinoTransactionsRepository extends CrudRepository<CasinoTransactions, Integer> {
}
