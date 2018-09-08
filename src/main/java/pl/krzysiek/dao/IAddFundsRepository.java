package pl.krzysiek.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.krzysiek.domain.AddFunds;

@Repository
public interface IAddFundsRepository extends CrudRepository<AddFunds, Integer> {

        Boolean existsByPrivateIndivdalCode(String code);
}
