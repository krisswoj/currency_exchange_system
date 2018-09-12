package pl.krzysiek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.currency_api.CurrencyApi;
import pl.krzysiek.dao.IAccountRepository;
import pl.krzysiek.dao.ICurrencyRepository;
import pl.krzysiek.dao.ICurrencyTransRepository;
import pl.krzysiek.domain.Account;
import pl.krzysiek.domain.Currency;
import pl.krzysiek.domain.CurrencyTrans;

import java.io.IOException;
import java.util.*;

@Service
public class CurrencyService {

    @Autowired
    private ReaderXMLFilesService readerXMLFilesService;
    @Autowired
    private ICurrencyRepository currencyRepository;
    @Autowired
    private CurrencyApi currencyApi;
    @Autowired
    private AccountService accountService;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private AddFundsService addFundsService;
    @Autowired
    private ICurrencyTransRepository currencyTransRepository;

    public List<Currency> updateRateAllCurrency() throws IOException {
        List<Currency> currentRates = new ArrayList<>();

        for (Currency currency : this.allCurrenciesFromXml()) {
            currentRates.add(currencyApi.getActuallyRate(currency.getPairName()));
            currencyRepository.save(currentRates.get(currentRates.size() - 1));
        }
        return currentRates;
    }

    public void updateSingleCurrencyPair(String currencyPair) throws IOException {
        currencyRepository.save(currencyApi.getActuallyRate(currencyPair));
    }

    public List<Currency> allCurrenciesFromXml() {

        String xmlPath = "xml_files/currency_list.xml";
        String xmlId = "currency";

        List<Currency> currencyList = new ArrayList<>();
        List<ArrayList<String>> xmlData = readerXMLFilesService.readXMLFiles(xmlPath, xmlId);

        for (ArrayList<String> position : xmlData) {
            Currency currency = new Currency(position.get(0) + "_" + position.get(1), position.get(0), position.get(1));
            currencyList.add(currency);
        }
        return currencyList;
    }

    public List<List<Currency>> lastXRatesAllCurrency(Integer recordAmount) {

        List<List<Currency>> mainList = new ArrayList<>();

        for (Currency currency : this.allCurrenciesFromXml()) {
            List<Currency> currencyList = currencyRepository.lastCurrencyPairRates(currency.getPairName(), recordAmount);
            mainList.add(currencyList);
        }
        return mainList;
    }

    public List<Currency> lastAllCurrencyRates() {

        List<Currency> currencyList = new ArrayList<>();
        for (Currency currency : this.allCurrenciesFromXml())
            currencyList.add(currencyRepository.lastSingleCurrencyPairRates(currency.getPairName()));

        return currencyList;
    }

    public Map<String, Currency> currencyMap() {
        Map<String, Currency> currencyMap = new HashMap<>();
        for (Currency currency : this.lastAllCurrencyRates())
            currencyMap.put(currency.getPairName(), currency);

        return currencyMap;
    }

    //In current version, for everthing to work correctly - the PLN currency must be the second. Example: EUR_PLN, USD_PLN.
    public String getCurrencyPair(String fromCurrency, String toCurrency) {
        if (!fromCurrency.equals("PLN"))
            return (fromCurrency + "_" + toCurrency);
        else
            return (toCurrency + "_" + fromCurrency);

    }

    public Map<String, Double> userCurrencyBalance() {
        Map<String, Double> userCurrencyMap = new HashMap<>();

        userCurrencyMap.put("PLN", accountService.loggedUser().getPlnBalance());
        userCurrencyMap.put("USD", accountService.loggedUser().getUsdBalance());
        userCurrencyMap.put("EUR", accountService.loggedUser().getEurBalance());
        userCurrencyMap.put("CNY", accountService.loggedUser().getCnyBalance());
        userCurrencyMap.put("GBP", accountService.loggedUser().getGbpBalance());

        return userCurrencyMap;
    }

    public void timeDifferenceForCurrencyPair(String currencyPair) throws IOException {

        long milliseconds = accountService.currentDate().getTime() - currencyRepository.lastSingleCurrencyPairRates(currencyPair).getAddDate().getTime();
        int seconds = (int) milliseconds / 1000;
        if (seconds > 900)
            this.updateSingleCurrencyPair(currencyPair);
    }

