package pl.krzysiek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.simpay.SimpayApi;
import pl.krzysiek.dao.IAccountRepository;
import pl.krzysiek.dao.IAddFundsRepository;
import pl.krzysiek.dao.ICurrencyRepository;
import pl.krzysiek.domain.Account;
import pl.krzysiek.domain.AddFunds;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddFundsService {

    @Autowired
    AccountService accountService;

    @Autowired
    ICurrencyRepository currencyRepository;

    @Autowired
    IAddFundsRepository addFundsRepository;

    @Autowired
    IAccountRepository accountRepository;


    public AddFunds smsVerifyPayment(AddFunds.AddFundsBySms addFundsBySms) throws IOException {
        SimpayApi simpayApi = new SimpayApi("880ecda9", "02e93146be06015c5c9ad8fb95fd0c8b");
        AddFunds addFunds;

            if (simpayApi.getStatus(addFundsBySms.getServiceNumber(), addFundsBySms.getPremiumNumber(), addFundsBySms.getSmsCode()).isSuccess()) {
                Account account = accountService.loggedUser();
                addFunds = new AddFunds(this.smsCommission().get(addFundsBySms.getPremiumNumber()), "PLN", 1, 1, this.generateUniqId(), addFundsBySms.getSmsCode(), account, accountService.currentDate());
                addFundsRepository.save(addFunds);
                account.setPlnBalance(account.getPlnBalance() + this.smsCommission().get(addFundsBySms.getPremiumNumber()));
                accountRepository.save(account);
                return addFunds;
            } else {
                System.out.println(String.valueOf(simpayApi.getStatus(addFundsBySms.getServiceNumber(), addFundsBySms.getPremiumNumber(), addFundsBySms.getSmsCode())));
                addFunds = new AddFunds();
                addFunds.setStatus(0);
                return addFunds;
            }
    }

    public String generateUniqId() {

        String uniqID = this.uniqString(8);
        if (addFundsRepository.existsByPrivateIndivdalCode(this.uniqString(8))) {
            this.generateUniqId();
        } else {
            return uniqID;
        }
        return uniqID;
    }

    private String uniqString(int count) {
        return RandomStringUtils.random(count, "123456789abcdefgh");
    }

    private Map<Integer, Double> smsCommission() {
        Map<Integer, Double> integerDoubleMap = new HashMap<>();

        //First value it's premium number, and second it's a amount of money in PLN currency which system will add to user account
        integerDoubleMap.put(7155, 10.0);
        integerDoubleMap.put(7555, 50.0);
        integerDoubleMap.put(92555, 300.0);

        return integerDoubleMap;
    }
}

