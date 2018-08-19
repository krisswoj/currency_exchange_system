package pl.krzysiek.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.krzysiek.dao.IAccountRepository;
import pl.krzysiek.dao.IRoleRepository;
import pl.krzysiek.domain.Account;
import pl.krzysiek.domain.Role;
import pl.krzysiek.services.AccountService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Account> findAll() {
        List<Account> retList = new ArrayList<>();
        for (Account acc : accountRepository.findAll()) {
            retList.add(acc);
        }
        return retList;
    }

    public void addAccount(Account acc) {
        acc.setPassword(bCryptPasswordEncoder.encode(acc.getPassword()));
        acc.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        acc.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        accountRepository.save(acc);
    }

    public Account getById(int id) {
        Account acc = accountRepository.findById(id);
        return acc;
    }

    public Integer checkNickExists(String name) {
        String userId;
        try {
            Account account = accountRepository.findByName(name);
            userId = String.valueOf(account.getUserId());
        } catch (Exception ex) {
            return 0;
        }
        return 1;
    }

    public int checkPassword(String email, String password) {

        Account passwordToCheck = accountRepository.findByEmail(email);
        if (passwordToCheck != null) {

            if (passwordToCheck.getPassword().equals(password)) {
                return 1;
            } else
                return 0;
        } else
            return 0;
    }

    public Integer checkEmailExists(String email) {
        String userId;
        try {
            Account account = accountRepository.findByEmail(email);
            userId = String.valueOf(account.getUserId());
        } catch (Exception ex) {
            return 0;
        }
        return 1;
    }

    public Account findUserByEmail(Account account) {
        return accountRepository.findByEmail(account.getEmail());
    }

    @Override
    public Account loggedUser() {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account account = accountRepository.findByEmail(userDetails.getUsername());
        return account;
    }
}
