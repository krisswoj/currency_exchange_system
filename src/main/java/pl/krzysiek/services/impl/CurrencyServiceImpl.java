package pl.krzysiek.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysiek.api.currency_api.CurrencyApi;
import pl.krzysiek.dao.ICurrencyRepository;
import pl.krzysiek.domain.Currency;
import pl.krzysiek.services.CurrencyService;
import pl.krzysiek.services.ReaderXMLFilesService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    ReaderXMLFilesService readerXMLFilesService;
    @Autowired
    ICurrencyRepository currencyRepository;
    @Autowired
    CurrencyApi currencyApi;

    @Override
    public List<Currency> updateRateAllCurrency() throws IOException {
        List<Currency> currentRates = new ArrayList<>();

        for (Currency currency : this.allCurrenciesFromXml()) {
            currentRates.add(currencyApi.getActuallyRate(currency.getFromCurrency(), currency.getToCurrency()));
            currencyRepository.save(currentRates.get(currentRates.size() - 1));
        }
        return currentRates;
    }

    @Override
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

    @Override
    public List<List<Currency>> lastXRatesAllCurrency(Integer recordAmount) {

        List<List<Currency>> mainList = new ArrayList<>();

        for (Currency currency : this.allCurrenciesFromXml()) {
            List<Currency> currencyList = currencyRepository.lastCurrencyPairRates(currency.getPairName(), recordAmount);
            mainList.add(currencyList);
        }
        return mainList;
    }

    @Override
    public List<Currency> lastAllCurrencyRates() {

        List<Currency> currencyList = new ArrayList<>();
        for(Currency currency : this.allCurrenciesFromXml()){
            currencyList.add(currencyRepository.lastSingleCurrencyPairRates(currency.getPairName()));
        }
        return currencyList;
    }

}
