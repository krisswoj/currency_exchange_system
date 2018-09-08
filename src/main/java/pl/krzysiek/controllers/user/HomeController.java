package pl.krzysiek.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.krzysiek.services.CurrencyService;

@Controller
public class HomeController {

    @Autowired
    CurrencyService currencyService;



    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView homeView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("currency_rates", currencyService.currencyMap());
        modelAndView.addObject("user_balance", currencyService.userCurrencyBalance());
        modelAndView.setViewName("logged_user/home");
        return modelAndView;
    }

}
