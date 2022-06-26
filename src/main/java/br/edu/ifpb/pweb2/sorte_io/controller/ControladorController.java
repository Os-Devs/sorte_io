package br.edu.ifpb.pweb2.sorte_io.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import br.edu.ifpb.pweb2.sorte_io.model.Controlador;

@Controller
@RequestMapping("/controladores")
public class ControladorController {

	@RequestMapping("/form")
	public ModelAndView getForm(Controlador controlador, ModelAndView model) {
		model.addObject("controlador", controlador);
		model.setViewName("/controlador/formControlador");

		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid Controlador controlador, BindingResult validation, ModelAndView model) {
		if (validation.hasErrors()) {
			model.setViewName("/controlador/formControlador");
		}
		else {
			model.setViewName("/home");
		}

		return model;
	}
}
