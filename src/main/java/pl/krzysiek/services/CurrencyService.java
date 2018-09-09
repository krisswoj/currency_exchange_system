package pl.krzysiek.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.currency_api.CurrencyApi;
import pl.krzysiek.dao.IAccountRepository;
import pl.krzysiek.dao.ICurrencyRepository;
import pl.krzysiek.domain.Currency;
import pl.krzysiek.domain.CurrencyTransaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyService {

    @Autowired
    ReaderXMLFilesService readerXMLFilesService;
    @Autowired
    ICurrencyRepository currencyRepository;
    @Autowired
    CurrencyApi currencyApi;
    @Autowired
    AccountService accountService;
    @Autowired
    IAccountRepository accountRepository;

    public List<Currency> updateRateAllCurrency() throws IOException {
        List<Currency> currentRates = new ArrayList<>();

        for (Currency currency : this.allCurrenciesFromXml()) {
            currentRates.add(currencyApi.getActuallyRate(currency.getFromCurrency(), currency.getToCurrency()));
            currencyRepository.save(currentRates.get(currentRates.size() - 1));
        }
        return currentRates;
    }

    public void updateSingleCurrencyPair(String fromCurrency, String toCurrency) throws IOException {
        currencyRepository.save(currencyApi.getActuallyRate(fromCurrency, toCurrency));
    }

    public List<Currency> allCurrenciesFromXml() {

        String xmlPath = "xml_files/currency_list.xml";
        String xmlId = "currency";

        List<Currency> currencyList = new ArrayList<>();
        List<ArrayList<String>> xmlData = readerXMLFilesService.readXMLFiles(xmlPath, xmlId);

        for (ArrayList<String> position : xmlData) {

            Currency currency = new Currency();
            currency.setFromCurrency(position.get(0));
            currency.setToCurrency(position.get(1));
            currency.setPairName(position.get(0) + "_" + position.get(1));

            Currency currency1 = new Currency();
            currency1.setFromCurrency(position.get(1));
            currency1.setToCurrency(position.get(0));
            currency1.setPairName(position.get(1) + "_" + position.get(0));

            currencyList.add(currency);
            currencyList.add(currency1);
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
        for (Currency currency : this.allCurrenciesFromXml()) {
            currencyList.add(currencyRepository.lastSingleCurrencyPairRates(currency.getPairName()));
        }
        return currencyList;
    }

    public Map<String, Currency> currencyMap() {
        Map<String, Currency> currencyMap = new HashMap<>();
        for (Currency currency : this.lastAllCurrencyRates()) {
            currencyMap.put(currency.getPairName(), currency);
        }
        return currencyMap;
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

    public void timeDifferenceForCurrencyPair(String fromCurrency, String toCurrency) throws IOException {

        long milliseconds = accountService.currentDate().getTime() - currencyRepository.lastSingleCurrencyPairRates(fromCurrency + "_" + toCurrency).getAddDate().getTime();
        int seconds = (int) milliseconds / 1000;
        if (seconds > 900) {
            this.updateSingleCurrencyPair(fromCurrency, toCurrency);
            this.updateSingleCurrencyPair(toCurrency, fromCurrency);
        }
    }

    // int kindOfOperation - You have set what you want to do -> Buy (1) currency or sell (2)
    public CurrencyTransaction exchangeCurrencyTransaction(String fromCurrency, Double currencyValueFrom, String toCurrency, Double currencyValueTo, Double systemFees, int kindOfOperation) throws IOException {
        CurrencyTransaction currencyTransaction = null;
        String currencyPairName = fromCurrency + "_" + toCurrency;

        switch (kindOfOperation) {
            case 1:


                break;
            case 2:


                break;
        }


        return currencyTransaction;
    }

    // int kindOfOperation - You have set what you want to do -> Buy (1) currency or sell (2)
    public CurrencyTransaction.CurrencyCalculations exchangesValuesRates(String fromCurrency, Double currencyValueFrom, String toCurrency, Double currencyValueTo, Double feeRate, int kindOfOperation) throws IOException {

        this.timeDifferenceForCurrencyPair(fromCurrency, toCurrency);
        String currencyPairName = "EUR_PLN";

        CurrencyTransaction.CurrencyCalculations currencyCalculations = null;

        switch (kindOfOperation) {

            case 1:
                if (feeRate < 1)
                    feeRate += 1.0;

                if (currencyValueFrom != null) {
                    CurrencyTransaction.CurrencyCalculations currencyCalculations1 = new CurrencyTransaction.CurrencyCalculations(fromCurrency, toCurrency, feeRate, currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue(), kindOfOperation);
                    currencyCalculations1.setFromCurrencyAmount(currencyValueFrom);
                    currencyCalculations1.setToCurrencyAmount(currencyValueFrom / ((currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * feeRate)));
                    currencyCalculations1.setCurrencyRateWithFee(currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * feeRate);
                    currencyCalculations1.setFee((currencyValueFrom / ((currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue()))) - (currencyValueFrom / ((currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * feeRate))));

                    currencyCalculations = currencyCalculations1;
                    return currencyCalculations;

                } else {
                    CurrencyTransaction.CurrencyCalculations currencyCalculations1 = new CurrencyTransaction.CurrencyCalculations(fromCurrency, toCurrency, feeRate, currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue(), kindOfOperation);
                    currencyCalculations1.setToCurrencyAmount(currencyValueTo);
                    currencyCalculations1.setFromCurrencyAmount(currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * currencyValueTo * feeRate);
                    currencyCalculations1.setCurrencyRateWithFee(currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * feeRate);
                    currencyCalculations1.setFee((currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * currencyValueTo * feeRate) - (currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * currencyValueTo));

                    currencyCalculations = currencyCalculations1;
                    return currencyCalculations;
                }


            case 2:
                if (currencyValueFrom != null) {
                    CurrencyTransaction.CurrencyCalculations currencyCalculations2 = new CurrencyTransaction.CurrencyCalculations(fromCurrency, toCurrency, feeRate, currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue(), kindOfOperation);
                    currencyCalculations2.setFromCurrencyAmount(currencyValueFrom);
                    currencyCalculations2.setToCurrencyAmount(currencyValueFrom * ((currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * feeRate)));
                    currencyCalculations2.setCurrencyRateWithFee(currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * feeRate);
                    currencyCalculations2.setFee((currencyValueFrom * ((currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * feeRate))) - currencyValueFrom * (currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue()));
                    currencyCalculations = currencyCalculations2;
                    return currencyCalculations;

                } else {
                    CurrencyTransaction.CurrencyCalculations currencyCalculations2 = new CurrencyTransaction.CurrencyCalculations(fromCurrency, toCurrency, feeRate, currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue(), kindOfOperation);
                    currencyCalculations2.setToCurrencyAmount(currencyValueTo);
                    currencyCalculations2.setFromCurrencyAmount(currencyValueTo / (currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * feeRate));
                    currencyCalculations2.setBaseCurrencyRate(currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue());
                    currencyCalculations2.setCurrencyRateWithFee(currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * feeRate);
                    currencyCalculations2.setFee((currencyValueTo / (currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue())) - (currencyValueTo / (currencyRepository.lastSingleCurrencyPairRates(currencyPairName).getRateValue() * feeRate)));
                    currencyCalculations = currencyCalculations2;
                    return currencyCalculations;
                }
        }
      return currencyCalculations;
    }

}





















