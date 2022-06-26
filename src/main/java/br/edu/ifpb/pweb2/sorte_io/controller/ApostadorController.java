package br.edu.ifpb.pweb2.sorte_io.controller;

/* Erro nos validations */
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.repository.ApostadoresRepository;

@Controller
@RequestMapping("/apostadores")
public class ApostadorController {

	@Autowired
	ApostadoresRepository repositoryApostadores;

	@RequestMapping("/form")
	public ModelAndView getForm(Apostador apostador, ModelAndView model) {
		model.addObject("apostador", apostador);
		model.setViewName("/apostador/formApostador");

		return model;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid Apostador apostador, BindingResult validation, ModelAndView model) {
		if (validation.hasErrors()) {
			model.setViewName("/apostador/formApostador");
		}
		else {
			model.setViewName("/home");
		}

		return model;
	}
}
