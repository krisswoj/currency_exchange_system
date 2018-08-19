package pl.krzysiek.services;

import pl.krzysiek.domain.Account;

import java.util.List;


public interface AccountService {

    List<Account> findAll();
    void addAccount(Account acc);
    Account getById(int id);
    Integer checkNickExists(String name);
    int checkPassword(String email, String password);
    Integer checkEmailExists(String email);
    Account findUserByEmail(Account account);
    Account loggedUser();
}
