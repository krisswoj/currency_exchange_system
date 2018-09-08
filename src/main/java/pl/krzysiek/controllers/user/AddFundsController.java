package pl.krzysiek.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.krzysiek.domain.AddFunds;
import pl.krzysiek.services.AddFundsService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class AddFundsController {

    @Autowired
    AddFundsService addFundsService;

    @RequestMapping(value = "/user/addfunds/sms", method = RequestMethod.GET)
    public ModelAndView addFundsBySmsView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sms_payment_verify", new AddFunds.AddFundsBySms());
        modelAndView.setViewName("logged_user/add_funds_by_sms");
        return modelAndView;
    }

    @RequestMapping(value = "/user/addfunds/sms", method = RequestMethod.POST)
    public ModelAndView verifySmsPayment(@Valid AddFunds.AddFundsBySms addFundsBySms) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sms_payment_verify", new AddFunds.AddFundsBySms());
        addFundsService.smsVerifyPayment(addFundsBySms);
        modelAndView.setViewName("logged_user/add_funds_by_sms");
        return modelAndView;
    }
}
