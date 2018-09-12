package pl.krzysiek.dao;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.CasinoGameTransactionDetails;

@Repository
public interface ICasinoGameTransactionDetailsRepository extends CrudRepository<CasinoGameTransactionDetails, Integer> {
}
