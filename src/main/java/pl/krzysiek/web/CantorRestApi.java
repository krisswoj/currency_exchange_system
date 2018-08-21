package pl.krzysiek.web;

import org.json.JSONException;
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

        SimpayApi simpayApi = new SimpayApi("xxxx", "xxxx");
        return String.valueOf(simpayApi.getStatus(995, 92555, code));
    }

    @RequestMapping(value = "/currency/{from}/{to}", method = RequestMethod.GET)
    public Currency currency(@PathVariable("from") String from, @PathVariable("to") String to) throws IOException {

        Currency currency = new CurrencyApi().getActuallyRate(from, to);
        return currencyRepository.save(currency);
    }

    @RequestMapping(value = "/last", method = RequestMethod.GET)
    public List<Currency> last(){

        return (List<Currency>) currencyRepository.lastTenEurPlnRates();
    }

    @RequestMapping(value = "/all-xml", method = RequestMethod.GET)
    public List<Currency> getAllFromXml(){
        return currencyService.getAllCurrencies();
    }

    @RequestMapping(value = "/update-currency-rate", method = RequestMethod.GET)
    public List<Currency> updateCurrencyRate(){


        return null;
    }
}
