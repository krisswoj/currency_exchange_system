package pl.krzysiek.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.krzysiek.api.currency_api.CurrencyApi;
import pl.krzysiek.dao.ICurrencyRepository;
import pl.krzysiek.domain.Currency;
import pl.krzysiek.services.*;
import pl.krzysiek.api.simpay.SimpayApi;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;


@Transactional
@RestController
public class CantorRestApi {


    @Autowired
    CurrencyService currencyService;

    @Autowired
    ICurrencyRepository currencyRepository;


    @RequestMapping(value = "/simpay/{code}", method = RequestMethod.GET)
    public String simpayAnswer(@PathVariable("code") String code) throws IOException {
        SimpayApi simpayApi = new SimpayApi("880ecda9", "02e93146be06015c5c9ad8fb95fd0c8b");
        return String.valueOf(simpayApi.getStatus(995, 92555, code));
    }

//    @RequestMapping(value = "/currency/{from}/{to}", method = RequestMethod.GET)
//    public Currency currency(@PathVariable("from") String from, @PathVariable("to") String to) throws IOException {
//        Currency currency = new CurrencyApi().getActuallyRate(from, to);
//        return currencyRepository.save(currency);
//    }

    @RequestMapping(value = "/all-xml", method = RequestMethod.GET)
    public List<Currency> getAllFromXml() {
        return currencyService.allCurrenciesFromXml();
    }

    @RequestMapping(value = "/update-all", method = RequestMethod.GET)
    public List<Currency> updateCurrencyRate() throws IOException {
        return currencyService.updateRateAllCurrency();
    }

    @RequestMapping(value = "/last-rates/{amount}", method = RequestMethod.GET)
    public List<List<Currency>> lastXAllCurrency(@PathVariable ("amount") Integer amount){
        return currencyService.lastXRatesAllCurrency(amount);
    }

    @RequestMapping(value = "/last-rates", method = RequestMethod.GET)
    public List<Currency> lastAllCurrencyRates(){
        return currencyService.lastAllCurrencyRates();
    }

    @RequestMapping(value = "/last-rate/{currencyPair}", method = RequestMethod.GET)
    public Currency lastCurrency(@PathVariable ("currencyPair") String currencyPair){
        return currencyRepository.lastSingleCurrencyPairRates(currencyPair);
    }

}
