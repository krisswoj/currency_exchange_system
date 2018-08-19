package pl.krzysiek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.krzysiek.dao.IAccountRepository;
import pl.krzysiek.services.AccountService;

@Controller
public class LoginController {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login");
        return modelAndView;
    }
}
