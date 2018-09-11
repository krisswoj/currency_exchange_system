package pl.krzysiek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysiek.dao.IAccountRepository;
import pl.krzysiek.dao.ITransferFundsRepository;
import pl.krzysiek.domain.Account;
import pl.krzysiek.domain.TransferFunds;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransferFundsService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ITransferFundsRepository transferFundsRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AddFundsService addFundsService;


    public TransferFunds transferFunds(Account accountFrom, Account accountTo, String currency, Double amount, Double feeRate) {

        if (accountFrom.getPlnBalance() >= amount) {
            accountFrom.setPlnBalance(accountFrom.getPlnBalance() - amount);
            accountRepository.save(accountFrom);
            accountTo.setPlnBalance(accountTo.getPlnBalance()+ fees(amount, feeRate).get("afterFee"));
            accountRepository.save(accountTo);
        } else {
            throw new IllegalStateException("Brak wystarczajacych srodkow na koncie");
        }
        return transferFundsRepository.save(new TransferFunds(amount, currency, fees(amount, feeRate).get("feeAmount"), fees(amount, feeRate).get("afterFee"), 1, accountService.currentDate(), addFundsService.generateUniqId(), accountFrom, accountTo));
    }

    private Map<String, Double> fees(Double amount, Double feeRate) {
        Map<String, Double> fees = new HashMap<>();
        fees.put("feeAmount", (amount * feeRate));
        fees.put("afterFee", (amount - (amount * feeRate)));
        return fees;
    }

}
