package br.edu.ifpb.pweb2.sorte_io.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    
    @RequestMapping("/home")
    public ModelAndView home(ModelAndView model) {
        model.setViewName("/home");

        return model;
    }

    
    @RequestMapping("/login")
    public ModelAndView login(ModelAndView model) {
        model.setViewName("./auth/login");

        return model;
    }
    
    @RequestMapping("/formApostador")
    public ModelAndView form(ModelAndView model) {
        model.setViewName("./form/formApostador");

        return model;
    }
}
