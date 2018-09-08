package pl.krzysiek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.krzysiek.domain.Account;
import pl.krzysiek.services.AccountService;

import javax.validation.Valid;

@Controller
@SessionAttributes("register")
public class RegisterController {

    @Autowired
    private AccountService accountService;


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        Account account = new Account();
        modelAndView.addObject("account", account);
        modelAndView.setViewName("login/register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Account account, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Account userExists = accountService.findUserByEmail(account);
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login/register");
        } else {
            accountService.addAccount(account);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("account", new Account());
            modelAndView.setViewName("login/register");
        }
        return modelAndView;
    }
}