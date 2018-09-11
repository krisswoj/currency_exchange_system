package pl.krzysiek.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import pl.krzysiek.domain.TransferFunds;

@Service
public interface ITransferFundsRepository extends CrudRepository<TransferFunds, Integer> {
}
