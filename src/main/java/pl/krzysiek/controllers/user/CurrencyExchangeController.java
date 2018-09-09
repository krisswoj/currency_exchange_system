package pl.krzysiek.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.krzysiek.domain.AddFunds;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class CurrencyExchangeController {

    @RequestMapping(value = "/user/currency-exchange", method = RequestMethod.GET)
    public ModelAndView buySellCurrencyView(){
        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }

    @RequestMapping(value = "/user/currency-exchange", method = RequestMethod.POST)
    public ModelAndView verifyCurrencyExchange() throws IOException {
        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }
}
