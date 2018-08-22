package pl.krzysiek.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView homeView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("logged_user/home");
        return modelAndView;
    }

}