    // int kindOfOperation - You have set what you want to do -> Buy (1) currency or sell (2)
    public CurrencyTrans exchangeCurrencyTransaction(CurrencyTrans.Calcs calcs){
        CurrencyTrans currencyTrans = null;

        Account account = accountService.loggedUser();

        switch (calcs.getKindOfOperation()) {
            case 1:
                if (account.getPlnBalance() >= calcs.getFromCurrencyAmount()) {
                    account.setPlnBalance(account.getPlnBalance() - calcs.getFromCurrencyAmount());
                    account.setEurBalance(account.getEurBalance() + calcs.getToCurrencyAmount());
                    accountRepository.save(account);

                    currencyTrans = new CurrencyTrans(calcs.getFromCurrency(), calcs.getToCurrency(), calcs.getFromCurrencyAmount(), calcs.getToCurrencyAmount(), calcs.getBaseCurrencyRate(), calcs.getCurrencyRateWithFee(), calcs.getFeeRate(), calcs.getFee(), calcs.getKindOfOperation(), 1, accountService.currentDate(), addFundsService.generateUniqId(), accountService.loggedUser());

                    return currencyTransRepository.save(currencyTrans);

                } else {
                    throw new IllegalStateException("Brak wystarczajacych srodkow na koncie");
                }

            case 2:
                if (account.getEurBalance() >= calcs.getFromCurrencyAmount()) {
                    account.setEurBalance(account.getEurBalance() - calcs.getFromCurrencyAmount());
                    account.setPlnBalance(account.getPlnBalance() + calcs.getToCurrencyAmount());
                    accountRepository.save(account);

                    currencyTrans = new CurrencyTrans(calcs.getFromCurrency(), calcs.getToCurrency(), calcs.getFromCurrencyAmount(), calcs.getToCurrencyAmount(), calcs.getBaseCurrencyRate(), calcs.getCurrencyRateWithFee(), calcs.getFeeRate(), calcs.getFee(), calcs.getKindOfOperation(), 1, accountService.currentDate(), addFundsService.generateUniqId(), accountService.loggedUser());

                    return currencyTransRepository.save(currencyTrans);

                } else {
                    throw new IllegalStateException("Brak wystarczajacych srodkow na koncie");
                }

        }
        return currencyTrans;
    }

    // int kindOfOperation - You have set what you want to do -> Buy (1) currency or sell (2)
    public CurrencyTrans.Calcs exchangesValuesRates(String fromCurrency, Double currencyValueFrom, String toCurrency, Double currencyValueTo, Double feeRate, int kindOfOperation) throws IOException {

        String currencyPair = this.getCurrencyPair(fromCurrency, toCurrency);
        this.timeDifferenceForCurrencyPair(currencyPair);

        CurrencyTrans.Calcs cc = null;

        switch (kindOfOperation) {

            case 1:
                if (feeRate < 1)
                    feeRate += 1.0;


                if (currencyValueFrom != null) {
                    cc = new CurrencyTrans.Calcs(fromCurrency, toCurrency, currencyValueFrom, currencyDivision(currencyValueFrom, currencyPair, feeRate),
                            feeRate, currencyDivisionFee(currencyValueFrom, currencyPair, feeRate),
                            rateValue(currencyPair), currencyWithFee(currencyPair, feeRate), kindOfOperation);
                    return cc;

                } else {
                    cc = new CurrencyTrans.Calcs(fromCurrency, toCurrency, currencyMultiplication(currencyValueTo, currencyPair, feeRate), currencyValueTo,
                            feeRate, currencyMultiplicationFee(currencyValueTo, currencyPair, feeRate),
                            rateValue(currencyPair), currencyWithFee(currencyPair, feeRate), kindOfOperation);
                    return cc;
                }

            case 2:
                if (currencyValueFrom != null) {
                    cc = new CurrencyTrans.Calcs(fromCurrency, toCurrency, currencyValueFrom, currencyMultiplication(currencyValueFrom, currencyPair, feeRate),
                            feeRate, Math.abs(currencyMultiplicationFee(currencyValueFrom, currencyPair, feeRate)),
                            rateValue(currencyPair), currencyWithFee(currencyPair, feeRate), kindOfOperation);
                    return cc;

                } else {

                    cc = new CurrencyTrans.Calcs(fromCurrency, toCurrency, currencyDivision(currencyValueTo, currencyPair, feeRate), currencyValueTo,
                            feeRate, Math.abs(currencyDivisionFee(currencyValueTo, currencyPair, feeRate)),
                            rateValue(currencyPair), currencyWithFee(currencyPair, feeRate), kindOfOperation);
                    return cc;
                }
        }
        return cc;
    }

    private Double rateValue(String currencyPair) {
        return currencyRepository.lastSingleCurrencyPairRates(currencyPair).getRateValue();
    }

    private Double currencyDivision(Double currencyValue, String currencyPair, Double feeRate) {
        return (currencyValue / (rateValue(currencyPair) * feeRate));
    }

    private Double currencyMultiplication(Double currencyValue, String currencyPair, Double feeRate) {
        return (currencyValue * rateValue(currencyPair) * feeRate);
    }

    private Double currencyWithFee(String currencyPair, Double feeRate) {
        return (rateValue(currencyPair) * feeRate);
    }


    private Double currencyDivisionFee(Double currencyValue, String currencyPair, Double feeRate) {
        return ((currencyValue / (rateValue(currencyPair))) - (currencyValue / (rateValue(currencyPair) * feeRate)));
    }

    private Double currencyMultiplicationFee(Double currencyValue, String currencyPair, Double feeRate) {
        return ((currencyValue * (rateValue(currencyPair) * feeRate))) - currencyValue * rateValue(currencyPair);
    }
}