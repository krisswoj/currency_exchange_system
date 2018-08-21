package pl.krzysiek.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krzysiek.domain.Currency;
import pl.krzysiek.services.CurrencyService;
import pl.krzysiek.services.ReaderXMLFilesService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    ReaderXMLFilesService readerXMLFilesService;


    @Override
    public List<Currency> updateRateAllCurrency() {

        return null;
    }

    @Override
    public List<Currency> getAllCurrencies(){
        String xmlPath = "xml_files/currency_list.xml";
        String xmlId = "currency";

        List<Currency> currencyList = new ArrayList<>();
        List<ArrayList<String>> xmlData = readerXMLFilesService.readXMLFiles(xmlPath, xmlId);

        for (ArrayList<String> position : xmlData){

                Currency currency = new Currency();
                currency.setFromCurrency(position.get(0));
                currency.setToCurrency(position.get(1));
                currency.setPairName(position.get(0)+"_"+position.get(1));

                Currency currency1 = new Currency();
                currency1.setFromCurrency(position.get(1));
                currency1.setToCurrency(position.get(0));
                currency1.setPairName(position.get(1)+"_"+position.get(0));

                currencyList.add(currency);
                currencyList.add(currency1);
            }
        return currencyList;
    }
}
