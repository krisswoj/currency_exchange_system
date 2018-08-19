package pl.krzysiek.web;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.krzysiek.api.currency_api.CurrencyApi;
import pl.krzysiek.dao.ICurrencyRepository;
import pl.krzysiek.domain.CurrencyRate;
import pl.krzysiek.services.*;
import pl.krzysiek.api.simpay.SimpayApi;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


@Transactional
@RestController
public class CantorRestApi {


    @Autowired
    CurrencyRateService currencyRateService;

    @Autowired
    ICurrencyRepository currencyRepository;


    @RequestMapping(value = "/simpay/{code}", method = RequestMethod.GET)
    public String simpayAnswer(@PathVariable("code") String code) throws IOException {

        SimpayApi simpayApi = new SimpayApi("xxxx", "xxxx");
        return String.valueOf(simpayApi.getStatus(995, 92555, code));
    }

    @RequestMapping(value = "/currency", method = RequestMethod.GET)
    public CurrencyRate currency() throws IOException, JSONException {

        CurrencyRate currencyRate = new CurrencyApi().getActuallyRate("EUR", "PLN");
        return currencyRepository.save(currencyRate);
    }
}
