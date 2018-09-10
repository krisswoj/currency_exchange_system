package pl.krzysiek.services;

import org.junit.Test;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.krzysiek.configuration.RestConfiguration;
import pl.krzysiek.configuration.RestInitializer;
import pl.krzysiek.configuration.SecurityConfiguration;
import pl.krzysiek.configuration.WebMvcConfig;
import pl.krzysiek.dao.IAddFundsRepository;
import pl.krzysiek.dao.ICurrencyRepository;
import pl.krzysiek.domain.CurrencyTrans;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestConfiguration.class, RestInitializer.class, SecurityConfiguration.class, WebMvcConfig.class})
//@Rollback
//@Commit
//@Transactional
public class TestClass {

    @Autowired
    AddFundsService addFundsService;

    @Autowired
    IAddFundsRepository addFundsRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    ICurrencyRepository currencyRepository;

//    @Test
//    public void testFunction() throws IOException {
//
//        Account account = new Account();
//        account.setUserId(7);
//
//        AddFunds.AddFundsBySms addFundsBySms = new AddFunds.AddFundsBySms();
//        addFundsBySms.setServiceNumber(995);
//        addFundsBySms.setPremiumNumber(92555);
//        addFundsBySms.setSmsCode("F24FB8");
//
//        System.out.println("-----------------------------");
//        System.out.println("zobaczymy co wypluje: " + addFundsService.smsVerifyPayment(addFundsBySms).getStatus());
//        System.out.println("-----------------------------");
//    }

//    @Test
//    public void checkCode() throws IOException {
//
//        String code = "B71F25";
//
//        SimpayApi simpayApi = new SimpayApi("880ecda9", "02e93146be06015c5c9ad8fb95fd0c8b");
//
//        System.out.println("-----------------------------");
//        System.out.println("co wypluje: " + String.valueOf(simpayApi.getStatus(995, 92555, code).isSuccess()));
//        System.out.println("-----------------------------");
//    }

//    @Test
//    public void checkTime(){
//
//        System.out.println("-----------------------------");
//        System.out.println("co wypluje: " + accountService.TimeDifferenceForCurrencyPair(currencyRepository.lastSingleCurrencyPairRates("EUR_PLN").getAddDate(), accountService.currentDate()));
//        System.out.println("-----------------------------");
//    }

    @Test
    public void currencyCalcultor() throws IOException {

        CurrencyTrans.Calcs exchangesValuesRates = currencyService.exchangesValuesRates("EUR", 1000.0, "PLN", null, 1.02, 1);
        exchangesValuesRates.getFee();

        CurrencyTrans.Calcs exchangesValuesRates1 = currencyService.exchangesValuesRates("EUR", null, "PLN", 1000.0, 1.02, 1);
        exchangesValuesRates1.getFee();

        CurrencyTrans.Calcs exchangesValuesRates2 = currencyService.exchangesValuesRates("EUR", 1000.0, "PLN", null, 0.98, 2);
        exchangesValuesRates2.getFee();

        CurrencyTrans.Calcs exchangesValuesRates3 = currencyService.exchangesValuesRates("EUR", null, "PLN", 1000.0, 0.98, 2);
        exchangesValuesRates3.getFee();

        System.out.println("-----------------------------");
        System.out.println("co wypluje: " +  exchangesValuesRates.getFee());
        System.out.println("-----------------------------");

    }
}













