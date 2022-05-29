package br.edu.ifpb.pweb2.sorte_io.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sorte_io.model.Controlador;

@Controller
@RequestMapping("/controladores")
public class ControladorController {

    @RequestMapping("/form")
    public ModelAndView getForm(Controlador controlador, ModelAndView model) {
        model.addObject("controlador", controlador);
        model.setViewName("./form/formControlador");

        return model;
    }
}
