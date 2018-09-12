package pl.krzysiek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysiek.dao.IAccountRepository;
import pl.krzysiek.dao.ICasinoGameTransactionDetailsRepository;
import pl.krzysiek.dao.ICasinoTransactionsRepository;
import pl.krzysiek.domain.Account;
import pl.krzysiek.domain.CasinoGameTransactionDetails;
import pl.krzysiek.domain.CasinoTransactions;

import java.util.List;
import java.util.Random;

@Service
public class CasinoService {

    @Autowired
    AddFundsService addFundsService;

    @Autowired
    ICasinoGameTransactionDetailsRepository cgtdRepo;

    @Autowired
    ICasinoTransactionsRepository iCasinoTransactionsRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    IAccountRepository accountRepository;

    public void newGame(List<CasinoTransactions> casinoTransactionsList) {
        CasinoGameTransactionDetails cgtd = cgtdRepo.save(new
                CasinoGameTransactionDetails(generateRandomNumber(),
                addFundsService.generateUniqId(),
                addFundsService.generateUniqId()));

        for (CasinoTransactions casinoTransactions : casinoTransactionsList) {
            iCasinoTransactionsRepository.save(betVerify(casinoTransactions, cgtd));
        }
    }

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(10 + 1);
//        return 1;
    }

    private CasinoTransactions betVerify(CasinoTransactions casinoTransactions, CasinoGameTransactionDetails casinoGameTransactionDetails) {

        Account account = accountService.loggedUser();

        casinoTransactions.setDate(accountService.currentDate());
        casinoTransactions.setStatus(1);
        casinoTransactions.setAccountByUserId(account);
        casinoTransactions.setPrivateIndivdalCode(addFundsService.generateUniqId());
        casinoTransactions.setCasinoGameTransactionDetailsByGameSpinId(casinoGameTransactionDetails);

        if (account.getPlnBalance() < casinoTransactions.getAmount())
            throw new IllegalStateException("Brak wystarczajacych srodkow do rozpoczecia gry");
        else
            casinoCharge(account, casinoTransactions);

        switch (casinoTransactions.getKindOfBet()) {
            case 1: {
                if (casinoTransactions.getUserNumber() == restultCase1(casinoGameTransactionDetails.getResultNumber())) {
                    creditWonMoney(casinoTransactions, account, 2);
                    break;
                } else {
                    System.out.println("Przykro nam, nie wytypowałeś właściwego przedziału");
                    break;
                }
            }
            case 2: {
                if (casinoTransactions.getUserNumber() == resultCase2(casinoGameTransactionDetails.getResultNumber())) {
                    creditWonMoney(casinoTransactions, account, 2);
                    break;
                } else {
                    System.out.println("Przykro nam, nie określiłeś poprawnie czy liczba jest parzysta lub nie");
                    break;
                }
            }
            case 3: {
                if (casinoTransactions.getUserNumber() == (casinoGameTransactionDetails.getResultNumber())) {
                    creditWonMoney(casinoTransactions, account, 10);
                    break;
                } else {
                    System.out.println("Przykro nam, nie wytypowales właściwej liczby");
                    break;
                }
            }
        }
        return casinoTransactions;
    }

    private int restultCase1(int resultNumber) {
        return (resultNumber <= 5) ? 1 : 2;
    }

    private int resultCase2(int resultNumber) {
        if (resultNumber == 0)
            throw new IllegalStateException("0 nie jest liczba parzystą ani nieparzystą");
        return ((resultNumber % 2) == 0) ? 2 : 1;
    }

    private void casinoCharge(Account account, CasinoTransactions casinoTransactions) {
        account.setPlnBalance(account.getPlnBalance() - casinoTransactions.getAmount());
        accountRepository.save(account);
    }

    private CasinoTransactions creditWonMoney(CasinoTransactions casinoTransactions, Account account, int prizeMultiplied){
        account.setPlnBalance(account.getPlnBalance() + (casinoTransactions.getAmount() * prizeMultiplied));
        accountRepository.save(account);
        casinoTransactions.setWonAmount(casinoTransactions.getAmount() * prizeMultiplied);
        casinoTransactions.setStatus(2);
        casinoTransactions.setFinishDate(accountService.currentDate());

        return casinoTransactions;
    }
}

















