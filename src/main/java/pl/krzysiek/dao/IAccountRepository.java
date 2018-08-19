package pl.krzysiek.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.krzysiek.domain.Account;

@Repository
public interface IAccountRepository extends CrudRepository<Account, Integer> {

    Account findById(int userId);
    Account findByName(String name);
    Account findByEmail(String email);
    Account findByPassword(String password);



}